package br.com.produban.openbus.introscope.sender.kafka;

import br.com.openbus.publisher.kafka.KafkaAvroPublisher;
import br.com.produban.openbus.ingestor.OpenbusDataIngestion;
import br.com.produban.openbus.ingestor.OpenbusIngestion;
import br.com.produban.openbus.introscope.sender.ISender;
import br.com.produban.openbus.model.avro.Introscope;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import org.apache.avro.Schema;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class KafkaSender extends ISender {

    private Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    private static KafkaSender instance;

    private String APP_CONFIG_ROOT = "openbus";
    private String APP_CONFIG_SCHEMA = "schemaFile";
    private String APP_CONFIG_KAFKA = "kafkaConfig";
    private String APP_CONFIG_TOPIC = "topicConfig";

    private final KafkaAvroPublisher<GenericRecord> publisher;

    private GenericRecord record;

    private final String resolvedTool;
    private OpenbusIngestion ingestion;
    private Schema schema;

    private static Config config;
    private static Config kafkaConf;
    private static Config topicConf;

    private KafkaSender() {

        config = ConfigFactory.load();

        kafkaConf = config.getConfig(APP_CONFIG_ROOT).getConfig(APP_CONFIG_KAFKA);
        topicConf = config.getConfig(APP_CONFIG_ROOT).getConfig(APP_CONFIG_TOPIC);


        publisher = new KafkaAvroPublisher<GenericRecord>(
                kafkaConf.getString("brokerList"),
                kafkaConf.getBoolean("requiredAcks"),
                kafkaConf.getBoolean("isAsync"),
                kafkaConf.getInt("batchNumMessages"));

        resolvedTool = "Introscope";

        loadSchema(config);

        ingestion = OpenbusDataIngestion.OpenbusDataIngestionBuilder
                .aIngestionConfiguration()
                .withKafkaAvroPublisher(publisher)
                .withSuccessTopic(topicConf.getString("successTopic"))
                .withFailureTopic(topicConf.getString("failureTopic")).build();

        record = new GenericData.Record(this.schema);
    }

    private static void init() {
        instance = new KafkaSender();
    }

    public static KafkaSender getInstance() {
        if (instance == null) {
            init();
        }

        return instance;
    }

    private void loadSchema(Config config) {
        String filePath = config.getConfig(APP_CONFIG_ROOT).getString(APP_CONFIG_SCHEMA);

        if (filePath == null) {
            throw new IllegalStateException("Schema file is required");
        } else if (!filePath.endsWith(".avsc")) {
            throw new IllegalStateException("Schema file is invalid");
        }

        File file = new File(filePath);

        try (InputStream is = new FileInputStream(file)) {
            schema = new Schema.Parser().parse(is);
        } catch (IOException io) {
            LOGGER.error("Invalid avsc file.", io);
        }
    }

    public void send(Introscope introscope) {
        try {
            record.put("host", introscope.getHost().toUpperCase());
            record.put("domain", introscope.getDomain());
            record.put("resource", introscope.getResource().replaceAll("[\\-\\|\\@]", "."));
            record.put("agentName", introscope.getAgentName());
            record.put("metricName", introscope.getMetricName());
            record.put("value", introscope.getValue());
            record.put("timestamp", introscope.getTimestamp() + "000");
            record.put("extraInfo", new HashMap<String, String>());

            ingestion.ingest(resolvedTool, record);

        } catch (Exception e) {
            LOGGER.error("Error in kafka publisher.", e);
        }
    }
}
