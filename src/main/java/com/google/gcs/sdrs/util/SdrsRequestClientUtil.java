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
 */

package com.google.gcs.sdrs.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.util.Utils;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import java.io.IOException;
import java.security.interfaces.RSAPrivateKey;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.builder.fluent.Configurations;
import org.apache.commons.configuration2.ex.ConfigurationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SdrsRequestClientUtil {

  private static final Logger logger = LoggerFactory.getLogger(SdrsRequestClientUtil.class);
  private static String serviceUrl;
  private static String protocol;

  private SdrsRequestClientUtil() {}

  public static Invocation.Builder request(String path) throws ConfigurationException {
    String jwt = generateJwt(String.format("https://%s", getServiceUrl()));

    Invocation.Builder client =
        ClientBuilder.newClient()
            .target(String.format("%s://%s", getProtocol(), getServiceUrl()))
            .path(path)
            .request()
            .header("Authorization", String.format("Bearer %s", jwt));

    return client;
  }

  private static String generateJwt(String audience) {
    HttpTransport httpTransport = Utils.getDefaultTransport();
    JsonFactory jsonFactory = Utils.getDefaultJsonFactory();

    try {
      GoogleCredential cred = GoogleCredential.getApplicationDefault(httpTransport, jsonFactory);

      Date now = new Date();
      Date expTime = new Date(System.currentTimeMillis() + TimeUnit.SECONDS.toMillis(3600));

      JWTCreator.Builder token =
          JWT.create()
              .withIssuedAt(now)
              .withExpiresAt(expTime)
              .withIssuer(cred.getServiceAccountId())
              .withSubject(cred.getServiceAccountId())
              .withAudience(audience)
              .withClaim("email", cred.getServiceAccountId());

      // Sign the JWT with a service account
      RSAPrivateKey key = (RSAPrivateKey) cred.getServiceAccountPrivateKey();
      Algorithm algorithm = Algorithm.RSA256(null, key);
      return token.sign(algorithm);
    } catch (IOException exception) {
      logger.error(String.format("Unable to generate JWT: %s", exception.getMessage()));
      return "";
    }
  }

  private static String getServiceUrl() throws ConfigurationException {
    if (serviceUrl == null) {
      Configuration config = new Configurations().xml("applicationConfig.xml");
      serviceUrl = config.getString("serverConfig.serviceUrl");
    }
    return serviceUrl;
  }

  private static String getProtocol() throws ConfigurationException {
    if (protocol == null) {
      Configuration config = new Configurations().xml("applicationConfig.xml");
      protocol = config.getString("serverConfig.protocol");
    }
    return protocol;
  }
}
