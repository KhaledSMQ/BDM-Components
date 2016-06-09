package br.com.produban.openbus.f5.webservice


import javax.xml.rpc.Call

import br.com.produban.openbus.f5.api._
import org.apache.axis.AxisProperties
import org.apache.axis.client.Stub
import org.springframework.beans.factory.annotation.{Autowired, Value}
import org.springframework.stereotype.Service

@Service
class PoolMemberService @Autowired()(private val poolService: PoolService) {

  @Value("${f5.ws.username}")
  var userName: String = _

  @Value("${f5.ws.password}")
  var password: String = _

  @Value("${f5.ws.url}")
  var webserviceURL: String = _

  def getStatistics(ipAddress: String): Array[LocalLBPoolMemberMemberStatistics] = {
    getService(ipAddress).get_all_statistics(poolService.getPools(ipAddress))
  }

  private def getService(ipAddress: String): LocalLBPoolMemberPortType = {
    AxisProperties.setProperty("axis.socketSecureFactory", "org.apache.axis.components.net.SunFakeTrustSocketFactory")

    val memberLocator = new LocalLBPoolMemberLocator
    memberLocator.setLocalLBPoolMemberPortEndpointAddress(webserviceURL.format(ipAddress))

    val poolMemberPortType = memberLocator.getLocalLBPoolMemberPort
    poolMemberPortType.asInstanceOf[Stub]._setProperty(Call.USERNAME_PROPERTY, userName)
    poolMemberPortType.asInstanceOf[Stub]._setProperty(Call.PASSWORD_PROPERTY, password)

    poolMemberPortType
  }
}
