package integration.kafka;

import br.com.openbus.publisher.kafka.KafkaAvroPublisher;
import br.com.produban.openbus.ingestor.OpenbusDataIngestion;
import br.com.produban.openbus.ingestor.OpenbusIngestion;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import integration.CommElementMessage;
import org.apache.avro.Schema;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class KafkaIntegrationTask<A extends GenericRecord> implements Runnable {

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaIntegrationTask.class);

    private static KafkaIntegrationTask instance;

    private String APP_CONFIG_ROOT = "openbus";
    private String APP_CONFIG_SCHEMA = "schemaFile";
    private String APP_CONFIG_KAFKA = "kafkaConfig";
    private String APP_CONFIG_TOPIC = "topicConfig";

    private final KafkaAvroPublisher<GenericRecord> publisher;
    
    private GenericRecord record;

    private String resolvedTool;
    private OpenbusIngestion ingestion;
    private Schema schema;

    private static Config config;

    @SuppressWarnings("unchecked")
    private KafkaIntegrationTask() {

        Config config = ConfigFactory.load();

        Config kafkaConf = config.getConfig(APP_CONFIG_ROOT).getConfig(APP_CONFIG_KAFKA);
        Config topicConf = config.getConfig(APP_CONFIG_ROOT).getConfig(APP_CONFIG_TOPIC);

        publisher = new KafkaAvroPublisher<GenericRecord>(
                kafkaConf.getString("brokerList"),
                kafkaConf.getBoolean("requiredAcks"),
                kafkaConf.getBoolean("isAsync"),
                kafkaConf.getInt("batchNumMessages"));

        loadSchema(config);

        ingestion = OpenbusDataIngestion.OpenbusDataIngestionBuilder
                .aIngestionConfiguration()
                .withKafkaAvroPublisher(publisher)
                .withSuccessTopic(topicConf.getString("successTopic"))
                .withFailureTopic(topicConf.getString("failureTopic")).build();

        record = new GenericData.Record(this.schema);
    }

    private static void init() {
        instance = new KafkaIntegrationTask();
    }

    public static KafkaIntegrationTask getInstance() {
        if (instance == null)
            init();

        return instance;
    }

    public void setRecord(CommElementMessage data) {
        java.util.Map<String, String> extrainfo = data.getModel().getInfo();

        record.put("hostname", data.getModel().getInfo().get("sysName"));
        record.put("metric", data.getModel().getPropertyName());
        record.put("value", data.getModel().getPropertyValue());
        record.put("extraInfo", extrainfo);
        record.put("timestamp", String.valueOf(System.currentTimeMillis()));

        resolvedTool = data.getTool();
    }

    private void loadSchema(Config config) {
        String filePath = config.getConfig(APP_CONFIG_ROOT).getString(APP_CONFIG_SCHEMA);

        if (filePath == null) {
            throw new IllegalStateException("Schema file is required");
        } else if (!filePath.endsWith(".avsc")) {
            throw new IllegalStateException("Schema file is invalid");
        }

        File file = new File(filePath);

        try (InputStream is = new FileInputStream(file)){
            schema = new Schema.Parser().parse(is);
        } catch (IOException io) {
            LOGGER.error("Invalid avsc file.", io);
        }
    }

    @Override
    public void run() {
        try {
            LOGGER.debug("Ingesting...", record);
            ingestion.ingest(resolvedTool, record);
        } catch (Exception e) {
            LOGGER.error("Fail to send collected data to Kafka", e);
            return;
        }
    }
}
