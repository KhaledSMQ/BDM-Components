package br.com.produban.openbus.sql2kafka.queue.serialization;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.apache.avro.Schema;
import org.apache.avro.io.BinaryDecoder;
import org.apache.avro.io.BinaryEncoder;
import org.apache.avro.io.DecoderFactory;
import org.apache.avro.io.EncoderFactory;
import org.apache.avro.specific.SpecificDatumReader;
import org.apache.avro.specific.SpecificDatumWriter;
import org.apache.avro.specific.SpecificRecordBase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Serializer {
	private Logger LOG = LoggerFactory.getLogger(this.getClass());
	
	private Schema schema;
	
	public Serializer(String avroSchemaClasss) {
		try {
			this.schema = ((SpecificRecordBase) Class.forName(avroSchemaClasss).newInstance()).getSchema();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			LOG.error("Error getting schema for deserializer.", e);
		}
	}
	
	public byte[] serialize(SpecificRecordBase specificRecordBase) throws IOException {
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		SpecificDatumWriter<SpecificRecordBase> genericDatumWriter = new SpecificDatumWriter<SpecificRecordBase>(schema);
		BinaryEncoder encoder = EncoderFactory.get().binaryEncoder(byteArrayOutputStream, null);
		genericDatumWriter.write(specificRecordBase, encoder);
		encoder.flush();
		return byteArrayOutputStream.toByteArray();
	}

	public SpecificRecordBase deserialize(byte[] bytes) throws IOException, ClassNotFoundException {
		ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
		SpecificDatumReader<SpecificRecordBase> genericDatumReader = new SpecificDatumReader<SpecificRecordBase>(schema);
		BinaryDecoder decoder = DecoderFactory.get().binaryDecoder(byteArrayInputStream, null);
		SpecificRecordBase specificRecordBase = genericDatumReader.read(null, decoder);
		return specificRecordBase;
	}
}
