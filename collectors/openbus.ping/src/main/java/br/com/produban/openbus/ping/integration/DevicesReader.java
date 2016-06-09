package br.com.produban.openbus.ping.integration;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import br.com.produban.openbus.ping.collector.PingCollectorService;

@Component
public class DevicesReader {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(DevicesReader.class);

	
	@Value("${config.file.path}")
	private String configFilePath;

	@Cacheable(cacheNames = "devices")
	public List<String> readIps() {
		List<String> lines = null;
		try {
			lines = Files.readAllLines(Paths.get(configFilePath),
					Charset.forName("UTF-8"));
		} catch (IOException e) {
			LOGGER.error("Error reading file from path="+ configFilePath, e);
			System.exit(0);
		}

		return lines;
	}
}