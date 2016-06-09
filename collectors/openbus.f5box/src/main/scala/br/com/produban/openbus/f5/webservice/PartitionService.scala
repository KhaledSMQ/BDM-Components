package br.com.produban.openbus.f5.webservice


import javax.xml.rpc.Call

import br.com.produban.openbus.f5.api.{ManagementPartitionAuthZPartition, ManagementPartitionLocator, ManagementPartitionPortType}
import org.apache.axis.AxisProperties
import org.apache.axis.client.Stub
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

@Service
class PartitionService {

  @Value("${f5.ws.username}")
  var userName: String = _

  @Value("${f5.ws.password}")
  var password: String = _

  @Value("${f5.ws.url}")
  var webserviceURL: String = _

  def getPartitions(ipAddress: String): Array[String] = {
    getService(ipAddress).get_partition_list().map(_.asInstanceOf[ManagementPartitionAuthZPartition].getPartition_name)
  }

  def activePartition(ipAddress: String, partition: String) {
    getService(ipAddress).set_active_partition(partition)
  }

  def getService(ipAddress: String): ManagementPartitionPortType = {
    AxisProperties.setProperty("axis.socketSecureFactory", "org.apache.axis.components.net.SunFakeTrustSocketFactory")

    val serviceLocator = new ManagementPartitionLocator
    serviceLocator.setManagementPartitionPortEndpointAddress(webserviceURL.format(ipAddress))

    val partitionPortType = serviceLocator.getManagementPartitionPort
    partitionPortType.asInstanceOf[Stub]._setProperty(Call.USERNAME_PROPERTY, userName)
    partitionPortType.asInstanceOf[Stub]._setProperty(Call.PASSWORD_PROPERTY, password)

    partitionPortType
  }
}
