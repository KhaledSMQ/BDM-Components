package br.com.produban.openbus.mqmonitor.integration;

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
	
	@Value("${kafka.mqmonitor.success.topic}")
	private String successTopic;
	
	@Value("${kafka.mqmonitor.failure.topic}")
	private String failureTopic;
	
	@Value("${kafka.mqmonitor.channel.avro.schema}")
	private String channelSchemaPath;

	@Value("${kafka.mqmonitor.queue.avro.schema}")
	private String queueSchemaPath;
	
	@SuppressWarnings("unchecked")
	public KafkaAvroPublisher<GenericRecord> kafkaAvroPublisher() {
		return new KafkaAvroPublisher<GenericRecord>(kafkaBrokerHost, requireAcks, isAsync,	batchMessages);
	}

	@Bean
	public OpenbusDataIngestion ingestor() {
		return OpenbusDataIngestion.OpenbusDataIngestionBuilder
				.aIngestionConfiguration()
				.withKafkaAvroPublisher(kafkaAvroPublisher())
				.withSuccessTopic(successTopic)
				.withFailureTopic(failureTopic)
				.build();
	}

	@Bean
	public Schema mqMonitorChannelSchema() throws IOException {
		return new Schema.Parser().parse(new File(channelSchemaPath));
	}
	
	@Bean
	public Schema mqMonitorQueueSchema() throws IOException {
		return new Schema.Parser().parse(new File(queueSchemaPath));
	}
}