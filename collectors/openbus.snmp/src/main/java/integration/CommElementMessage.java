package integration;

import br.com.produban.openbus.model.avro.CommElement;
import integration.kafka.KafkaMessageBase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;

public class CommElementMessage extends KafkaMessageBase<CommElement> {

    private static final Logger LOGGER = LoggerFactory.getLogger(CommElementMessage.class);

    @Override
    public List<CommElement> toAvro() {
        LOGGER.info("Populating avro data.");

        CommElement avro = new CommElement();
        avro.setInfo(getModel().getInfo());
        avro.setObjectId(getModel().getId());
        avro.setPropertyName(getModel().getPropertyName());
        avro.setPropertyValue(getModel().getPropertyValue());
        avro.setTimestamp(String.valueOf(System.currentTimeMillis()));

        return Arrays.asList(new CommElement[] {avro});
    }
}
