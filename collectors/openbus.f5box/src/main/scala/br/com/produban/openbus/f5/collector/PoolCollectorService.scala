package br.com.produban.openbus.f5.collector

import br.com.produban.openbus.f5.infoblox.InfobloxService
import br.com.produban.openbus.f5.util.ULong
import br.com.produban.openbus.f5.webservice.{VirtualServerService, PoolService}
import br.com.produban.openbus.ingestor.OpenbusDataIngestion
import org.apache.avro.Schema
import org.apache.avro.generic.GenericData.Record
import org.springframework.beans.factory.annotation.{Autowired, Value}
import org.springframework.stereotype.Service

import scala.collection.JavaConversions

@Service
class PoolCollectorService @Autowired()(poolService: PoolService,
                                        ingestor: OpenbusDataIngestion,
                                        poolSchema: Schema,
                                        infobloxService: InfobloxService,
                                        virtualServerService: VirtualServerService) {

  val toolName: String = "Pool"

  def collect(ipAddress: String, partition: String) {
    val virtualServerMap = virtualServerService.getVirtualServerMap(ipAddress, partition)

    poolService.getStatistics(ipAddress).getStatistics.foreach(
      pool => pool.getStatistics.foreach(
        statistic => {

          val record = new Record(poolSchema)

          record.put("f5hostname", infobloxService.getHostName(ipAddress))
          record.put("name", pool.getPool_name)
          record.put("partition", partition)
          record.put("metricName", statistic.getType.toString)
          record.put("value", ULong.toString(statistic.getValue))
          record.put("timestamp", System.currentTimeMillis.toString)
          record.put("extraInfo", JavaConversions.mapAsJavaMap(Map()))

          if (virtualServerMap.contains(pool.getPool_name)) {

            val virtualServer = virtualServerMap(pool.getPool_name)

            record.put("virtualServerHostname", virtualServer.getAddress)
            record.put("virtualServerName", virtualServer.getName)
            record.put("virtualServerPort", virtualServer.getPort.toString)
          }

          ingestor.ingest(toolName, record)
        }
      )
    )
  }
}
