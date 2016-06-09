package br.com.produban.openbus.mqmonitor.config;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

@Configuration
public class DevicesConfiguration {

	@Value("${config.file.path}")
	private String configFilePath;

	@Bean(name = "serverConfigList")
	public List<MQServerConfig> serverConfigList() throws Exception {

		Config config = null;

		if (StringUtils.isEmpty(configFilePath))
			config = ConfigFactory.load("devices");
		else
			config = ConfigFactory.parseFile(new File(configFilePath));

		List<MQServerConfig> configs = new ArrayList<MQServerConfig>();

		List<? extends Config> servers = config.getConfigList("devices");

		for (Config cfg : servers) {

			MQServerConfig serverConfigInfo = new MQServerConfig();
			serverConfigInfo.setHostname(cfg.getString("host"));

			List<String> ports = new ArrayList<String>();

			for (String port : cfg.getStringList("ports")) {
				ports.add(port);
			}

			serverConfigInfo.setPorts(ports);

			configs.add(serverConfigInfo);
		}

		return configs;
	}
}
