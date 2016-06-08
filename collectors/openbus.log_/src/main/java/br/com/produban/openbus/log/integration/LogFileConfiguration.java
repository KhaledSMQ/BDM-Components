package br.com.produban.openbus.log.integration;

import java.io.File;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LogFileConfiguration {
	
	private static final Logger logger = LoggerFactory.getLogger(LogFileConfiguration.class);
	
	@Value("${collector.log.hostname}")
	private String hostname;
	
	@Value("${collector.log.path}")
	private String path;
	
	@Value("${collector.log.path.error}")
	private String pathError;
	
	@Value("${collector.log.delimiter}")
	private String delimiter;
	
	@Value("${collector.log.enable.ingestor}")
	private String enableIngestor;
	
	private File filePath;
	private File filePathError;
	
	@Bean
	public LogFileConfiguration getLogFileConfiguration() {
		loadConfig();
		return this;
	}
	
	public void loadConfig() {
		File file = new File(path);
		File fileError = new File(pathError);
		
		if (!file.exists()) {
			logger.error("CSV Path not exists.");
		}
		
		if (!file.isDirectory()) {
			logger.error("CSV Path is not a correct path.");
		}
		
		if (!fileError.exists()) {
			logger.error("CSV Path Error not exists.");
		}
		
		if (!fileError.isDirectory()) {
			logger.error("CSV Path Error is not a correct path.");
		}
		
		if (StringUtils.isEmpty(delimiter)) {
			logger.error("Delimiter is null or empty.");
		}
		
		filePath = file;
		filePathError = fileError;
	}

	public String getHostname() {
		return hostname;
	}

	public File getFilePath() {
		return filePath;
	}

	public File getFilePathError() {
		return filePathError;
	}

	public String getDelimiter() {
		return delimiter;
	}
	
	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getPathError() {
		return pathError;
	}

	public void setPathError(String pathError) {
		this.pathError = pathError;
	}

	public String getEnableIngestor() {
		return enableIngestor;
	}

	public void setEnableIngestor(String enableIngestor) {
		this.enableIngestor = enableIngestor;
	}

	public void setHostname(String hostname) {
		this.hostname = hostname;
	}

	public void setDelimiter(String delimiter) {
		this.delimiter = delimiter;
	}

	public void setFilePath(File filePath) {
		this.filePath = filePath;
	}

	public void setFilePathError(File filePathError) {
		this.filePathError = filePathError;
	}

	public boolean isEnableIngestor() {
		if (StringUtils.isEmpty(enableIngestor)) {
			return true;
		}
		
		return enableIngestor.trim().equalsIgnoreCase("true");
	}

}
