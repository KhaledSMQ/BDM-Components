package br.com.produban.openbus.f5.collector

import java.rmi.RemoteException
import javax.xml.rpc.ServiceException

import br.com.produban.openbus.f5.webservice.PartitionService
import br.com.produban.openbus.security.exceptions.BusinessException
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Service

@Service
class MetricCollectorService @Autowired()(hostCollectorService: HostCollectorService,
                                          partitionService: PartitionService,
                                          virtualServerCollectorService: VirtualServerCollectorService,
                                          poolCollectorService: PoolCollectorService,
                                          poolMemberCollectorService: PoolMemberCollectorService) {

  private val LOGGER = LoggerFactory.getLogger(classOf[MetricCollectorService])

  @Async
  def collectMetricsByHost(ipAddress: String): Unit = {
    try {
      hostCollectorService.collect(ipAddress)

      partitionService.getPartitions(ipAddress).foreach(
        partition => {
          partitionService.activePartition(ipAddress, partition)
          virtualServerCollectorService.collect(ipAddress, partition)
          poolCollectorService.collect(ipAddress, partition)
          //poolMemberCollectorService.collect(ipAddress, partition)
        }
      )
    } catch {
      case e: RemoteException => LOGGER.error("Error trying to connect to f5 with ipAddress=" + ipAddress, e)
      case e: ServiceException => LOGGER.error("Error trying to connect to f5 with ipAddress=" + ipAddress, e)
      case e: BusinessException => LOGGER.error("Error sending to kafka metrics from ipAddress=" + ipAddress, e)
      case e: Exception => LOGGER.error("Unknow Error collecting metrics info from ipAddress=" + ipAddress, e)
    }
  }

}
