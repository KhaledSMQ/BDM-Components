package integration;

import integration.bigip.response.handlers.GetAllStatisticsResponseHandler;
import integration.bigip.response.handlers.GetListResponseHandler;
import integration.bigip.response.handlers.GetPartitionListResponseHandler;
import org.junit.Test;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class ResponseParserTest {

    @Test
    public void testParseGetPartitionListResponse() throws ParserConfigurationException, SAXException, IOException {
        SAXParserFactory parserFactor = SAXParserFactory.newInstance();
        SAXParser parser = parserFactor.newSAXParser();
        GetPartitionListResponseHandler handler = new GetPartitionListResponseHandler();
        File file = new File(Paths.get("").toAbsolutePath() +"/src/test/resources/out_get_partition_list.xml");
        parser.parse(file, handler);

        System.out.println("Partitions: " + handler.getResponse());
    }

    @Test
    public void testParseGetListResponse() throws ParserConfigurationException, SAXException, IOException {
        SAXParserFactory parserFactor = SAXParserFactory.newInstance();
        SAXParser parser = parserFactor.newSAXParser();
        GetListResponseHandler handler = new GetListResponseHandler();
        File file = new File(Paths.get("").toAbsolutePath() +"/src/test/resources/out_get_list.xml");
        parser.parse(file, handler);

        System.out.println("Number of pools: " + handler.getResponse().size());
    }

    @Test
    public void testParseGetAllStatisticsResponse() throws ParserConfigurationException, SAXException, IOException {
        List<String> pools = Arrays.asList(new String[]{"pool_cust.santanderbr.corp_10045", "pool_santanderaldia", "gda-adsm-santanderbr.corp"});
        List<String> statisticWhileList = Arrays.asList(new String[] {"STATISTIC_SERVER_SIDE_CURRENT_CONNECTIONS"});

        SAXParserFactory parserFactor = SAXParserFactory.newInstance();
        SAXParser parser = parserFactor.newSAXParser();
        GetAllStatisticsResponseHandler handler = new GetAllStatisticsResponseHandler(pools,statisticWhileList);
        File file = new File(Paths.get("").toAbsolutePath() +"/src/test/resources/out_get_all_statistics.xml");
        parser.parse(file, handler);

        System.out.println("Number of pools: " + handler.getResponse().size());
    }

}
