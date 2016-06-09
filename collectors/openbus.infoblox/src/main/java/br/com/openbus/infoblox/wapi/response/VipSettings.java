package br.com.openbus.infoblox.wapi.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class VipSettings {

    @JsonProperty("address")
    private String address;

    @JsonProperty("gateway")
    private String gateway;

    @JsonProperty("subnet_mask")
    private String subnetMask;

    public String getAddress() {

	return address;
    }

    public void setAddress(String address) {

	this.address = address;
    }

    public String getGateway() {

	return gateway;
    }

    public void setGateway(String gateway) {

	this.gateway = gateway;
    }

    public String getSubnetMask() {

	return subnetMask;
    }

    public void setSubnetMask(String subnetMask) {

	this.subnetMask = subnetMask;
    }

}
