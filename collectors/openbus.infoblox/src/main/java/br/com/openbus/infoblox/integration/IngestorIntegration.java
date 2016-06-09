package br.com.openbus.infoblox.integration;

import java.io.File;

import org.apache.avro.Schema;
import org.apache.avro.generic.GenericRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.typesafe.config.Config;

import br.com.openbus.publisher.kafka.KafkaAvroPublisher;
import br.com.produban.openbus.ingestor.OpenbusDataIngestion;

@Configuration
public class IngestorIntegration {

    @Autowired
    private Config config;

    @SuppressWarnings("unchecked")
    public KafkaAvroPublisher<GenericRecord> kafkaAvroPublisher() {

	return new KafkaAvroPublisher<GenericRecord>(
		config.getString("openbus.publisher.kafkaConfig.brokerList"),
		config.getBoolean("openbus.publisher.kafkaConfig.requiredAcks"),
		config.getBoolean("openbus.publisher.kafkaConfig.isAsync"),
		config.getInt("openbus.publisher.kafkaConfig.batchNumMessages"));
    }

    @Bean
    public OpenbusDataIngestion ingestor() {

	return OpenbusDataIngestion.OpenbusDataIngestionBuilder.aIngestionConfiguration()
		.withKafkaAvroPublisher(kafkaAvroPublisher())
		.withSuccessTopic(config.getString("openbus.publisher.topicConfig.successTopic"))
		.withFailureTopic(config.getString("openbus.publisher.topicConfig.failureTopic")).build();
    }

    @Bean
    public Schema schema() throws Exception {

	return new Schema.Parser().parse(new File(config.getString("openbus.publisher.schemaFile")));
    }
}
