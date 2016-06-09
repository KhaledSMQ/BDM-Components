package br.com.openbus.infoblox.wapi.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SnmpSettings {

    @JsonProperty("queries_community_string")
    private String queryCommunityString;

    @JsonProperty("queries_enable")
    private String queryEnabled;

    public String getQueryCommunityString() {

	return queryCommunityString;
    }

    public void setQueryCommunityString(String queryCommunityString) {

	this.queryCommunityString = queryCommunityString;
    }

    public String getQueryEnabled() {

	return queryEnabled;
    }

    public void setQueryEnabled(String queryEnabled) {

	this.queryEnabled = queryEnabled;
    }

}
