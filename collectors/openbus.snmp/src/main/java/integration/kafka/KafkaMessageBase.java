package integration.kafka;

import integration.KafkaMessage;
import integration.snmp.model.SnmpModel;
import org.apache.avro.generic.GenericRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class KafkaMessageBase<A extends GenericRecord> implements KafkaMessage<A> {

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaMessageBase.class);

    private SnmpModel model;
    private String tool;

    public KafkaMessageBase() {
    }

    public SnmpModel getModel() {
        return model;
    }

    public void setModel(SnmpModel model) {
        this.model = model;
    }

    @Override
    public String getTool() {
        return tool;
    }

    public void setTool(String tool) {
        this.tool = tool;
    }
}
