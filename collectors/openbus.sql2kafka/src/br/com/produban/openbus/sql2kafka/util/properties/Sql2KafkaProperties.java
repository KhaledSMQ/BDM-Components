package br.com.produban.openbus.sql2kafka.util.properties;

public class Sql2KafkaProperties {

	private String collectorListResultsExit;
	private String collectorName;
	private String kafkaBrokerList;
	private boolean kafkaRequiredAcks;
	private String kafkaSuccessTopic;
	private String kafkaFailureTopic;
	private boolean kafkaAsync;
	private int kafkaBatchNumMessages;
	private String ingestorSchemaFile;

	public String getCollectorListResultsExit() {
		return collectorListResultsExit;
	}

	public void setCollectorListResultsExit(String collectorListResultsExit) {
		this.collectorListResultsExit = collectorListResultsExit;
	}

	public String getCollectorName() {
		return collectorName;
	}

	public void setCollectorName(String collectorName) {
		this.collectorName = collectorName;
	}

	public String getKafkaBrokerList() {
		return kafkaBrokerList;
	}

	public void setKafkaBrokerList(String kafkaBrokerList) {
		this.kafkaBrokerList = kafkaBrokerList;
	}

	public boolean isKafkaRequiredAcks() {
		return kafkaRequiredAcks;
	}

	public void setKafkaRequiredAcks(boolean kafkaRequiredAcks) {
		this.kafkaRequiredAcks = kafkaRequiredAcks;
	}

	public String getKafkaSuccessTopic() {
		return kafkaSuccessTopic;
	}

	public void setKafkaSuccessTopic(String kafkaSuccessTopic) {
		this.kafkaSuccessTopic = kafkaSuccessTopic;
	}

	public String getKafkaFailureTopic() {
		return kafkaFailureTopic;
	}

	public void setKafkaFailureTopic(String kafkaFailureTopic) {
		this.kafkaFailureTopic = kafkaFailureTopic;
	}

	public boolean isKafkaAsync() {
		return kafkaAsync;
	}

	public void setKafkaAsync(boolean kafkaAsync) {
		this.kafkaAsync = kafkaAsync;
	}

	public int getKafkaBatchNumMessages() {
		return kafkaBatchNumMessages;
	}

	public void setKafkaBatchNumMessages(int kafkaBatchNumMessages) {
		this.kafkaBatchNumMessages = kafkaBatchNumMessages;
	}

	public String getIngestorSchemaFile() {
		return ingestorSchemaFile;
	}

	public void setIngestorSchemaFile(String ingestorSchemaFile) {
		this.ingestorSchemaFile = ingestorSchemaFile;
	}

}
