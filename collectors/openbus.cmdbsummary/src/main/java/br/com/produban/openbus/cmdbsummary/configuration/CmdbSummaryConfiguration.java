package br.com.produban.openbus.cmdbsummary.configuration;

import java.io.File;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

import br.com.produban.openbus.security.exceptions.BusinessException;

@Configuration
public class CmdbSummaryConfiguration {
	
	@Value("${collector.cmdbsummary.query}")
	private String query;
	
	private Config config;
	
	@Bean
	public CmdbSummaryConfiguration getCmdbSummaryConfiguration() throws BusinessException {
		File file = new File(query);
		
		if (!file.exists()) {
			throw new BusinessException("Neo4J query file not exists ");
		}
		
		System.setProperty("config.file", query);
		config = ConfigFactory.load().getConfig("openbus");
		
		return this;
	}

	public Config getConfig() {
		return config;
	}

}
