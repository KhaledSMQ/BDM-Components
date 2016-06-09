package br.com.openbus.infoblox.integration;

import java.io.File;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

@Configuration
public class ApplicationConfig {
    
    @Value("${config.file.path}")
    private String configFilePath;
    
    @Bean
    public Config config() {
	
	if(StringUtils.isEmpty(configFilePath)) {
	    return ConfigFactory.load();
	}
	return ConfigFactory.parseFile(new File(configFilePath));
    }
    
}
