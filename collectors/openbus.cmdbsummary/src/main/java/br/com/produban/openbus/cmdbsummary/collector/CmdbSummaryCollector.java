package br.com.produban.openbus.cmdbsummary.collector;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import br.com.produban.openbus.security.exceptions.BusinessException;

@Component
public class CmdbSummaryCollector {
	
	private static final Logger logger = LoggerFactory.getLogger(CmdbSummaryCollector.class);
	
	@Autowired
	private CmdbSummaryService cmdbSummaryService;
	
	@Scheduled(fixedDelayString="${collector.cmdbsummary.timeScheduler}")
	public void run() {
		logger.info("***** [CMDB] - CMDB Summary Started");
		
		try {
			cmdbSummaryService.sendIngestor(cmdbSummaryService.getTotalPlatformByVdc());
			cmdbSummaryService.sendIngestor(cmdbSummaryService.getTotalPlatformByVdcDeployed());
			cmdbSummaryService.sendIngestor(cmdbSummaryService.getTotalPlatformByVdcBeingAssembled());
			cmdbSummaryService.sendIngestor(cmdbSummaryService.getTotalPlatformByVdcDown());
			cmdbSummaryService.sendIngestor(cmdbSummaryService.getTotalPlatformByVdcReserved());
		} catch (BusinessException ex) {
			logger.error("***** [ERROR_CMDB] - " + ex.getMessage(), ex);
		}
		
		logger.info("***** [CMDB] - CMDB Summary Finished");
	}
	
}
