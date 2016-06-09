package br.com.produban.openbus.f5.task

import java.util.Calendar

import br.com.produban.openbus.f5.collector.CertificateCollectorService
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component

@Component
class CertificateCollectorTask @Autowired()(private val ipAddresses: List[String],
                                            private val certificateCollectorService: CertificateCollectorService) {

  private val LOGGER = LoggerFactory.getLogger(classOf[CertificateCollectorTask])

  @Scheduled(cron = " ${f5.collector.certificate.cron}")
  def init() {
    LOGGER.info("Collecting certificate at: " + Calendar.getInstance().getTime)
    ipAddresses.foreach(certificateCollectorService.collectCertificateByHost)
  }
}
