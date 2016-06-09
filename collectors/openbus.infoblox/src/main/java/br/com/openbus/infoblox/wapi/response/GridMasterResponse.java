package br.com.openbus.infoblox.wapi.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GridMasterResponse {

    @JsonProperty("snmp_setting")
    private SnmpSettings snmpSettings;

    
    public SnmpSettings getSnmpSettings() {
    
        return snmpSettings;
    }

    
    public void setSnmpSettings(SnmpSettings snmpSettings) {
    
        this.snmpSettings = snmpSettings;
    }
    
    
}
