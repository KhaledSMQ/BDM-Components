package br.com.produban.openbus.f5.task

import java.util.Calendar

import br.com.produban.openbus.f5.collector.MetricCollectorService
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component

@Component
class MetricCollectorTask @Autowired()(private val metricCollectorService: MetricCollectorService,
                                       private val ipAddresses: List[String]) {

  private val LOGGER = LoggerFactory.getLogger(classOf[MetricCollectorTask])

  @Scheduled(cron = "${f5.collector.metrics.cron}")
  def init() {
    LOGGER.info("Collecting metrics at: " + Calendar.getInstance().getTime)
    ipAddresses.foreach(metricCollectorService.collectMetricsByHost)
  }
}
