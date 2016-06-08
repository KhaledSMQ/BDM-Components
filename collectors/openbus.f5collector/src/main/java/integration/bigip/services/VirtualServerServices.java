package integration.bigip.services;

import exceptions.ResponseParsingException;
import integration.bigip.request.RequestBuilder;
import integration.bigip.response.ResponseParser;
import integration.bigip.response.model.VirtualServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class VirtualServerServices extends BaseService {

    private static final Logger LOGGER = LoggerFactory.getLogger(VirtualServerServices.class);

    private List<String> getList(String bigIpAddress) throws ResponseParsingException {
        HttpEntity<?> requestEntity = new HttpEntity(RequestBuilder.generateVirtualServerGetListSoapEnvelope(),
                                                     DEFAULT_HTTP_HEADERS);
        HttpEntity<String> response = template.exchange(getServiceURL("bigip.ws.url", bigIpAddress),
                                                        HttpMethod.POST,
                                                        requestEntity,
                                                        String.class);
        return ResponseParser.parseGetListResponse(response.getBody());
    }

    private List<VirtualServer> getDestination(String bigIpAddress,
                                               List<String> virtualServers) throws ResponseParsingException  {
        HttpEntity<?> requestEntity = new HttpEntity(RequestBuilder.generateGetDestinationSoapEnvelope(virtualServers),
                DEFAULT_HTTP_HEADERS);
        HttpEntity<String> response = template.exchange(getServiceURL("bigip.ws.url", bigIpAddress),
                HttpMethod.POST,
                requestEntity,
                String.class);
        return ResponseParser.parseGetDestinationResponse(response.getBody(), bigIpAddress, virtualServers);
    }

    private List<String> getDefaultPoolName(String bigIpAddress,
                                                   List<String> virtualServers) throws ResponseParsingException {
        HttpEntity<?> requestEntity = new HttpEntity(RequestBuilder.generateGetDefaultPoolNameSoapEnvelope(virtualServers),
                DEFAULT_HTTP_HEADERS);
        HttpEntity<String> response = template.exchange(getServiceURL("bigip.ws.url", bigIpAddress),
                HttpMethod.POST,
                requestEntity,
                String.class);
        return ResponseParser.parseGetListResponse(response.getBody());
    }

    public List<VirtualServer> getVirtualServers(String bigIpAddress) throws ResponseParsingException {

        List<String> names = getList(bigIpAddress);
        List<VirtualServer> servers = getDestination(bigIpAddress, names);
        List<String> defaultPoolNames = getDefaultPoolName(bigIpAddress, names);

        for (int i = 0; i < servers.size(); i++) {
            String defaultPoolName = defaultPoolNames.get(i);
            servers.get(i).setDefaultPoolName(defaultPoolName);
        }

        return servers;
    }
}
