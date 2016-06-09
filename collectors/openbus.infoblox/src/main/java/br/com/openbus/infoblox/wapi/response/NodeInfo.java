package br.com.openbus.infoblox.wapi.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class NodeInfo {

    @JsonProperty("hwid")
    private String hardwareId;

    @JsonProperty("hwtype")
    private String hardwareType;

    @JsonProperty("service_status")
    private List<ServiceStatus> serviceStatusList;

    public String getHardwareId() {

	return hardwareId;
    }

    public void setHardwareId(String hardwareId) {

	this.hardwareId = hardwareId;
    }

    public String getHardwareType() {

	return hardwareType;
    }

    public void setHardwareType(String hardwareType) {

	this.hardwareType = hardwareType;
    }

    public List<ServiceStatus> getServiceStatusList() {

	return serviceStatusList;
    }

    public void setServiceStatusList(List<ServiceStatus> serviceStatusList) {

	this.serviceStatusList = serviceStatusList;
    }

    public ServiceStatus getNodeStatus() {

	if (serviceStatusList == null)
	    return null;

	for (ServiceStatus status : serviceStatusList) {
	    if (ServiceStatus.NODE_STATUS.equals(status.getService())) {
		return status;
	    }
	}

	return null;
    }

}
