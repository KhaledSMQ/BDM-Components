package br.com.produban.openbus.cmdbsummary.configuration;

import java.io.File;
import java.io.IOException;

import org.apache.avro.Schema;
import org.apache.avro.generic.GenericRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.openbus.publisher.kafka.KafkaAvroPublisher;
import br.com.produban.openbus.ingestor.OpenbusDataIngestion;

@Configuration
public class IngestorConfiguration {

	@Value("${kafka.config.brokers.host}")
	private String kafkaBrokerHost;

	@Value("${kafka.config.required.acks}")
	private boolean requireAcks;

	@Value("${kafka.config.async}")
	private boolean isAsync;

	@Value("${kafka.config.batch.messages}")
	private int batchMessages;
	
	@Value("${kafka.success.topic}")
	private String successTopic;
	
	@Value("${kafka.failure.topic}")
	private String failureTopic;
	
	@Value("${avro.schema}")
	private String schemaPath;
	
	@SuppressWarnings("unchecked")
	@Bean
	public KafkaAvroPublisher<GenericRecord> kafkaAvroPublisher(){
		return new KafkaAvroPublisher<GenericRecord>(kafkaBrokerHost, requireAcks, isAsync, batchMessages);
	} 
	
	@Bean
	public OpenbusDataIngestion ingestor(){
		return OpenbusDataIngestion.OpenbusDataIngestionBuilder.aIngestionConfiguration()
				.withKafkaAvroPublisher(kafkaAvroPublisher())
				.withSuccessTopic(successTopic)
				.withFailureTopic(failureTopic).build();
	}
	
	@Bean
	public Schema schema() throws IOException {
		return new Schema.Parser().parse(new File(schemaPath));
	}
}
