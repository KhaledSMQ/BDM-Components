package br.com.produban.openbus.mqmonitor.task;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import br.com.produban.openbus.mqmonitor.collector.MQMonitorCollector;
import br.com.produban.openbus.mqmonitor.config.MQServerConfig;

@Component
public class MQMonitorTask {

	private static final Logger LOGGER = LoggerFactory.getLogger(MQMonitorTask.class);
	
	@Resource
	@Qualifier("serverConfigList")
	private List<MQServerConfig> serverConfigList;

	@Autowired
	private MQMonitorCollector mqMonitorCollector;

	@Scheduled(cron = "${mqmonitor.cron}")
	public void collect() {
		
		LOGGER.info("Collecting MQ metrics");
		
		for (MQServerConfig cfg : serverConfigList) {
			mqMonitorCollector.collect(cfg);
		}

	}
}
