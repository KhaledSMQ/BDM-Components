package br.com.produban.openbus.log.collector;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.avro.Schema;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.util.Utf8;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.produban.openbus.ingestor.OpenbusDataIngestion;
import br.com.produban.openbus.log.integration.LogFileConfiguration;
import br.com.produban.openbus.security.exceptions.BusinessException;
import br.com.produban.openbus.security.exceptions.InfraException;
import freemarker.template.Configuration;
import freemarker.template.Template;

@Service
public class LogService {
	
	private static final Logger logger = LoggerFactory.getLogger(LogService.class);

	@Autowired
	private OpenbusDataIngestion ingestor;

	@Autowired
	private Schema logSchema;
	
	@Autowired
	private LogFileConfiguration logFileConfiguration;
	
	public GenericRecord createGenericRecord(List<String> fields, List<String> values, String timeStamp) throws BusinessException {
		GenericRecord record = null;
		try {
			record = new GenericData.Record(logSchema);
			Map<Utf8, Utf8> logInformation = new HashMap<>();
			
			int position = 0;
			for (String key : fields) {
				logInformation.put(new Utf8(key), new Utf8(values.get(position)));
				position++;
			}
			
			logger.info("***** [LOG INFORMATION] - " + logInformation);
			
			record.put("hostname", logFileConfiguration.getHostname());
			record.put("timestamp", timeStamp); 
			record.put("logInformation", logInformation);
			record.put("extraInfo", new HashMap<String, String>());
		} catch (Exception ex) {
			logger.info("***** [ERROR CREATE GENERICRECORD] - " + ex.getMessage(), ex);
			throw new BusinessException(ex.getMessage());
		}
		return record;
	}
	
	public void sendToIngestor(GenericRecord record) throws Exception {
		logger.info("***** [GENERIC RECORD] - " + record);
		ingestor.ingest("Log", record);
	}
	
	public String generateConfigView(HashMap<String, Object> values) throws InfraException {
		StringBuffer body = new StringBuffer();
		Configuration configuration = null;
		Template template = null;
		
		try {
			File file = new File("." + File.separator + "config");
			configuration = new Configuration(Configuration.VERSION_2_3_0);
			configuration.setDirectoryForTemplateLoading(file);
			template = configuration.getTemplate("config.html");

			ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
			Writer writer = new OutputStreamWriter(arrayOutputStream);
			template.process(values, writer);
			
			body.append(new String(arrayOutputStream.toByteArray()));
		} catch (Exception ex) {
			throw new InfraException(ex);
		}
		
		return body.toString();
	}

}
