package br.com.produban.openbus.log.collector;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.avro.generic.GenericRecord;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import br.com.produban.openbus.log.integration.LogFileConfiguration;
import br.com.produban.openbus.security.exceptions.BusinessException;

@Component
public class LogScheduler {
	
	private static final Integer POS_HEADER = 0;
	private static final String COLUMN_HEADER = "host";
	private static final Logger logger = LoggerFactory.getLogger(LogScheduler.class);
	
	@Autowired
	private LogService logService;
	
	@Autowired
	private LogFileConfiguration logFileConfiguration; 

	@Scheduled(fixedDelayString="${collector.log.timeScheduler}")
	public void run() {
		File filePath = logFileConfiguration.getFilePath();
		File[] files = filePath.listFiles();
		logger.info("***** \n\n");
		logger.info("***** [CSV PARSER] - Start reading files.");
		
		if (files == null) {
			logger.error("***** [CSV PARSER] - Object files is null.");
			return;
		}
		
		for (File file : files) {
			if (file.isDirectory()) {
				continue;
			}
			
			CSVParser csvParser = null;
			
			try {
				logger.info("***** \n\n");
				logger.info("***** [CSV PARSER] - Parsing file: " + file.getPath());
				
				csvParser = CSVParser.parse(file, Charset.defaultCharset(), CSVFormat.newFormat(logFileConfiguration.getDelimiter().charAt(0)));
				Iterator<CSVRecord> iterator = csvParser.iterator();
				
				List<String> fields = new ArrayList<>(); 
				
				int i = 0;
				while (iterator.hasNext()) {
					CSVRecord csvRecord = iterator.next();
					List<String> values = new ArrayList<>();
//					Iterator<String> iteratorFields = csvRecord.iterator();
					
					logger.debug("***** [CSV PARSER] - LINE: " + csvRecord);
					
					if (i == POS_HEADER) {
						if (!verifiyHeader(csvRecord.iterator())) {
							createCsvError(file);
							throw new BusinessException("File without header");
						}
					}
					
					splitColumns(csvRecord.iterator(), fields, values, i);
						
					if (i != POS_HEADER) {
						if (logFileConfiguration.isEnableIngestor()) {
							GenericRecord record = logService.createGenericRecord(fields, values, "" + new Date().getTime());
							logService.sendToIngestor(record);
						}
					}
					i++;
				}
			} catch (Exception ex) {
				logger.error("***** [ERRO PARSER FILE] - " + file.getName(), ex);
			} finally {
				try {
					csvParser.close();
				} catch (Exception e) {}
			}
		}
		logger.info("***** [CSV PARSER] - End reading files.");
	}
	
	private String removeSpaces(String value) {
		String val = value;
		if (!StringUtils.isEmpty(val)) {
			val = val.trim();
			val = StringUtils.remove(val, ' ');
			val = val.toLowerCase();
		}
		return val;
	}
	
	private boolean verifiyHeader(Iterator<String> iteratorFields) {
		if (iteratorFields.hasNext()) {
			String value = iteratorFields.next();
			value = removeSpaces(value);
			if (!value.trim().equals(COLUMN_HEADER)) {
				return false;
			}
		} else {
			return false;
		}
		return true;
	}
	
	private void splitColumns(Iterator<String> iteratorFields, List<String> fields, List<String> values, long positionLine) {
		Iterator<String> itFields = iteratorFields;
 		while (itFields.hasNext()) {
			String value = itFields.next();
			
			if (!StringUtils.isEmpty(value)) {
				value = value.trim();
			}
					
			if (positionLine == POS_HEADER) {
				value = removeSpaces(value);
				fields.add(value);
			} else {
				values.add(value);
			}
		}
	}
	
	private void createCsvError(File file) throws BusinessException {
		File fileDest = new File(logFileConfiguration.getFilePath().getAbsolutePath() 
				+ File.separator + "error" 
				+ File.separator + file.getName() + "." + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
		try {
			Files.copy(Paths.get(file.toURI()), Paths.get(fileDest.toURI()), StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			throw new BusinessException("Could not create file error");
		}
	}

}
