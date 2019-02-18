/*
 * Copyright 2019 Google LLC. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the “License”);
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an “AS IS” BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and limitations under the License.
 *
 * Any software provided by Google hereunder is distributed “AS IS”,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, and is not intended for production use.
 *
 */

package com.google.gcs.sdrs.util;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.util.Utils;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.util.Preconditions;
import com.google.api.services.storage.StorageScopes;
import com.google.api.services.storagetransfer.v1.Storagetransfer;
import com.google.api.services.storagetransfer.v1.StoragetransferScopes;
import com.google.api.services.storagetransfer.v1.model.Date;
import com.google.api.services.storagetransfer.v1.model.GcsData;
import com.google.api.services.storagetransfer.v1.model.ObjectConditions;
import com.google.api.services.storagetransfer.v1.model.Schedule;
import com.google.api.services.storagetransfer.v1.model.TimeOfDay;
import com.google.api.services.storagetransfer.v1.model.TransferJob;
import com.google.api.services.storagetransfer.v1.model.TransferOptions;
import com.google.api.services.storagetransfer.v1.model.TransferSpec;
import com.google.api.services.storagetransfer.v1.model.UpdateTransferJobRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.util.List;

/**
 * Manages the concrete integration with STS
 */
public class StsUtil {

  private static final String STS_ENABLED_STRING = "ENABLED";
  private static final Logger logger = LoggerFactory.getLogger(StsUtil.class);

  /**
   * Creates an instance of the STS Client
   */
  public static Storagetransfer createStsClient() throws IOException {
    HttpTransport httpTransport = Utils.getDefaultTransport();
    JsonFactory jsonFactory = Utils.getDefaultJsonFactory();
    GoogleCredential credential =
        GoogleCredential.getApplicationDefault(httpTransport, jsonFactory);
    return createStorageTransferClient(httpTransport, jsonFactory, credential);
  }

  /**
   * Submits a job to be executed by STS
   * @param client the {@link Storagetransfer} client to use
   * @param projectId the project ID of the target GCP project
   * @param sourceBucket the bucket from which you want to move
   * @param destinationBucket the destination bucket you want to move to
   * @param prefixes a {@link List} of all prefixes to include in the transfer job
   * @param description the description of the transfer job
   * @param startDateTime the {@link ZonedDateTime} when you want the job to start
   * @return the {@link TransferJob} object that is created
   * @throws IOException when the client or job cannot be created
   */
  public static TransferJob createStsJob(Storagetransfer client,
                                         String projectId,
                                         String sourceBucket,
                                         String destinationBucket,
                                         List<String> prefixes,
                                         String description,
                                         ZonedDateTime startDateTime)
      throws IOException {

    // Subtracting a day to force STS to trigger the job immediately
    TransferJob transferJob = buildTransferJob(projectId, sourceBucket, destinationBucket,
        prefixes, description, startDateTime, true, null);

    return client.transferJobs().create(transferJob).execute();
  }

  /**
   * Creates a recurring scheduled STS job
   * @param client the {@link Storagetransfer} client to use
   * @param projectId the project ID of the target GCP project
   * @param sourceBucket the name of the bucket from which you want to move
   * @param destinationBucket the name of the bucket to which you want to move
   * @param prefixesToExclude a {@link List} of prefixes to exclude from the job
   * @param description the description of the job as it will appear in the STS console
   * @param startDateTime a {@link ZonedDateTime} of when you would like the job to begin
   * @param retentionInDays the period in days for which to retain records
   * @return the {@link TransferJob} object that is created
   * @throws IOException when the client or job cannot be created
   */
  public static TransferJob createDefaultStsJob(Storagetransfer client,
                                                String projectId,
                                                String sourceBucket,
                                                String destinationBucket,
                                                List<String> prefixesToExclude,
                                                String description,
                                                ZonedDateTime startDateTime,
                                                Integer retentionInDays)
      throws IOException {

    TransferJob transferJob = buildTransferJob(projectId,
        sourceBucket, destinationBucket, prefixesToExclude,
        description, startDateTime, false, retentionInDays);

    return client.transferJobs().create(transferJob).execute();
  }

  /**
   * Updates an existing transfer job within STS
   * @param client the {@link Storagetransfer} client to use
   * @param jobToUpdate the {@link TransferJob} object to submit as an update
   * @return the updated {@link TransferJob} object
   * @throws IOException when the client connection can't be established or the request fails
   */
  public static TransferJob updateExistingJob(Storagetransfer client, TransferJob jobToUpdate)
      throws IOException{

    UpdateTransferJobRequest requestBody = new UpdateTransferJobRequest();

    requestBody.setProjectId(jobToUpdate.getProjectId());
    requestBody.setTransferJob(jobToUpdate);

    Storagetransfer.TransferJobs.Patch request = client.transferJobs().patch(jobToUpdate.getName(), requestBody);
    return request.execute();
  }

  /**
   * Gets the record from STS for an existing job
   * @param client the {@link Storagetransfer} client to use
   * @param projectId the project ID of the target GCP project
   * @param jobName the name of the transfer job to retrieve
   * @return the {@link TransferJob} object, if it exists
   * @throws IOException when the client can't establish a connection
   */
  public static TransferJob getExistingJob(
      Storagetransfer client, String projectId, String jobName) throws IOException {
    Storagetransfer.TransferJobs.Get request = client.transferJobs().get(jobName);
    request.setProjectId(projectId);

    return request.execute();
  }

  static TransferJob buildTransferJob(String projectId,
                                      String sourceBucket,
                                      String destinationBucket,
                                      List<String> prefixes,
                                      String description,
                                      ZonedDateTime startDateTime,
                                      Boolean isOneTimeSchedule,
                                      Integer retentionInDays) {
    return new TransferJob()
        .setProjectId(projectId)
        .setDescription(description)
        .setTransferSpec(
            buildTransferSpec(sourceBucket, destinationBucket,
                prefixes, true, retentionInDays))
        .setSchedule(buildSchedule(startDateTime, isOneTimeSchedule))
        .setStatus(STS_ENABLED_STRING);
  }

  static TransferSpec buildTransferSpec(String sourceBucket,
                                        String destinationBucket,
                                        List<String> prefixes,
                                        Boolean isExcludePrefixes,
                                        Integer retentionInDays) {
    return new TransferSpec()
        .setGcsDataSource(new GcsData().setBucketName(sourceBucket))
        .setGcsDataSink(new GcsData().setBucketName(destinationBucket))
        .setObjectConditions(buildObjectConditions(prefixes, isExcludePrefixes, retentionInDays))
        .setTransferOptions(
            new TransferOptions()
                .setDeleteObjectsFromSourceAfterTransfer(false)
                .setOverwriteObjectsAlreadyExistingInSink(true));
  }


  static ObjectConditions buildObjectConditions(
      List<String> prefixes, Boolean isExcludePrefixes, Integer retentionInDays){

    ObjectConditions objectConditions = new ObjectConditions();

    if (retentionInDays != null) {
      objectConditions.setMinTimeElapsedSinceLastModification(
          convertRetentionInDaysToDuration(retentionInDays));
    }

    if (isExcludePrefixes) {
      objectConditions.setExcludePrefixes(prefixes);
    } else {
      objectConditions.setIncludePrefixes(prefixes);
    }

    return objectConditions;
  }

  static Schedule buildSchedule(ZonedDateTime startDateTime, Boolean isOneTimeSchedule) {

    // Subtracting a day to force STS to trigger the job immediately
    Date date = convertToDate(startDateTime.toLocalDate().minusDays(1));
    TimeOfDay startTime = convertToTimeOfDay(startDateTime.toLocalTime());

    Schedule schedule = new Schedule()
        .setScheduleStartDate(date)
        .setStartTimeOfDay(startTime);

    if (isOneTimeSchedule) {
      schedule.setScheduleEndDate(date);
    }

    return schedule;
  }

  private static Storagetransfer createStorageTransferClient(
      HttpTransport httpTransport, JsonFactory jsonFactory, GoogleCredential credential) {

    Preconditions.checkNotNull(httpTransport);
    Preconditions.checkNotNull(jsonFactory);
    Preconditions.checkNotNull(credential);

    // In some cases, you need to add the scope explicitly.
    if (credential.createScopedRequired()) {
      credential = credential.createScoped(StorageScopes.all());
      credential = credential.createScoped(StoragetransferScopes.all());
    }

    HttpRequestInitializer initializer = new RetryHttpInitializerWrapper(credential);
    return new Storagetransfer.Builder(httpTransport, jsonFactory, initializer)
        .setApplicationName("sdrs")
        .build();
  }

  static @NotNull String convertRetentionInDaysToDuration(int retentionInDays) {
    int ONE_DAY_IN_SECS = 3600 * 24;
    return (retentionInDays * ONE_DAY_IN_SECS) + "s";
  }

  static @NotNull Date convertToDate(LocalDate startDate) {
    Date googleDate = new Date();
    googleDate.setYear(startDate.getYear());
    googleDate.setMonth(startDate.getMonthValue());
    googleDate.setDay(startDate.getDayOfMonth());

    return googleDate;
  }

  static @NotNull TimeOfDay convertToTimeOfDay(LocalTime startTime) {
    TimeOfDay timeOfDay = new TimeOfDay();
    timeOfDay.setHours(startTime.getHour());
    timeOfDay.setMinutes(startTime.getMinute());
    timeOfDay.setSeconds(startTime.getSecond());

    return timeOfDay;
  }
}
