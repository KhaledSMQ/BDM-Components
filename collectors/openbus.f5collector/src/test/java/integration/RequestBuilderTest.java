package integration;

import integration.bigip.request.RequestBuilder;
import integration.bigip.request.model.GetAllStatistics;
import integration.bigip.request.model.PoolNames;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class RequestBuilderTest {

    @Test
    public void testGetListRequestBuilder() {
        String requestXml = RequestBuilder.generateGetListSoapEnvelope();

        System.out.println(requestXml);
    }

    @Test
    public void testGetAllStatisticsRequestBuilder() {
        List<String> pools = new ArrayList<>();

        for (int i = 1; i <= 5; i++)
            pools.add("pool" + i);

        GetAllStatistics gas = new GetAllStatistics();
        gas.setPoolNames(new PoolNames(pools));

        System.out.println(RequestBuilder.generateGetAllStatisticsSoapEnvelope(pools));
    }

    @Test
    public void testGetPartitionListRequestBuilder() {
        System.out.println(RequestBuilder.generateGetPartitionListSoapEnvelope());
    }

    @Test
    public void testSetActivePartitionRequestBuilder() {
        System.out.println(RequestBuilder.generateSetActivePartitionSoapEnvelope("camada2"));
    }
}
