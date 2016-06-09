package br.com.produban.openbus.f5.collector

import br.com.produban.openbus.f5.api.{CommonStatistic, CommonStatisticType, CommonULong64}
import br.com.produban.openbus.f5.infoblox.InfobloxService
import br.com.produban.openbus.f5.util.ULong
import br.com.produban.openbus.f5.webservice.{SystemInformationService, SystemStatisticsService}
import br.com.produban.openbus.ingestor.OpenbusDataIngestion
import org.apache.avro.Schema
import org.apache.avro.generic.GenericData
import org.springframework.beans.factory.annotation.{Autowired, Value}
import org.springframework.stereotype.Service

import scala.collection.JavaConversions.mapAsJavaMap

@Service
class HostCollectorService @Autowired()(private val systemInformationService: SystemInformationService,
                                        private val systemStatisticsService: SystemStatisticsService,
                                        private val ingestor: OpenbusDataIngestion,
                                        private val f5BoxSchema: Schema,
                                        private val infobloxService: InfobloxService) {

  val toolName: String = "F5Box"

  val hostUpTime: String = "HOST_UPTIME"

  def collect(ipAddress: String) {
    cpu(ipAddress)
    hostStatistics(ipAddress)
    connectionsNumber(ipAddress)
    uptime(ipAddress)
  }

  def cpu(ipAddress: String) {
    systemInformationService.getCpu(ipAddress).getHosts.foreach(
      host => host.getStatistics.foreach(
        cpuIdsByHost => {

          val cpuId = ULong.toString(extractStatistic(cpuIdsByHost, CommonStatisticType.STATISTIC_CPU_INFO_CPU_ID))
          val cpuUsage = ULong.toString(extractStatistic(cpuIdsByHost, CommonStatisticType.STATISTIC_CPU_INFO_USAGE_RATIO))

          val record = new GenericData.Record(f5BoxSchema)
          record.put("hostname", infobloxService.getHostName(ipAddress))
          record.put("metricName", CommonStatisticType.STATISTIC_CPU_INFO_USAGE_RATIO.toString)
          record.put("value", cpuUsage)
          record.put("timestamp", System.currentTimeMillis().toString)
          record.put("extraInfo", mapAsJavaMap(Map("cpuId" -> cpuId)))

          ingestor.ingest(toolName, record)
        }
      )
    )
  }

  private def extractStatistic(cpuIdsByHost: Array[CommonStatistic], commonStatisticType: CommonStatisticType): CommonULong64 = {
    cpuIdsByHost.find(_.getType.getValue == commonStatisticType.getValue).get.getValue
  }

  def hostStatistics(ipAddress: String) {
    systemStatisticsService.getHostStatistics(ipAddress).foreach(
      ingestCommomStatistic(ipAddress, _)
    )
  }

  def ingestCommomStatistic(ipAddress: String, commonStatistic: CommonStatistic) {
    val record = new GenericData.Record(f5BoxSchema)
    record.put("hostname", infobloxService.getHostName(ipAddress))
    record.put("metricName", commonStatistic.getType.toString)
    record.put("value", ULong.toString(commonStatistic.getValue))
    record.put("timestamp", System.currentTimeMillis().toString)
    record.put("extraInfo", mapAsJavaMap(Map()))
    ingestor.ingest(toolName, record)
  }

  def connectionsNumber(ipAddress: String) {
    systemStatisticsService.getClientSSLConnections(ipAddress).foreach(
      ingestCommomStatistic(ipAddress, _)
    )

    systemStatisticsService.getTCPConnections(ipAddress).foreach(
      ingestCommomStatistic(ipAddress, _)
    )
  }

  def uptime(ipAddress: String) {
    val record = new GenericData.Record(f5BoxSchema)
    record.put("hostname", infobloxService.getHostName(ipAddress))
    record.put("metricName", hostUpTime)
    record.put("value", systemInformationService.getUpTime(ipAddress).toString)
    record.put("timestamp", System.currentTimeMillis().toString)
    record.put("extraInfo", mapAsJavaMap(Map()))
    ingestor.ingest(toolName, record)
  }
}
