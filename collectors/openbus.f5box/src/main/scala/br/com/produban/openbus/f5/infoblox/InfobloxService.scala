package br.com.produban.openbus.f5.infoblox

import br.com.produban.openbus.f5.util.SSLUtil
import org.springframework.beans.factory.annotation.{Value, Autowired}
import org.springframework.cache.annotation.Cacheable
import org.springframework.http.{HttpMethod, HttpEntity, ResponseEntity}
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate

@Service
class InfobloxService @Autowired()(val httpEntity: HttpEntity[String]) {

  @Value("${infoblox.rest.url}")
  var urlBase: String = _

  @Value("${infoblox.rest.gridmaster.ip}")
  var gridmasterIp: String = _

  @Cacheable(cacheNames = Array("hostname"))
  def getHostName(ipAddress : String) : String ={
    SSLUtil.disableSslVerification()

    val restTemplate = new RestTemplate

    val response = restTemplate.exchange(urlBase.format(gridmasterIp, ipAddress), HttpMethod.GET, httpEntity,
      classOf[Array[GridMember]])

    val hostname = if (response.getBody.length > 0) Some(response.getBody()(0).getHostname) else None

    hostname.getOrElse(ipAddress)
  }
}
