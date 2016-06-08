package br.com.produban.openbus.sql2kafka.sender;

import java.util.HashMap;

import br.com.produban.openbus.avro.FingerprintUtils;
import org.apache.avro.Schema;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.specific.SpecificRecordBase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.openbus.publisher.kafka.KafkaAvroPublisher;
import br.com.produban.openbus.ingestor.OpenbusDataIngestion;
import br.com.produban.openbus.ingestor.OpenbusIngestion;
import br.com.produban.openbus.security.exceptions.BusinessException;
import br.com.produban.openbus.sql2kafka.normalizer.SQLNormalizer;
import br.com.produban.openbus.sql2kafka.util.SchemaUtil;

public class KafkaSender extends SenderJob {

	private Logger LOG = LoggerFactory.getLogger(this.getClass());
	
	private KafkaAvroPublisher<GenericRecord> publisher;

	private static Schema schema;
	private OpenbusIngestion ingestion;
	
	@SuppressWarnings("unchecked")
	@Override
	public void send() {
		
		if (this.publisher == null) {
			this.publisher = new KafkaAvroPublisher<GenericRecord>(sql2KafkaProperties.getKafkaBrokerList(),
					sql2KafkaProperties.isKafkaRequiredAcks(), sql2KafkaProperties.isKafkaAsync(),
					sql2KafkaProperties.getKafkaBatchNumMessages());
		}
		if (this.ingestion == null) {
			this.ingestion = OpenbusDataIngestion.OpenbusDataIngestionBuilder.aIngestionConfiguration()
					.withKafkaAvroPublisher(publisher).withSuccessTopic(sql2KafkaProperties.getKafkaSuccessTopic())
					.withFailureTopic(sql2KafkaProperties.getKafkaFailureTopic()).withNormalizer(new SQLNormalizer()).build();
		}
		if (this.schema == null) {
			this.schema = SchemaUtil.getInstance(sql2KafkaProperties.getIngestorSchemaFile()).getSchema();
			LOG.info("Schema ID: "+ String.valueOf(FingerprintUtils.crc32AsInt(collectProperties.getTool(), this.schema)));
			LOG.info("Schema File: "+ sql2KafkaProperties.getIngestorSchemaFile());
			LOG.info("Schema File content: "+ this.schema.toString());
		}

		for (SpecificRecordBase specificRecordBase : sendQueues.getSpecificRecordBaseList()) {
			try {
				
				GenericRecord genericRecord = new GenericData.Record(this.schema);
				
				if(specificRecordBase.get("timestamp") == null){
					specificRecordBase.put("timestamp", System.currentTimeMillis() + "");
				}
				
				for (Schema.Field field : specificRecordBase.getSchema().getFields()) {
					if(genericRecord.getSchema().getField(field.name()) != null){
						
						if("timestamp".equals(field.name())){
							if(specificRecordBase.get(field.name()) != null){
								if(specificRecordBase.get(field.name()).toString().length() < 13){
									genericRecord.put(field.name(), specificRecordBase.get(field.name()) + "000");
								}
								else {
									genericRecord.put(field.name(), specificRecordBase.get(field.name()));
								}
							}
						}
						else {
							genericRecord.put(field.name(), specificRecordBase.get(field.name()));
						}
					}
				}

				genericRecord.put("extraInfo", new HashMap<String, String>());
				
				LOG.info("Sending message to ingestor: "+ " - "+ collectProperties.getTool() +" / "+ genericRecord.toString());

				ingestion.ingest(collectProperties.getTool(), genericRecord);
				
			} catch (BusinessException e) {
				LOG.error(e.getMessage(), e);
			}
		}
	}
}
