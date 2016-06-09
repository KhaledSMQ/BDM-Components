package br.com.produban.openbus.f5.webservice


import javax.xml.rpc.Call

import br.com.produban.openbus.f5.api._
import org.apache.axis.AxisProperties
import org.apache.axis.client.Stub
import org.springframework.beans.factory.annotation.Value
import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Service

import scala.collection.mutable

@Service
class PoolService {

  @Value("${f5.ws.username}")
  var userName: String = _

  @Value("${f5.ws.password}")
  var password: String = _

  @Value("${f5.ws.url}")
  var webserviceURL: String = _

  def getPools(ipAddress: String): Array[String] = {
    getService(ipAddress).get_list()
  }

  def getStatistics(ipAddress : String): LocalLBPoolPoolStatistics = {
    getService(ipAddress).get_all_statistics()
  }

  @Cacheable(cacheNames = Array("poolsMap"), key="#p0.concat('-').concat(#p1)")
  def getPoolsMap(ipAddress : String, partition : String): mutable.HashMap[String, String] = {

    val poolNames = getService(ipAddress).get_list();
    val members = getService(ipAddress).get_member(poolNames)

    val poolMap = new mutable.HashMap[String, String]

    for(i <- 0 until members.length){
      for(j <- 0 until members(i).length){
        poolMap += (members(i)(j).getAddress) -> poolNames(i)
      }
    }
    poolMap
  }

  private def getService(ipAddress: String): LocalLBPoolPortType = {
    AxisProperties.setProperty("axis.socketSecureFactory", "org.apache.axis.components.net.SunFakeTrustSocketFactory")

    val poolLocator = new LocalLBPoolLocator
    poolLocator.setLocalLBPoolPortEndpointAddress(webserviceURL.format(ipAddress))

    val poolPortType = poolLocator.getLocalLBPoolPort
    poolPortType.asInstanceOf[Stub]._setProperty(Call.USERNAME_PROPERTY, userName)
    poolPortType.asInstanceOf[Stub]._setProperty(Call.PASSWORD_PROPERTY, password)

    poolPortType
  }
}
