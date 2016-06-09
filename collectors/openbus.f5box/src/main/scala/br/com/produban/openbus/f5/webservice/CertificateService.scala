package br.com.produban.openbus.f5.webservice


import javax.xml.rpc.Call

import br.com.produban.openbus.f5.api.{ManagementKeyCertificateCertificateInformation, ManagementKeyCertificateLocator, ManagementKeyCertificateManagementModeType, ManagementKeyCertificatePortType}
import org.apache.axis.AxisProperties
import org.apache.axis.client.Stub
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

@Service
class CertificateService {

  @Value("${f5.ws.username}")
  var userName: String = _

  @Value("${f5.ws.password}")
  var password: String = _

  @Value("${f5.ws.url}")
  var webserviceURL: String = _

  def getCertificates(ipAddress: String): Array[ManagementKeyCertificateCertificateInformation] = {
    getService(ipAddress).get_certificate_list(ManagementKeyCertificateManagementModeType.MANAGEMENT_MODE_DEFAULT)
  }

  def getService(ipAddress: String): ManagementKeyCertificatePortType = {
    AxisProperties.setProperty("axis.socketSecureFactory", "org.apache.axis.components.net.SunFakeTrustSocketFactory")

    val serviceLocator = new ManagementKeyCertificateLocator
    serviceLocator.setManagementKeyCertificatePortEndpointAddress(webserviceURL.format(ipAddress))

    val managementKeyCertificatePortType = serviceLocator.getManagementKeyCertificatePort
    managementKeyCertificatePortType.asInstanceOf[Stub]._setProperty(Call.USERNAME_PROPERTY, userName)
    managementKeyCertificatePortType.asInstanceOf[Stub]._setProperty(Call.PASSWORD_PROPERTY, password)

    managementKeyCertificatePortType
  }
}
