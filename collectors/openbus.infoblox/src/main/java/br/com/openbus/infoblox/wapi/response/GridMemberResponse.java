package br.com.openbus.infoblox.wapi.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GridMemberResponse {

    @JsonProperty("_ref")
    private String ref;

    @JsonProperty("host_name")
    private String hostname;

    @JsonProperty("node_info")
    private List<NodeInfo> nodeInfoList;

    @JsonProperty("vip_setting")
    private VipSettings vipSettings;

    private String gridMaster;

    public String getHostname() {

	return hostname;
    }

    public void setHostname(String hostname) {

	this.hostname = hostname;
    }

    public String getRef() {

	return ref;
    }

    public void setRef(String ref) {

	this.ref = ref;
    }

    public List<NodeInfo> getNodeInfoList() {

	return nodeInfoList;
    }

    public NodeInfo getNodeInfo() {

	return nodeInfoList.get(0);
    }

    public void setNodeInfoList(List<NodeInfo> nodeInfoList) {

	this.nodeInfoList = nodeInfoList;
    }

    public VipSettings getVipSettings() {

	return vipSettings;
    }

    public void setVipSettings(VipSettings vipSettings) {

	this.vipSettings = vipSettings;
    }

    public String getGridMaster() {

	return gridMaster;
    }

    public void setGridMaster(String gridMaster) {

	this.gridMaster = gridMaster;
    }

}
