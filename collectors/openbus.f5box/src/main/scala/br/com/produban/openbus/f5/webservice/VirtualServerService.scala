package br.com.produban.openbus.f5.webservice


import javax.xml.rpc.Call

import br.com.produban.openbus.f5.api._
import org.apache.axis.AxisProperties
import org.apache.axis.client.Stub
import org.springframework.beans.factory.annotation.Value
import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Service

import scala.collection.mutable
import scala.collection.mutable.Map

@Service
class VirtualServerService {

  @Value("${f5.ws.username}")
  var userName: String = _

  @Value("${f5.ws.password}")
  var password: String = _

  @Value("${f5.ws.url}")
  var webserviceURL: String = _

  def getStatistics(ipAddress: String): Array[LocalLBVirtualServerVirtualServerStatisticEntry] = {
    getService(ipAddress).get_all_statistics().getStatistics
  }

  def getStatus(ipAddress: String, virtualServer: String): LocalLBObjectStatus = {
    getService(ipAddress).get_object_status(Array(virtualServer))(0)
  }

  @Cacheable(cacheNames = Array("virtualServers"), key="#p0.concat('-').concat(#p1)")
  def getVirtualServerMap(ipAddress: String, partition: String): Map[String, CommonVirtualServerDefinition] = {
    val serverNames = getService(ipAddress).get_list()
    val virtualServers = getService(ipAddress).get_destination(serverNames)
    val poolNames = getService(ipAddress).get_default_pool_name(serverNames)


    val virtualServerByPoolNameMap = new mutable.HashMap[String, CommonVirtualServerDefinition]

    for( i <- 0 until serverNames.length){
      if( !poolNames(i).isEmpty ) {

        val virtualServerDefinition = new CommonVirtualServerDefinition()
        virtualServerDefinition.setAddress(virtualServers(i).getAddress)
        virtualServerDefinition.setPort(virtualServers(i).getPort)
        virtualServerDefinition.setName(serverNames(i))

        virtualServerByPoolNameMap += poolNames(i) -> virtualServerDefinition
      }
    }
    virtualServerByPoolNameMap
  }


  private def getService(ipAddress: String): LocalLBVirtualServerPortType = {
    AxisProperties.setProperty("axis.socketSecureFactory", "org.apache.axis.components.net.SunFakeTrustSocketFactory")

    val virtualServerLocator = new LocalLBVirtualServerLocator()
    virtualServerLocator.setLocalLBVirtualServerPortEndpointAddress(webserviceURL.format(ipAddress))

    val virtualServerPortType = virtualServerLocator.getLocalLBVirtualServerPort
    virtualServerPortType.asInstanceOf[Stub]._setProperty(Call.USERNAME_PROPERTY, userName)
    virtualServerPortType.asInstanceOf[Stub]._setProperty(Call.PASSWORD_PROPERTY, password)

    virtualServerPortType
  }
}
