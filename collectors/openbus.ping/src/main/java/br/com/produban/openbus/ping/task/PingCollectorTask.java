package br.com.produban.openbus.ping.task;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import br.com.produban.openbus.ping.collector.PingCollectorService;
import br.com.produban.openbus.ping.integration.DevicesReader;

@Component
public class PingCollectorTask {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(PingCollectorTask.class);

	@Autowired
	private PingCollectorService pingCollectorService;

	@Autowired
	private DevicesReader devicesReader;

	@Scheduled(cron = "${ping.cron}")
	public void run() {

		LOGGER.info("Starting ping at" + new Date());

		for (String ipAddress : devicesReader.readIps()) {
			LOGGER.info("Collecting from ip=" + ipAddress);
			pingCollectorService.collectByIp(ipAddress);
		}

		LOGGER.info("Ending at" + new Date());
	}
}
