package br.com.openbus.infoblox.collector;

import java.util.Calendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class InfobloxCollectorTask {

    private static final Logger LOGGER = LoggerFactory.getLogger(InfobloxCollectorTask.class);

    @Autowired
    private InfobloxService service;

    @Scheduled(cron = "${infoblox.cron.metrics}")
    public void run() {

	LOGGER.info("Collecting metrics at : " + Calendar.getInstance().getTime());
	service.collect();
    }

}
