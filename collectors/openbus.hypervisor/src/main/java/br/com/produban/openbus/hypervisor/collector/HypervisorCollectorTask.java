package br.com.produban.openbus.hypervisor.collector;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class HypervisorCollectorTask {

	@Autowired
	private HypervisorService hypervisorService;

	@Scheduled(cron = "0 * * * * *")
	public void run() {
		hypervisorService.collect();
	}
}
