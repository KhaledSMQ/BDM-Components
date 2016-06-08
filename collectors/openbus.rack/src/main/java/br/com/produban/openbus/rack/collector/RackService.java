package br.com.produban.openbus.rack.collector;

import java.util.HashMap;
import java.util.Map;

import org.apache.avro.Schema;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.produban.openbus.ingestor.OpenbusDataIngestion;

@Service
public class RackService {

	private static final Logger logger = LoggerFactory.getLogger(RackService.class);

	@Autowired
	private OpenbusDataIngestion ingestor;

	@Autowired
	private Schema rackSchema;

	public void sendToIngestor(Map<String, Object> map) throws Exception {
		GenericRecord record = new GenericData.Record(rackSchema);

		map.put("timestamp", String.valueOf(System.currentTimeMillis()));
		
		for (String key : map.keySet()) {
			record.put(key, map.get(key));			
		}
		
		record.put("extraInfo", new HashMap<String, String>());

		logger.info("***** [GENERIC RECORD] - " + record);
		ingestor.ingest("Rack", record);
	}

}
