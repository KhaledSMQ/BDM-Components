package br.com.produban.openbus.f5.collector

import java.rmi.RemoteException
import javax.xml.rpc.ServiceException

import br.com.produban.openbus.f5.infoblox.InfobloxService
import br.com.produban.openbus.f5.webservice.{CertificateService, PartitionService}
import br.com.produban.openbus.ingestor.OpenbusDataIngestion
import br.com.produban.openbus.security.exceptions.BusinessException
import org.apache.avro.Schema
import org.apache.avro.generic.GenericData
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.{Autowired, Value}
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Service

import scala.collection.JavaConversions.mapAsJavaMap

@Service
class CertificateCollectorService @Autowired()(private val partitionService: PartitionService,
                                               private val certificateService: CertificateService,
                                               private val certificateSchema: Schema,
                                               private val ingestor: OpenbusDataIngestion,
                                               private val infobloxService: InfobloxService) {

  private val LOGGER = LoggerFactory.getLogger(classOf[CertificateCollectorService])

  val toolName: String = "F5BoxCertificate"

  @Async
  def collectCertificateByHost(ipAddress: String) {
    try {
      partitionService.getPartitions(ipAddress).foreach(
        partition => collectByHostAndPartition(ipAddress, partition)
      )
    } catch {
      case e: RemoteException => LOGGER.error("Error trying to connect to f5 with ipAddress=" + ipAddress, e)
      case e: ServiceException => LOGGER.error("Error trying to connect to f5 with ipAddress=" + ipAddress, e)
      case e: BusinessException => LOGGER.error("Error sending to kafka metrics from ipAddress=" + ipAddress, e)
      case e : Exception => LOGGER.error("Unknow Error collecting certificates from ipAddress=" + ipAddress, e)
    }
  }

  def collectByHostAndPartition(ipAddress: String, partition: String) {
    partitionService.activePartition(ipAddress, partition)

    certificateService.getCertificates(ipAddress).foreach(
      certificate => {
        val record = new GenericData.Record(certificateSchema)
        record.put("hostname", infobloxService.getHostName(ipAddress))
        record.put("partition", partition)
        record.put("timestamp", System.currentTimeMillis.toString)
        record.put("name", certificate.getFile_name)
        record.put("commonName", certificate.getCertificate.getSubject.getCommon_name)
        record.put("metricName", "CERTIFICATE_EXPIRATION")
        record.put("value", certificate.getCertificate.getExpiration_date.toString)
        record.put("extraInfo", mapAsJavaMap(Map()))
        ingestor.ingest(toolName, record)
      }
    )
  }
}
