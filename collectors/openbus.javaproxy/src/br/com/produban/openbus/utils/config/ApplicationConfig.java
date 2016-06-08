package br.com.produban.openbus.utils.config;

import org.apache.avro.Schema;

public class ApplicationConfig {

	private final Schema schema;
	private final String redisHost;
	private final int redisPort;
	private final String redisUserInfo;
	private final String kafkaBrokerList;
	private final String kafkaSuccessTopic;
	private final String kafkaFailureTopic;
	private final boolean kafkaRequiredAcks;
	private final boolean kafkaIsAsync;
	private final int kafkaBatchNumMessages;

	public ApplicationConfig(Schema schema, String redisHost, int redisPort, String redisUserInfo,
			String kafkaBrokerList, String kafkaSuccessTopic, String kafkaFailureTopic, boolean kafkaRequiredAcks,
			boolean kafkaIsAsync, int kafkaBatchNumMessages) {
		super();
		this.schema = schema;
		this.redisHost = redisHost;
		this.redisPort = redisPort;
		this.redisUserInfo = redisUserInfo;
		this.kafkaBrokerList = kafkaBrokerList;
		this.kafkaSuccessTopic = kafkaSuccessTopic;
		this.kafkaFailureTopic = kafkaFailureTopic;
		this.kafkaRequiredAcks = kafkaRequiredAcks;
		this.kafkaIsAsync = kafkaIsAsync;
		this.kafkaBatchNumMessages = kafkaBatchNumMessages;
	}

	public Schema getSchema() {
		return schema;
	}

	public String getRedisHost() {
		return redisHost;
	}

	public int getRedisPort() {
		return redisPort;
	}

	public String getRedisUserInfo() {
		return redisUserInfo;
	}

	public String getKafkaBrokerList() {
		return kafkaBrokerList;
	}

	public String getKafkaSuccessTopic() {
		return kafkaSuccessTopic;
	}

	public String getKafkaFailureTopic() {
		return kafkaFailureTopic;
	}

	public boolean isKafkaRequiredAcks() {
		return kafkaRequiredAcks;
	}

	public boolean isKafkaIsAsync() {
		return kafkaIsAsync;
	}

	public int getKafkaBatchNumMessages() {
		return kafkaBatchNumMessages;
	}

}
