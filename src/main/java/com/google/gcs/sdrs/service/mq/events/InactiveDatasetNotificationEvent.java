/**
 * Autogenerated by Avro
 *
 * <p>DO NOT EDIT DIRECTLY
 */
package com.google.gcs.sdrs.service.mq.events;

import org.apache.avro.message.BinaryMessageDecoder;
import org.apache.avro.message.BinaryMessageEncoder;
import org.apache.avro.message.SchemaStore;
import org.apache.avro.specific.SpecificData;

@SuppressWarnings("all")
/** Event sent to interested parties on inactive dataset. */
@org.apache.avro.specific.AvroGenerated
public class InactiveDatasetNotificationEvent extends org.apache.avro.specific.SpecificRecordBase
    implements org.apache.avro.specific.SpecificRecord {
  private static final long serialVersionUID = -4804922951281924811L;
  public static final org.apache.avro.Schema SCHEMA$ =
      new org.apache.avro.Schema.Parser()
          .parse(
              "{\"type\":\"record\",\"name\":\"InactiveDatasetNotificationEvent\",\"namespace\":\"com.google.gcs.sdrs.service.mq.events\",\"doc\":\"Event sent to interested parties on inactive dataset.\",\"fields\":[{\"name\":\"context\",\"type\":{\"type\":\"record\",\"name\":\"EventContext\",\"namespace\":\"com.google.gcs.sdrs.service.mq.events.context\",\"doc\":\"The event context used for all events\",\"fields\":[{\"name\":\"uuid\",\"type\":\"string\",\"doc\":\"Event UUID.\"},{\"name\":\"name\",\"type\":\"string\",\"doc\":\"Name of event.\"},{\"name\":\"correlationID\",\"type\":[\"null\",\"string\"],\"doc\":\"Unique ID that correlates multiple related events generated from a multi-stepped process.\",\"default\":null},{\"name\":\"timestamp\",\"type\":{\"type\":\"long\",\"logicalType\":\"timestamp-millis\"},\"doc\":\"Time of event expressed as number of milliseconds from the unix epoch, 1 January 1970 00:00:00.000 UTC.\"},{\"name\":\"version\",\"type\":\"string\",\"doc\":\"Version of event schema\"},{\"name\":\"group\",\"type\":[\"null\",{\"type\":\"record\",\"name\":\"groupRecord\",\"fields\":[{\"name\":\"groupID\",\"type\":\"string\",\"doc\":\"Unique event group ID\"},{\"name\":\"sequenceNumber\",\"type\":\"int\",\"doc\":\"Event sequence in the group. \"},{\"name\":\"totalNumber\",\"type\":\"int\",\"doc\":\"Total number of events in the group.\"}]}],\"doc\":\"Event group. Present if event is broken into multiple messages. \",\"default\":null},{\"name\":\"attributes\",\"type\":[\"null\",{\"type\":\"array\",\"items\":\"string\"}],\"doc\":\"Optional event attributes in key=value pairs\",\"default\":null}]}},{\"name\":\"version\",\"type\":\"string\",\"doc\":\"Version of event\"},{\"name\":\"projectId\",\"type\":\"string\"},{\"name\":\"bucket\",\"type\":\"string\"},{\"name\":\"directory\",\"type\":\"string\",\"doc\":\"The directory containing success delete marker that has been deleted. \"},{\"name\":\"trigger\",\"type\":\"string\",\"doc\":\"The trigger that results in delete. \"},{\"name\":\"inactiveAt\",\"type\":\"string\",\"doc\":\"Timestamp at which the directory was deleted. ISO format in UTC. i.e. 2018-09-01T10:10:00z \"}]}");

  public static org.apache.avro.Schema getClassSchema() {
    return SCHEMA$;
  }

  private static SpecificData MODEL$ = new SpecificData();

  private static final BinaryMessageEncoder<InactiveDatasetNotificationEvent> ENCODER =
      new BinaryMessageEncoder<InactiveDatasetNotificationEvent>(MODEL$, SCHEMA$);

  private static final BinaryMessageDecoder<InactiveDatasetNotificationEvent> DECODER =
      new BinaryMessageDecoder<InactiveDatasetNotificationEvent>(MODEL$, SCHEMA$);

  /** Return the BinaryMessageDecoder instance used by this class. */
  public static BinaryMessageDecoder<InactiveDatasetNotificationEvent> getDecoder() {
    return DECODER;
  }

  /**
   * Create a new BinaryMessageDecoder instance for this class that uses the specified {@link
   * SchemaStore}.
   *
   * @param resolver a {@link SchemaStore} used to find schemas by fingerprint
   */
  public static BinaryMessageDecoder<InactiveDatasetNotificationEvent> createDecoder(
      SchemaStore resolver) {
    return new BinaryMessageDecoder<InactiveDatasetNotificationEvent>(MODEL$, SCHEMA$, resolver);
  }

  /** Serializes this InactiveDatasetNotificationEvent to a ByteBuffer. */
  public java.nio.ByteBuffer toByteBuffer() throws java.io.IOException {
    return ENCODER.encode(this);
  }

  /** Deserializes a InactiveDatasetNotificationEvent from a ByteBuffer. */
  public static InactiveDatasetNotificationEvent fromByteBuffer(java.nio.ByteBuffer b)
      throws java.io.IOException {
    return DECODER.decode(b);
  }

  @Deprecated public com.google.gcs.sdrs.service.mq.events.context.EventContext context;
  /** Version of event */
  @Deprecated public java.lang.CharSequence version;

  @Deprecated public java.lang.CharSequence projectId;
  @Deprecated public java.lang.CharSequence bucket;
  /** The directory containing success delete marker that has been deleted. */
  @Deprecated public java.lang.CharSequence directory;
  /** The trigger that results in delete. */
  @Deprecated public java.lang.CharSequence trigger;
  /** Timestamp at which the directory was deleted. ISO format in UTC. i.e. 2018-09-01T10:10:00z */
  @Deprecated public java.lang.CharSequence inactiveAt;

  /**
   * Default constructor. Note that this does not initialize fields to their default values from the
   * schema. If that is desired then one should use <code>newBuilder()</code>.
   */
  public InactiveDatasetNotificationEvent() {}

  /**
   * All-args constructor.
   *
   * @param context The new value for context
   * @param version Version of event
   * @param projectId The new value for projectId
   * @param bucket The new value for bucket
   * @param directory The directory containing success delete marker that has been deleted.
   * @param trigger The trigger that results in delete.
   * @param inactiveAt Timestamp at which the directory was deleted. ISO format in UTC. i.e.
   *     2018-09-01T10:10:00z
   */
  public InactiveDatasetNotificationEvent(
      com.google.gcs.sdrs.service.mq.events.context.EventContext context,
      java.lang.CharSequence version,
      java.lang.CharSequence projectId,
      java.lang.CharSequence bucket,
      java.lang.CharSequence directory,
      java.lang.CharSequence trigger,
      java.lang.CharSequence inactiveAt) {
    this.context = context;
    this.version = version;
    this.projectId = projectId;
    this.bucket = bucket;
    this.directory = directory;
    this.trigger = trigger;
    this.inactiveAt = inactiveAt;
  }

  public org.apache.avro.Schema getSchema() {
    return SCHEMA$;
  }
  // Used by DatumWriter.  Applications should not call.
  public java.lang.Object get(int field$) {
    switch (field$) {
      case 0:
        return context;
      case 1:
        return version;
      case 2:
        return projectId;
      case 3:
        return bucket;
      case 4:
        return directory;
      case 5:
        return trigger;
      case 6:
        return inactiveAt;
      default:
        throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }

  // Used by DatumReader.  Applications should not call.
  @SuppressWarnings(value = "unchecked")
  public void put(int field$, java.lang.Object value$) {
    switch (field$) {
      case 0:
        context = (com.google.gcs.sdrs.service.mq.events.context.EventContext) value$;
        break;
      case 1:
        version = (java.lang.CharSequence) value$;
        break;
      case 2:
        projectId = (java.lang.CharSequence) value$;
        break;
      case 3:
        bucket = (java.lang.CharSequence) value$;
        break;
      case 4:
        directory = (java.lang.CharSequence) value$;
        break;
      case 5:
        trigger = (java.lang.CharSequence) value$;
        break;
      case 6:
        inactiveAt = (java.lang.CharSequence) value$;
        break;
      default:
        throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }

  /**
   * Gets the value of the 'context' field.
   *
   * @return The value of the 'context' field.
   */
  public com.google.gcs.sdrs.service.mq.events.context.EventContext getContext() {
    return context;
  }

  /**
   * Sets the value of the 'context' field.
   *
   * @param value the value to set.
   */
  public void setContext(com.google.gcs.sdrs.service.mq.events.context.EventContext value) {
    this.context = value;
  }

  /**
   * Gets the value of the 'version' field.
   *
   * @return Version of event
   */
  public java.lang.CharSequence getVersion() {
    return version;
  }

  /**
   * Sets the value of the 'version' field. Version of event
   *
   * @param value the value to set.
   */
  public void setVersion(java.lang.CharSequence value) {
    this.version = value;
  }

  /**
   * Gets the value of the 'projectId' field.
   *
   * @return The value of the 'projectId' field.
   */
  public java.lang.CharSequence getProjectId() {
    return projectId;
  }

  /**
   * Sets the value of the 'projectId' field.
   *
   * @param value the value to set.
   */
  public void setProjectId(java.lang.CharSequence value) {
    this.projectId = value;
  }

  /**
   * Gets the value of the 'bucket' field.
   *
   * @return The value of the 'bucket' field.
   */
  public java.lang.CharSequence getBucket() {
    return bucket;
  }

  /**
   * Sets the value of the 'bucket' field.
   *
   * @param value the value to set.
   */
  public void setBucket(java.lang.CharSequence value) {
    this.bucket = value;
  }

  /**
   * Gets the value of the 'directory' field.
   *
   * @return The directory containing success delete marker that has been deleted.
   */
  public java.lang.CharSequence getDirectory() {
    return directory;
  }

  /**
   * Sets the value of the 'directory' field. The directory containing success delete marker that
   * has been deleted.
   *
   * @param value the value to set.
   */
  public void setDirectory(java.lang.CharSequence value) {
    this.directory = value;
  }

  /**
   * Gets the value of the 'trigger' field.
   *
   * @return The trigger that results in delete.
   */
  public java.lang.CharSequence getTrigger() {
    return trigger;
  }

  /**
   * Sets the value of the 'trigger' field. The trigger that results in delete.
   *
   * @param value the value to set.
   */
  public void setTrigger(java.lang.CharSequence value) {
    this.trigger = value;
  }

  /**
   * Gets the value of the 'inactiveAt' field.
   *
   * @return Timestamp at which the directory was deleted. ISO format in UTC. i.e.
   *     2018-09-01T10:10:00z
   */
  public java.lang.CharSequence getInactiveAt() {
    return inactiveAt;
  }

  /**
   * Sets the value of the 'inactiveAt' field. Timestamp at which the directory was deleted. ISO
   * format in UTC. i.e. 2018-09-01T10:10:00z
   *
   * @param value the value to set.
   */
  public void setInactiveAt(java.lang.CharSequence value) {
    this.inactiveAt = value;
  }

  /**
   * Creates a new InactiveDatasetNotificationEvent RecordBuilder.
   *
   * @return A new InactiveDatasetNotificationEvent RecordBuilder
   */
  public static com.google.gcs.sdrs.service.mq.events.InactiveDatasetNotificationEvent.Builder
      newBuilder() {
    return new com.google.gcs.sdrs.service.mq.events.InactiveDatasetNotificationEvent.Builder();
  }

  /**
   * Creates a new InactiveDatasetNotificationEvent RecordBuilder by copying an existing Builder.
   *
   * @param other The existing builder to copy.
   * @return A new InactiveDatasetNotificationEvent RecordBuilder
   */
  public static com.google.gcs.sdrs.service.mq.events.InactiveDatasetNotificationEvent.Builder
      newBuilder(
          com.google.gcs.sdrs.service.mq.events.InactiveDatasetNotificationEvent.Builder other) {
    return new com.google.gcs.sdrs.service.mq.events.InactiveDatasetNotificationEvent.Builder(
        other);
  }

  /**
   * Creates a new InactiveDatasetNotificationEvent RecordBuilder by copying an existing
   * InactiveDatasetNotificationEvent instance.
   *
   * @param other The existing instance to copy.
   * @return A new InactiveDatasetNotificationEvent RecordBuilder
   */
  public static com.google.gcs.sdrs.service.mq.events.InactiveDatasetNotificationEvent.Builder
      newBuilder(com.google.gcs.sdrs.service.mq.events.InactiveDatasetNotificationEvent other) {
    return new com.google.gcs.sdrs.service.mq.events.InactiveDatasetNotificationEvent.Builder(
        other);
  }

  /** RecordBuilder for InactiveDatasetNotificationEvent instances. */
  public static class Builder
      extends org.apache.avro.specific.SpecificRecordBuilderBase<InactiveDatasetNotificationEvent>
      implements org.apache.avro.data.RecordBuilder<InactiveDatasetNotificationEvent> {

    private com.google.gcs.sdrs.service.mq.events.context.EventContext context;
    private com.google.gcs.sdrs.service.mq.events.context.EventContext.Builder contextBuilder;
    /** Version of event */
    private java.lang.CharSequence version;

    private java.lang.CharSequence projectId;
    private java.lang.CharSequence bucket;
    /** The directory containing success delete marker that has been deleted. */
    private java.lang.CharSequence directory;
    /** The trigger that results in delete. */
    private java.lang.CharSequence trigger;
    /**
     * Timestamp at which the directory was deleted. ISO format in UTC. i.e. 2018-09-01T10:10:00z
     */
    private java.lang.CharSequence inactiveAt;

    /** Creates a new Builder */
    private Builder() {
      super(SCHEMA$);
    }

    /**
     * Creates a Builder by copying an existing Builder.
     *
     * @param other The existing Builder to copy.
     */
    private Builder(
        com.google.gcs.sdrs.service.mq.events.InactiveDatasetNotificationEvent.Builder other) {
      super(other);
      if (isValidValue(fields()[0], other.context)) {
        this.context = data().deepCopy(fields()[0].schema(), other.context);
        fieldSetFlags()[0] = true;
      }
      if (other.hasContextBuilder()) {
        this.contextBuilder =
            com.google.gcs.sdrs.service.mq.events.context.EventContext.newBuilder(
                other.getContextBuilder());
      }
      if (isValidValue(fields()[1], other.version)) {
        this.version = data().deepCopy(fields()[1].schema(), other.version);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.projectId)) {
        this.projectId = data().deepCopy(fields()[2].schema(), other.projectId);
        fieldSetFlags()[2] = true;
      }
      if (isValidValue(fields()[3], other.bucket)) {
        this.bucket = data().deepCopy(fields()[3].schema(), other.bucket);
        fieldSetFlags()[3] = true;
      }
      if (isValidValue(fields()[4], other.directory)) {
        this.directory = data().deepCopy(fields()[4].schema(), other.directory);
        fieldSetFlags()[4] = true;
      }
      if (isValidValue(fields()[5], other.trigger)) {
        this.trigger = data().deepCopy(fields()[5].schema(), other.trigger);
        fieldSetFlags()[5] = true;
      }
      if (isValidValue(fields()[6], other.inactiveAt)) {
        this.inactiveAt = data().deepCopy(fields()[6].schema(), other.inactiveAt);
        fieldSetFlags()[6] = true;
      }
    }

    /**
     * Creates a Builder by copying an existing InactiveDatasetNotificationEvent instance
     *
     * @param other The existing instance to copy.
     */
    private Builder(com.google.gcs.sdrs.service.mq.events.InactiveDatasetNotificationEvent other) {
      super(SCHEMA$);
      if (isValidValue(fields()[0], other.context)) {
        this.context = data().deepCopy(fields()[0].schema(), other.context);
        fieldSetFlags()[0] = true;
      }
      this.contextBuilder = null;
      if (isValidValue(fields()[1], other.version)) {
        this.version = data().deepCopy(fields()[1].schema(), other.version);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.projectId)) {
        this.projectId = data().deepCopy(fields()[2].schema(), other.projectId);
        fieldSetFlags()[2] = true;
      }
      if (isValidValue(fields()[3], other.bucket)) {
        this.bucket = data().deepCopy(fields()[3].schema(), other.bucket);
        fieldSetFlags()[3] = true;
      }
      if (isValidValue(fields()[4], other.directory)) {
        this.directory = data().deepCopy(fields()[4].schema(), other.directory);
        fieldSetFlags()[4] = true;
      }
      if (isValidValue(fields()[5], other.trigger)) {
        this.trigger = data().deepCopy(fields()[5].schema(), other.trigger);
        fieldSetFlags()[5] = true;
      }
      if (isValidValue(fields()[6], other.inactiveAt)) {
        this.inactiveAt = data().deepCopy(fields()[6].schema(), other.inactiveAt);
        fieldSetFlags()[6] = true;
      }
    }

    /**
     * Gets the value of the 'context' field.
     *
     * @return The value.
     */
    public com.google.gcs.sdrs.service.mq.events.context.EventContext getContext() {
      return context;
    }

    /**
     * Sets the value of the 'context' field.
     *
     * @param value The value of 'context'.
     * @return This builder.
     */
    public com.google.gcs.sdrs.service.mq.events.InactiveDatasetNotificationEvent.Builder
        setContext(com.google.gcs.sdrs.service.mq.events.context.EventContext value) {
      validate(fields()[0], value);
      this.contextBuilder = null;
      this.context = value;
      fieldSetFlags()[0] = true;
      return this;
    }

    /**
     * Checks whether the 'context' field has been set.
     *
     * @return True if the 'context' field has been set, false otherwise.
     */
    public boolean hasContext() {
      return fieldSetFlags()[0];
    }

    /**
     * Gets the Builder instance for the 'context' field and creates one if it doesn't exist yet.
     *
     * @return This builder.
     */
    public com.google.gcs.sdrs.service.mq.events.context.EventContext.Builder getContextBuilder() {
      if (contextBuilder == null) {
        if (hasContext()) {
          setContextBuilder(
              com.google.gcs.sdrs.service.mq.events.context.EventContext.newBuilder(context));
        } else {
          setContextBuilder(
              com.google.gcs.sdrs.service.mq.events.context.EventContext.newBuilder());
        }
      }
      return contextBuilder;
    }

    /**
     * Sets the Builder instance for the 'context' field
     *
     * @param value The builder instance that must be set.
     * @return This builder.
     */
    public com.google.gcs.sdrs.service.mq.events.InactiveDatasetNotificationEvent.Builder
        setContextBuilder(
            com.google.gcs.sdrs.service.mq.events.context.EventContext.Builder value) {
      clearContext();
      contextBuilder = value;
      return this;
    }

    /**
     * Checks whether the 'context' field has an active Builder instance
     *
     * @return True if the 'context' field has an active Builder instance
     */
    public boolean hasContextBuilder() {
      return contextBuilder != null;
    }

    /**
     * Clears the value of the 'context' field.
     *
     * @return This builder.
     */
    public com.google.gcs.sdrs.service.mq.events.InactiveDatasetNotificationEvent.Builder
        clearContext() {
      context = null;
      contextBuilder = null;
      fieldSetFlags()[0] = false;
      return this;
    }

    /**
     * Gets the value of the 'version' field. Version of event
     *
     * @return The value.
     */
    public java.lang.CharSequence getVersion() {
      return version;
    }

    /**
     * Sets the value of the 'version' field. Version of event
     *
     * @param value The value of 'version'.
     * @return This builder.
     */
    public com.google.gcs.sdrs.service.mq.events.InactiveDatasetNotificationEvent.Builder
        setVersion(java.lang.CharSequence value) {
      validate(fields()[1], value);
      this.version = value;
      fieldSetFlags()[1] = true;
      return this;
    }

    /**
     * Checks whether the 'version' field has been set. Version of event
     *
     * @return True if the 'version' field has been set, false otherwise.
     */
    public boolean hasVersion() {
      return fieldSetFlags()[1];
    }

    /**
     * Clears the value of the 'version' field. Version of event
     *
     * @return This builder.
     */
    public com.google.gcs.sdrs.service.mq.events.InactiveDatasetNotificationEvent.Builder
        clearVersion() {
      version = null;
      fieldSetFlags()[1] = false;
      return this;
    }

    /**
     * Gets the value of the 'projectId' field.
     *
     * @return The value.
     */
    public java.lang.CharSequence getProjectId() {
      return projectId;
    }

    /**
     * Sets the value of the 'projectId' field.
     *
     * @param value The value of 'projectId'.
     * @return This builder.
     */
    public com.google.gcs.sdrs.service.mq.events.InactiveDatasetNotificationEvent.Builder
        setProjectId(java.lang.CharSequence value) {
      validate(fields()[2], value);
      this.projectId = value;
      fieldSetFlags()[2] = true;
      return this;
    }

    /**
     * Checks whether the 'projectId' field has been set.
     *
     * @return True if the 'projectId' field has been set, false otherwise.
     */
    public boolean hasProjectId() {
      return fieldSetFlags()[2];
    }

    /**
     * Clears the value of the 'projectId' field.
     *
     * @return This builder.
     */
    public com.google.gcs.sdrs.service.mq.events.InactiveDatasetNotificationEvent.Builder
        clearProjectId() {
      projectId = null;
      fieldSetFlags()[2] = false;
      return this;
    }

    /**
     * Gets the value of the 'bucket' field.
     *
     * @return The value.
     */
    public java.lang.CharSequence getBucket() {
      return bucket;
    }

    /**
     * Sets the value of the 'bucket' field.
     *
     * @param value The value of 'bucket'.
     * @return This builder.
     */
    public com.google.gcs.sdrs.service.mq.events.InactiveDatasetNotificationEvent.Builder setBucket(
        java.lang.CharSequence value) {
      validate(fields()[3], value);
      this.bucket = value;
      fieldSetFlags()[3] = true;
      return this;
    }

    /**
     * Checks whether the 'bucket' field has been set.
     *
     * @return True if the 'bucket' field has been set, false otherwise.
     */
    public boolean hasBucket() {
      return fieldSetFlags()[3];
    }

    /**
     * Clears the value of the 'bucket' field.
     *
     * @return This builder.
     */
    public com.google.gcs.sdrs.service.mq.events.InactiveDatasetNotificationEvent.Builder
        clearBucket() {
      bucket = null;
      fieldSetFlags()[3] = false;
      return this;
    }

    /**
     * Gets the value of the 'directory' field. The directory containing success delete marker that
     * has been deleted.
     *
     * @return The value.
     */
    public java.lang.CharSequence getDirectory() {
      return directory;
    }

    /**
     * Sets the value of the 'directory' field. The directory containing success delete marker that
     * has been deleted.
     *
     * @param value The value of 'directory'.
     * @return This builder.
     */
    public com.google.gcs.sdrs.service.mq.events.InactiveDatasetNotificationEvent.Builder
        setDirectory(java.lang.CharSequence value) {
      validate(fields()[4], value);
      this.directory = value;
      fieldSetFlags()[4] = true;
      return this;
    }

    /**
     * Checks whether the 'directory' field has been set. The directory containing success delete
     * marker that has been deleted.
     *
     * @return True if the 'directory' field has been set, false otherwise.
     */
    public boolean hasDirectory() {
      return fieldSetFlags()[4];
    }

    /**
     * Clears the value of the 'directory' field. The directory containing success delete marker
     * that has been deleted.
     *
     * @return This builder.
     */
    public com.google.gcs.sdrs.service.mq.events.InactiveDatasetNotificationEvent.Builder
        clearDirectory() {
      directory = null;
      fieldSetFlags()[4] = false;
      return this;
    }

    /**
     * Gets the value of the 'trigger' field. The trigger that results in delete.
     *
     * @return The value.
     */
    public java.lang.CharSequence getTrigger() {
      return trigger;
    }

    /**
     * Sets the value of the 'trigger' field. The trigger that results in delete.
     *
     * @param value The value of 'trigger'.
     * @return This builder.
     */
    public com.google.gcs.sdrs.service.mq.events.InactiveDatasetNotificationEvent.Builder
        setTrigger(java.lang.CharSequence value) {
      validate(fields()[5], value);
      this.trigger = value;
      fieldSetFlags()[5] = true;
      return this;
    }

    /**
     * Checks whether the 'trigger' field has been set. The trigger that results in delete.
     *
     * @return True if the 'trigger' field has been set, false otherwise.
     */
    public boolean hasTrigger() {
      return fieldSetFlags()[5];
    }

    /**
     * Clears the value of the 'trigger' field. The trigger that results in delete.
     *
     * @return This builder.
     */
    public com.google.gcs.sdrs.service.mq.events.InactiveDatasetNotificationEvent.Builder
        clearTrigger() {
      trigger = null;
      fieldSetFlags()[5] = false;
      return this;
    }

    /**
     * Gets the value of the 'inactiveAt' field. Timestamp at which the directory was deleted. ISO
     * format in UTC. i.e. 2018-09-01T10:10:00z
     *
     * @return The value.
     */
    public java.lang.CharSequence getInactiveAt() {
      return inactiveAt;
    }

    /**
     * Sets the value of the 'inactiveAt' field. Timestamp at which the directory was deleted. ISO
     * format in UTC. i.e. 2018-09-01T10:10:00z
     *
     * @param value The value of 'inactiveAt'.
     * @return This builder.
     */
    public com.google.gcs.sdrs.service.mq.events.InactiveDatasetNotificationEvent.Builder
        setInactiveAt(java.lang.CharSequence value) {
      validate(fields()[6], value);
      this.inactiveAt = value;
      fieldSetFlags()[6] = true;
      return this;
    }

    /**
     * Checks whether the 'inactiveAt' field has been set. Timestamp at which the directory was
     * deleted. ISO format in UTC. i.e. 2018-09-01T10:10:00z
     *
     * @return True if the 'inactiveAt' field has been set, false otherwise.
     */
    public boolean hasInactiveAt() {
      return fieldSetFlags()[6];
    }

    /**
     * Clears the value of the 'inactiveAt' field. Timestamp at which the directory was deleted. ISO
     * format in UTC. i.e. 2018-09-01T10:10:00z
     *
     * @return This builder.
     */
    public com.google.gcs.sdrs.service.mq.events.InactiveDatasetNotificationEvent.Builder
        clearInactiveAt() {
      inactiveAt = null;
      fieldSetFlags()[6] = false;
      return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public InactiveDatasetNotificationEvent build() {
      try {
        InactiveDatasetNotificationEvent record = new InactiveDatasetNotificationEvent();
        if (contextBuilder != null) {
          record.context = this.contextBuilder.build();
        } else {
          record.context =
              fieldSetFlags()[0]
                  ? this.context
                  : (com.google.gcs.sdrs.service.mq.events.context.EventContext)
                      defaultValue(fields()[0]);
        }
        record.version =
            fieldSetFlags()[1] ? this.version : (java.lang.CharSequence) defaultValue(fields()[1]);
        record.projectId =
            fieldSetFlags()[2]
                ? this.projectId
                : (java.lang.CharSequence) defaultValue(fields()[2]);
        record.bucket =
            fieldSetFlags()[3] ? this.bucket : (java.lang.CharSequence) defaultValue(fields()[3]);
        record.directory =
            fieldSetFlags()[4]
                ? this.directory
                : (java.lang.CharSequence) defaultValue(fields()[4]);
        record.trigger =
            fieldSetFlags()[5] ? this.trigger : (java.lang.CharSequence) defaultValue(fields()[5]);
        record.inactiveAt =
            fieldSetFlags()[6]
                ? this.inactiveAt
                : (java.lang.CharSequence) defaultValue(fields()[6]);
        return record;
      } catch (java.lang.Exception e) {
        throw new org.apache.avro.AvroRuntimeException(e);
      }
    }
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumWriter<InactiveDatasetNotificationEvent> WRITER$ =
      (org.apache.avro.io.DatumWriter<InactiveDatasetNotificationEvent>)
          MODEL$.createDatumWriter(SCHEMA$);

  @Override
  public void writeExternal(java.io.ObjectOutput out) throws java.io.IOException {
    WRITER$.write(this, SpecificData.getEncoder(out));
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumReader<InactiveDatasetNotificationEvent> READER$ =
      (org.apache.avro.io.DatumReader<InactiveDatasetNotificationEvent>)
          MODEL$.createDatumReader(SCHEMA$);

  @Override
  public void readExternal(java.io.ObjectInput in) throws java.io.IOException {
    READER$.read(this, SpecificData.getDecoder(in));
  }
}
