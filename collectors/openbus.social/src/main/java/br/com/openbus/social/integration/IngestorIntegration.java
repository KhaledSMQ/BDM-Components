package br.com.openbus.social.integration;

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
public class IngestorIntegration {

	@Value("${kafka.config.brokers.host}")
	private String kafkaBrokerHost;

	@Value("${kafka.config.required.acks}")
	private boolean requireAcks;

	@Value("${kafka.config.async}")
	private boolean isAsync;

	@Value("${kafka.config.batch.messages}")
	private int batchMessages;

	@Value("${kafka.social.success.topic}")
	private String socialSucessTopic;

	@Value("${kafka.social.failure.topic}")
	private String socialFailureTopic;
	
	@Value("${kafka.facebook.avro.schema}")
	private String schemaPath;

	@SuppressWarnings("unchecked")
	@Bean
	public KafkaAvroPublisher<GenericRecord> kafkaPublishier() {
		return new KafkaAvroPublisher<GenericRecord>(kafkaBrokerHost, requireAcks, isAsync, batchMessages);
	}

	@Bean
	public OpenbusDataIngestion ingestor() {
		return OpenbusDataIngestion.OpenbusDataIngestionBuilder.aIngestionConfiguration()
				.withKafkaAvroPublisher(kafkaPublishier()).withSuccessTopic(socialSucessTopic)
				.withFailureTopic(socialFailureTopic).build();
	}

	@Bean
	public Schema schema() throws IOException {
		return new Schema.Parser().parse(new File(schemaPath));
	}

}
