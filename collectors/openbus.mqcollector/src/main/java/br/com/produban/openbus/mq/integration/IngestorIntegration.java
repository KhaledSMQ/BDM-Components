package br.com.produban.openbus.mq.integration;

import java.io.File;
import java.io.IOException;

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

	if (!ingestorIsEnabled())
	    return null;

	return new KafkaAvroPublisher<GenericRecord>(config.getString("openbus.publisher.kafkaConfig.brokerList"),
		config.getBoolean("openbus.publisher.kafkaConfig.requiredAcks"),
		config.getBoolean("openbus.publisher.kafkaConfig.isAsync"),
		config.getInt("openbus.publisher.kafkaConfig.batchNumMessages"));
    }

    @Bean
    public OpenbusDataIngestion ingestor() {

	if (!ingestorIsEnabled())
	    return null;

	return OpenbusDataIngestion.OpenbusDataIngestionBuilder.aIngestionConfiguration()
		.withKafkaAvroPublisher(kafkaAvroPublisher())
		.withSuccessTopic(config.getString("openbus.publisher.topicConfig.successTopic"))
		.withFailureTopic(config.getString("openbus.publisher.topicConfig.failureTopic")).build();
    }

    @Bean
    public boolean ingestorIsEnabled() {

	boolean isEnabled = config.getBoolean("openbus.publisher.enabled");

	if (isEnabled) {
	    System.out.println("\nSending with Openbus.ingestor is enabled for this application.");
	    System.out.println(
		    "For disabling Openbus ingestor just set the 'enable' property to false in the section 'openbus.publisher' at the application.conf file.\n");
	} else {
	    System.out.println("\nSending with Openbus.ingestor is NOT enabled for this application.");
	    System.out.println(
		    "For enabling Openbus ingestor just set the 'enable' property to true in the section 'openbus.publisher' at the application.conf file.\n");
	}

	return isEnabled;
    }

    @Bean
    public Schema schema() throws IOException {

	return new Schema.Parser().parse(new File(config.getString("openbus.publisher.schemaPath")));
    }
}
