package br.com.produban.openbus.hypervisor.config;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

import com.typesafe.config.ConfigFactory;

@Configuration
public class DevicesConfiguration {

	@Value("${config.file.path}")
	private String configFilePath;

	@Bean
	public List<String> ipAddresses() {

		if (StringUtils.isEmpty(configFilePath)) {
			return ConfigFactory.load("devices").getStringList("devices.ipAddresses");
		}

		return ConfigFactory.parseFile(new File(configFilePath)).getStringList("devices.ipAddresses");
	}
}
