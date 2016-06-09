package br.com.produban.openbus.f5.collector

import br.com.produban.openbus.f5.infoblox.InfobloxService
import br.com.produban.openbus.f5.util.ULong
import br.com.produban.openbus.f5.webservice.{VirtualServerService, PoolService, PoolMemberService}
import br.com.produban.openbus.ingestor.OpenbusDataIngestion
import org.apache.avro.Schema
import org.apache.avro.generic.GenericData.Record
import org.springframework.beans.factory.annotation.{Autowired, Value}
import org.springframework.stereotype.Service

import scala.collection.JavaConversions

@Service
class PoolMemberCollectorService @Autowired()(poolMemberService: PoolMemberService,
                                              ingestor: OpenbusDataIngestion,
                                              infobloxService: InfobloxService,
                                              poolService: PoolService,
                                              virtualServerService: VirtualServerService) {
  @Value("${kafka.f5.avro.tool.name}")
  private var toolName: String = _

  def collect(ipAddress: String, partition: String) {

    val poolMap = poolService.getPoolsMap(ipAddress, partition)
    val virtualServerMap = virtualServerService.getVirtualServerMap(ipAddress, partition)

    poolMemberService.getStatistics(ipAddress).foreach(
      poolMemberStatistic => poolMemberStatistic.getStatistics.foreach(
        poolMember => poolMember.getStatistics.foreach(
          statistic => {
            /*val record = new Record(schema)
            record.put("hostname", infobloxService.getHostName(ipAddress))
            record.put("partition", partition)
            record.put("poolMemberHost", infobloxService.getHostName(poolMember.getMember.getAddress))
            record.put("poolMemberPort", poolMember.getMember.getPort.toString)
            record.put("metricName", statistic.getType.toString)
            record.put("value", ULong.toString(statistic.getValue))
            record.put("timestamp", System.currentTimeMillis.toString)
            record.put("extraInfo", JavaConversions.mapAsJavaMap(Map()))

            if(poolMap.contains(poolMember.getMember.getAddress)){
              val poolName = poolMap(poolMember.getMember.getAddress)
              record.put("poolName", poolName)

              if(virtualServerMap.contains(poolName)){

                val virtualServer = virtualServerMap(poolName)

                record.put("virtualServerName", virtualServer.getName)
                record.put("virtualServerHost", virtualServer.getAddress)
                record.put("virtualServerPort", virtualServer.getPort.toString)
              }
            }
            ingestor.ingest(toolName, record)*/
          }
        )
      )
    )
  }
}
