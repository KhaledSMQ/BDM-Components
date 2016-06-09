package br.com.produban.openbus.f5.infoblox

import com.google.api.client.util.Base64
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.{Bean, Configuration}
import org.springframework.http.{HttpHeaders, HttpEntity}

@Configuration
class InfobloxConfiguration {

  @Value("${infoblox.rest.username}")
  var user : String = _

  @Value("${infoblox.rest.password}")
  var password : String = _


  @Bean
  def getAuthorizationEntity : HttpEntity[String] =  {

    val credentials = user + ":" + password;
    val base64EncodedCredentials = new String(Base64.encodeBase64(credentials.getBytes()));

    val headers = new HttpHeaders();
    headers.add("Authorization", "Basic " + base64EncodedCredentials);
    headers.add("Content-Type", "application/octet-stream");

    new HttpEntity(headers);

  }
}
