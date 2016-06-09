package br.com.openbus.infoblox.collector;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.google.api.client.repackaged.org.apache.commons.codec.binary.Base64;

import br.com.openbus.infoblox.integration.GridMaster;
import br.com.openbus.infoblox.wapi.response.GridMasterResponse;
import br.com.openbus.infoblox.wapi.response.GridMemberResponse;
import br.com.openbus.infoblox.wapi.response.SnmpSettings;

@Service
public class InfobloxApiService {
    
    @Resource
    @Qualifier("snmpSettingsPath")
    private String snmpSettingsPath;

    @Resource
    @Qualifier("membersInfoPath")
    private String membersInfoPath;

    @Cacheable(cacheNames = "snmp_settings", key = "#source.ipAddress")
    public SnmpSettings getSnmpSettings(GridMaster source) {

	String url = "https://" + source.getIpAddress() + "/wapi" + snmpSettingsPath;

	RestTemplate template = new RestTemplate();

	HttpEntity<String> request = getBasicAuthorizationRequest(source.getUsername(), source.getPassword());

	ResponseEntity<GridMasterResponse[]> responseEntity = template.exchange(url, HttpMethod.GET, request,
		GridMasterResponse[].class);

	GridMasterResponse[] gridMasterResponseList = responseEntity.getBody();
	if (gridMasterResponseList != null) {
	    if (gridMasterResponseList.length > 0)
		return gridMasterResponseList[0].getSnmpSettings();
	}

	return null;
    }

    @Cacheable(cacheNames = "grid_members", key = "#source.ipAddress")
    public GridMemberResponse[] getGridMembers(GridMaster source) {

	String url = "https://" + source.getIpAddress() + membersInfoPath;

	RestTemplate template = new RestTemplate();

	HttpEntity<String> request = getBasicAuthorizationRequest(source.getUsername(), source.getPassword());

	ResponseEntity<GridMemberResponse[]> responseEntity = template.exchange(url, HttpMethod.GET, request,
		GridMemberResponse[].class);

	return responseEntity.getBody();
    }

    private HttpEntity<String> getBasicAuthorizationRequest(String username, String password) {

	String credentials = username + ":" + password;
	String base64EncodedCredentials = new String(Base64.encodeBase64(credentials.getBytes()));

	HttpHeaders headers = new HttpHeaders();
	headers.add("Authorization", "Basic " + base64EncodedCredentials);
	headers.add("Content-Type", "application/octet-stream");

	HttpEntity<String> request = new HttpEntity<String>(headers);

	return request;
    }
}
