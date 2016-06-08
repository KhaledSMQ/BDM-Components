package br.com.produban.openbus.sql2kafka.util.properties;

public class Constants {

	public static final String COLLECT_JDBC_CONNECTION_STRING = "collect.jdbc.connection.string";
	public static final String COLLECT_JDBC_CONNECTION_USER ="collect.jdbc.connection.user";
	public static final String COLLECT_JDBC_CONNECTION_PASSWORD = "collect.jdbc.connection.password";
	public static final String COLLECT_JDBC_DRIVER_CLASS = "collect.jdbc.driver.class";
	public static final String COLLECT_TYPE = "collect.type";
	public static final String COLLECT_TIME_DATE_FORMAT = "collect.time.date.format";
	public static final String COLLECT_INITIAL_VALUE = "collect.initial.value";
	public static final String COLLECT_OFFSET_COLUMN_NAME = "collect.offset.column.name";
	public static final String COLLECT_SQL_QUERY_FILE = "collect.sql.query.file";
	public static final String COLLECT_AVRO_SCHEMA_CLASS = "collect.avro.schema.class";
	public static final String COLLECT_TOOL = "collect.tool";
	public static final String COLLECT_CRON_EVERY_MIN = "collect.cron.every.min";

	public static final String COLLECTOR_LIST_METRICS_EXIT = "collector.list.results.exit";
	public static final String COLLECTOR_NAME = "collectorName";

	public static final String KAFKA_BROKER_LIST = "kafka.broker.list";
	public static final String KAFKA_REQUIRED_ACKS = "kafka.required.acks";
	public static final String KAFKA_TOPIC_SUCCESS = "kafka.topic.success";
	public static final String KAFKA_TOPIC_FAILURE = "kafka.topic.failure";
	public static final String KAFKA_ASYNC = "kafka.async";
	public static final String KAFKA_BATCH_NUM_MESSAGES = "kafka.batch.num.messages";
	
	public static final String INGESTOR_SCHEMA_FILE = "ingestor.schema.file";

	public static final String SQL_QUERY_PARAMETER_START_TIME = "startTime";
	public static final String SQL_QUERY_PARAMETER_STOP_TIME = "stopTime";
	public static final String SQL_QUERY_PARAMETER_OFFSET = "offset";
}
