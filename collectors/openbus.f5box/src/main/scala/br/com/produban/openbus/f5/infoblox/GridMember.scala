package br.com.produban.openbus.f5.infoblox

import com.fasterxml.jackson.annotation.{JsonProperty, JsonIgnoreProperties}

import scala.beans.BeanProperty

@JsonIgnoreProperties(ignoreUnknown = true)
class GridMember {

  @JsonProperty("host_name")
  @BeanProperty
  var hostname: String = _


}
