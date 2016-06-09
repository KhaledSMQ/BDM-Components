package br.com.produban.openbus.f5.webservice


import javax.xml.rpc.Call

import br.com.produban.openbus.f5.api.{SystemSystemInfoLocator, SystemSystemInfoPortType}
import org.apache.axis.AxisProperties
import org.apache.axis.client.Stub
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

@Service
class SystemInformationService {

  @Value("${f5.ws.username}")
  var userName: String = _

  @Value("${f5.ws.password}")
  var password: String = _

  @Value("${f5.ws.url}")
  var webserviceURL: String = _

  def getUpTime(ipAddress: String) = getService(ipAddress).get_uptime()

  def getCpu(ipAddress: String) = getService(ipAddress).get_all_cpu_usage_extended_information()

  def getMemoryInformation(ipAddress: String) = getService(ipAddress).get_memory_usage_information()

  def getService(ipAddress: String): SystemSystemInfoPortType = {
    AxisProperties.setProperty("axis.socketSecureFactory", "org.apache.axis.components.net.SunFakeTrustSocketFactory")

    val systemInfoLocator = new SystemSystemInfoLocator()
    systemInfoLocator.setSystemSystemInfoPortEndpointAddress(webserviceURL.format(ipAddress))

    val systemInfoPortType = systemInfoLocator.getSystemSystemInfoPort
    systemInfoPortType.asInstanceOf[Stub]._setProperty(Call.USERNAME_PROPERTY, userName)
    systemInfoPortType.asInstanceOf[Stub]._setProperty(Call.PASSWORD_PROPERTY, password)

    systemInfoPortType
  }
}
