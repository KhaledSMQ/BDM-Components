package integration.bigip.services;

import exceptions.ResponseParsingException;
import integration.bigip.request.RequestBuilder;
import integration.bigip.response.ResponseParser;
import integration.bigip.response.model.Pool;
import integration.bigip.response.model.PoolMember;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class PoolMemberService extends BaseService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PoolMemberService.class);

    public List<Pool> getAllStatisticsFrom(String bigIpAddress, List<String> pools) throws ResponseParsingException {
        HttpEntity<?> requestEntity = new HttpEntity(RequestBuilder.generateGetAllStatisticsSoapEnvelope(pools),
                DEFAULT_HTTP_HEADERS);
        HttpEntity<String> response = template.exchange(getServiceURL("bigip.ws.url", bigIpAddress),
                HttpMethod.POST,
                requestEntity,
                String.class);
        return ResponseParser.parseGetAllStatisticsResponse(response.getBody(),
                Arrays.asList(new String[]{"STATISTIC_SERVER_SIDE_CURRENT_CONNECTIONS"}), pools);
    }

}
