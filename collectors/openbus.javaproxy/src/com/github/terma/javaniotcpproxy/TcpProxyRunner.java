/*
Copyright 2012 Artem Stasuk

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
 */

package com.github.terma.javaniotcpproxy;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.nio.ByteBuffer;
import java.util.List;
import java.util.Properties;

import org.apache.log4j.xml.DOMConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.weakref.jmx.MBeanExporter;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

import br.com.produban.openbus.integration.IntegrationQueue;
import br.com.produban.openbus.integration.KafkaIntegrationListener;
import br.com.produban.openbus.integration.ZabbixIntegrationListener;
import br.com.produban.openbus.model.avro.ZabbixAgentData;
import br.com.produban.openbus.utils.config.ApplicationConfig;
import br.com.produban.openbus.utils.config.ApplicationConfigParser;
import br.com.produban.openbus.utils.config.PublisherConfig;
import br.com.produban.openbus.utils.config.PublisherConfigParser;

public class TcpProxyRunner {

	private final static Logger LOGGER = LoggerFactory.getLogger(TcpProxyRunner.class);

	private static ApplicationConfig applicationConfig;
	private static PublisherConfig publisherConfig;

	
	public static synchronized ApplicationConfig getApplicationConfig() {
		return applicationConfig;
	}

	public static synchronized PublisherConfig getPublisherConfig() {
		return publisherConfig;
	}

	public static MBeanExporter exporter = new MBeanExporter(ManagementFactory.getPlatformMBeanServer());

	public static void main(final String[] args) {
		if (args.length != 4) {
			System.err.println("Please specify path to proxy and publisher config files!");
			System.exit(1);
		}

		String configFileLocation = args[3];
		
		if (configFileLocation == null || configFileLocation.isEmpty()){
			throw new IllegalStateException("Config file location is mandatory!");
		}
        else if (!(new File(configFileLocation).exists())){
        	throw new IllegalStateException("Config file location is mandatory!");
        }

        System.setProperty("config.file", configFileLocation);
		
        final Config appConf = ConfigFactory.load();
		final Properties proxyProperties = new Properties();
		final Properties publisherProperties = new Properties();
		
		try {
			proxyProperties.load(new FileInputStream(args[0]));
			publisherProperties.load(new FileInputStream(args[1]));
			DOMConfigurator.configure(args[2]);
		} catch (IOException exception) {
			LOGGER.error("Can't load properties from " + args[0] + " and " + args[1], exception);
			System.exit(1);
		}

		final List<TcpProxyConfig> configs = TcpProxyConfigParser.parse(proxyProperties);
//		if (LOGGER.isLoggable(Level.INFO))
			LOGGER.info("Starting TcpProxy with " + configs.size() + " connectors");

		final int cores = Runtime.getRuntime().availableProcessors();
//		if (LOGGER.isLoggable(Level.INFO))
			LOGGER.info("TcpProxy detected " + cores + " core" + (cores > 1 ? "s" : ""));

//		final int workerCount = Math.max(cores / configs.size(), 1);

		synchronized (TcpProxyRunner.class) {
			try {
				applicationConfig = ApplicationConfigParser.parser(appConf);
				publisherConfig = PublisherConfigParser.parse(publisherProperties);
			} catch (IOException exception) {
				LOGGER.error("Can't build configs files");
				System.exit(1);
			}
		}

		IntegrationQueue<ByteBuffer> kafkaIntegrationQueue = new IntegrationQueue<>();
		IntegrationQueue<ZabbixAgentData> zabbixIntegrationQueue = new IntegrationQueue<>();

		exporter.export("openbus.javaproxy:type=IntegrationQueue,name=kafkaIntegrationQueue",kafkaIntegrationQueue);
		exporter.export("openbus.javaproxy:type=IntegrationQueue,name=zabbixIntegrationQueue",zabbixIntegrationQueue);

		for (final TcpProxyConfig config : configs) {
			// TODO
//			config.setWorkerCount(workerCount);
			if (LOGGER.isInfoEnabled())
				LOGGER.info("TcpProxy will use " + config.getWorkerCount() * configs.size() + " workers per connector");

			for (int c = 0; c < getPublisherConfig().getListeningThreads(); c++) {
				Thread kafkaIntegrationListener = new Thread(new KafkaIntegrationListener(kafkaIntegrationQueue, zabbixIntegrationQueue, config.getTopicName()));
				kafkaIntegrationListener.setName("Kafka Integration Listener #" + c);
				kafkaIntegrationListener.start();
			}

			Thread zabbixIntegrationListener = new Thread(new ZabbixIntegrationListener(zabbixIntegrationQueue,config));
			zabbixIntegrationListener.setName("Zabbix Integration Listener");
			zabbixIntegrationListener.start();

			final TcpProxy proxy = new TcpProxy(config, kafkaIntegrationQueue);
			proxy.start();

			Runtime.getRuntime().addShutdownHook(new Thread() {
				@Override
				public void run() {
					proxy.shutdown();
				}
			});
		}

//		if (LOGGER.isLoggable(Level.INFO))
		LOGGER.info("Publisher will use " + publisherConfig.getListeningThreads() + " thread listeners");

//		if (LOGGER.isLoggable(Level.INFO))
		LOGGER.info("TcpProxy started");
	}

}
