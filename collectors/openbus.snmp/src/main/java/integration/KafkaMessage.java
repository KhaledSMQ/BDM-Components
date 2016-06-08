package integration;

import org.apache.avro.generic.GenericRecord;

import java.util.List;

public interface KafkaMessage<A extends GenericRecord> {
    List<A> toAvro();
    String getTool();
}
