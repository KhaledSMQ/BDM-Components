package integration.bigip.services;

import exceptions.ResponseParsingException;
import integration.bigip.request.RequestBuilder;
import integration.bigip.response.ResponseParser;
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
public class PartitionService extends BaseService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PartitionService.class);

    public List<String> getPartitionList(String bigIpAddress) throws ResponseParsingException {
        HttpEntity<?> requestEntity = new HttpEntity(RequestBuilder.generateGetPartitionListSoapEnvelope(), DEFAULT_HTTP_HEADERS);
        HttpEntity<String> response = template.exchange(getServiceURL("bigip.ws.url", bigIpAddress), HttpMethod.POST, requestEntity, String.class);
        return ResponseParser.parseGetPartitionListResponse(response.getBody());
    }

    public void setActivePartition(String bigIpAddress, String partition) {
        HttpEntity<?> requestEntity = new HttpEntity(RequestBuilder.generateSetActivePartitionSoapEnvelope(partition), DEFAULT_HTTP_HEADERS);
        template.exchange(getServiceURL("bigip.ws.url", bigIpAddress), HttpMethod.POST, requestEntity, String.class);
    }
}
