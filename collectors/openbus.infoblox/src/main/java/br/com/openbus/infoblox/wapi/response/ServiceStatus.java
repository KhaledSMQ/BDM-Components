package br.com.openbus.infoblox.wapi.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ServiceStatus {

    public static final String NODE_STATUS = "NODE_STATUS";
    public static final String DESC_RUNNING = "Running";
    public static final String DESC_OFFLINE = "Offline";
    public static final String STATUS_WORKING = "WORKING";
    public static final String STATUS_FAILED = "FAILED";

    @JsonProperty("description")
    private String description;

    @JsonProperty("service")
    private String service;

    @JsonProperty("status")
    private String status;

    public String getDescription() {

	return description;
    }

    public void setDescription(String description) {

	this.description = description;
    }

    public String getService() {

	return service;
    }

    public void setService(String service) {

	this.service = service;
    }

    public String getStatus() {

	return status;
    }

    public void setStatus(String status) {

	this.status = status;
    }

    public boolean isRunning() {

	return ServiceStatus.DESC_RUNNING.equals(this.description);
    }

    public boolean isWorking() {

	return ServiceStatus.STATUS_WORKING.equals(this.status);
    }
}
