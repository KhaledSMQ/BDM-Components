package br.com.produban.openbus.hypervisor.task;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import br.com.produban.openbus.hypervisor.collector.HypervisorMetricsCollector;

@Component
public class HypervisorMetricsTask {

    private static final Logger LOGGER = LoggerFactory.getLogger(HypervisorMetricsCollector.class);

    @Autowired
    private HypervisorMetricsCollector hypervisorMetricsCollector;

    @Resource
    @Qualifier("ipAddresses")
    private List<String> ipAddresses;

    @Scheduled(cron = "${vcenter.metrics.cron}")
    public void run() {

	for (String ipAddress : ipAddresses) {
	    LOGGER.info("Collecting Hypervisor Metrics : " + ipAddress);
	    hypervisorMetricsCollector.collectMetricsByIpAddress(ipAddress);
	}
    }
}
