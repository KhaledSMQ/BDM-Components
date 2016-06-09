package br.com.produban.openbus.f5.collector

import br.com.produban.openbus.f5.api.{CommonStatistic, CommonVirtualServerDefinition}
import br.com.produban.openbus.f5.infoblox.InfobloxService
import br.com.produban.openbus.f5.util.ULong
import br.com.produban.openbus.f5.webservice.VirtualServerService
import br.com.produban.openbus.ingestor.OpenbusDataIngestion
import org.apache.avro.Schema
import org.apache.avro.generic.GenericData
import org.apache.avro.generic.GenericData.Record
import org.springframework.beans.factory.annotation.{Autowired, Value}
import org.springframework.stereotype.Service

import scala.collection.JavaConversions


@Service
class VirtualServerCollectorService @Autowired()(virtualServerService: VirtualServerService,
                                                 ingestor: OpenbusDataIngestion,
                                                 virtualServerSchema: Schema,
                                                 infobloxService: InfobloxService) {
  val toolName: String = "VirtualServer"

  def collect(ipAddress: String, partition: String) {
    virtualServerService.getStatistics(ipAddress).foreach(
      entry => {
        entry.getStatistics.foreach(
          virtualServerStatistics => ingestVirtualServerStatistics(ipAddress, partition, entry.getVirtual_server, virtualServerStatistics)
        )
        ingestVirtualServerStatus(ipAddress, partition, entry.getVirtual_server)
      }
    )
  }

  def ingestVirtualServerStatistics(ipAddress: String, partition: String,
                                    virtualServer: CommonVirtualServerDefinition, commonStatistic: CommonStatistic) {
    val record = new GenericData.Record(virtualServerSchema)
    record.put("f5hostname", infobloxService.getHostName(ipAddress))
    record.put("hostname", infobloxService.getHostName(virtualServer.getAddress))
    record.put("name", virtualServer.getName)
    record.put("port", virtualServer.getPort.toString)
    record.put("partition", partition)
    record.put("metricName", commonStatistic.getType.toString)
    record.put("value", ULong.toString(commonStatistic.getValue))
    record.put("timestamp", System.currentTimeMillis.toString)
    record.put("extraInfo", JavaConversions.mapAsJavaMap(Map()))
    ingestor.ingest(toolName, record)
  }

  def ingestVirtualServerStatus(ipAddress: String, partition: String, virtualServer: CommonVirtualServerDefinition) {
    val virtualServerStatus = virtualServerService.getStatus(ipAddress, virtualServer.getName)

    val enabledStatusRecord = new Record(virtualServerSchema)
    enabledStatusRecord.put("f5hostname", infobloxService.getHostName(ipAddress))
    enabledStatusRecord.put("hostname", infobloxService.getHostName(virtualServer.getAddress))
    enabledStatusRecord.put("name", virtualServer.getName)
    enabledStatusRecord.put("port", virtualServer.getPort.toString)
    enabledStatusRecord.put("partition", partition)
    enabledStatusRecord.put("metricName", VirtualServerCollectorService.ENABLED_STATUS_METRIC)
    enabledStatusRecord.put("value", virtualServerStatus.getEnabled_status.getValue)
    enabledStatusRecord.put("timestamp", System.currentTimeMillis.toString)
    enabledStatusRecord.put("extraInfo", JavaConversions.mapAsJavaMap(Map()))
    ingestor.ingest(toolName, enabledStatusRecord)

    val availabilityStatus = new Record(virtualServerSchema)
    availabilityStatus.put("f5hostname", infobloxService.getHostName(ipAddress))
    availabilityStatus.put("hostname", infobloxService.getHostName(virtualServer.getAddress))
    availabilityStatus.put("name", virtualServer.getName)
    availabilityStatus.put("port", virtualServer.getPort.toString)
    availabilityStatus.put("partition", partition)
    availabilityStatus.put("metricName", VirtualServerCollectorService.AVAILABILITY_STATUS_METRIC)
    availabilityStatus.put("value", virtualServerStatus.getAvailability_status.getValue)
    availabilityStatus.put("timestamp", System.currentTimeMillis.toString)
    availabilityStatus.put("extraInfo", JavaConversions.mapAsJavaMap(Map()))
    ingestor.ingest(toolName, availabilityStatus)
  }

  object VirtualServerCollectorService {
    val AVAILABILITY_STATUS_METRIC: String = "AVAILABILITY_STATUS"
    val ENABLED_STATUS_METRIC: String = "ENABLED_STATUS"
  }

}
