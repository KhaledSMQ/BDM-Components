package br.com.produban.openbus.f5.webservice


import javax.xml.rpc.Call

import br.com.produban.openbus.f5.api.{CommonStatistic, SystemStatisticsLocator, SystemStatisticsPortType}
import org.apache.axis.AxisProperties
import org.apache.axis.client.Stub
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

@Service
class SystemStatisticsService {

  @Value("${f5.ws.username}")
  private var userName: String = _

  @Value("${f5.ws.password}")
  private var password: String = _

  @Value("${f5.ws.url}")
  private var webserviceURL: String = _

  def getHostStatistics(ipAddress: String): Array[CommonStatistic] = {
    getService(ipAddress).get_all_host_statistics().getStatistics()(0).getStatistics
  }

  def getClientSSLConnections(ipAddress: String): Array[CommonStatistic] = {
    getService(ipAddress).get_client_ssl_statistics.getStatistics
  }

  def getTCPConnections(ipAddress: String): Array[CommonStatistic] = {
    getService(ipAddress).get_tcp_statistics.getStatistics
  }

  private def getService(ipAddress: String): SystemStatisticsPortType = {
    AxisProperties.setProperty("axis.socketSecureFactory", "org.apache.axis.components.net.SunFakeTrustSocketFactory")

    val systemStatisticsLocator = new SystemStatisticsLocator
    systemStatisticsLocator.setSystemStatisticsPortEndpointAddress(webserviceURL.format(ipAddress))

    val systemStatisticsPortType = systemStatisticsLocator.getSystemStatisticsPort
    systemStatisticsPortType.asInstanceOf[Stub]._setProperty(Call.USERNAME_PROPERTY, userName)
    systemStatisticsPortType.asInstanceOf[Stub]._setProperty(Call.PASSWORD_PROPERTY, password)

    systemStatisticsPortType
  }

}
