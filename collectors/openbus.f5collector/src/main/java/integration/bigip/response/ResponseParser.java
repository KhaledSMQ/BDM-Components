package integration.bigip.response;

import exceptions.ResponseParsingException;
import integration.bigip.response.handlers.GetAllStatisticsResponseHandler;
import integration.bigip.response.handlers.GetDestinationResponse;
import integration.bigip.response.handlers.GetListResponseHandler;
import integration.bigip.response.handlers.GetPartitionListResponseHandler;
import integration.bigip.response.model.Pool;
import integration.bigip.response.model.VirtualServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

public class ResponseParser {

    private static final Logger LOGGER = LoggerFactory.getLogger(ResponseParser.class);

    public static List<String> parseGetPartitionListResponse(String xml) throws ResponseParsingException {
        GetPartitionListResponseHandler handler = new GetPartitionListResponseHandler();
        fireParser(xml,handler);
        return handler.getResponse();
    }

    public static List<String> parseGetListResponse(String xml) throws ResponseParsingException {
        GetListResponseHandler handler = new GetListResponseHandler();
        fireParser(xml,handler);
        return handler.getResponse();
    }

    public static List<VirtualServer> parseGetDestinationResponse(String xml,
                                                                  String bigIpAddress,
                                                                  List<String> virtualServers)
                                                                  throws ResponseParsingException {
        GetDestinationResponse handler = new GetDestinationResponse(bigIpAddress, virtualServers);
        fireParser(xml,handler);
        return handler.getResponse();
    }

    public static List<Pool> parseGetAllStatisticsResponse(String xml,
                                                           List<String> attributeWhiteList,
                                                           List<String> pools) throws ResponseParsingException {
        GetAllStatisticsResponseHandler handler = new GetAllStatisticsResponseHandler(pools,attributeWhiteList);
        fireParser(xml,handler);
        return handler.getResponse();
    }

    private static void fireParser(String xml, DefaultHandler handler) throws ResponseParsingException {
        SAXParser parser = null;
        try {
            parser = SAXParserFactory.newInstance().newSAXParser();
            parser.parse(new ByteArrayInputStream(xml.getBytes()), handler);
        } catch (ParserConfigurationException e) {
            throw new ResponseParsingException("Fail to parse response XML",e);
        } catch (SAXException e) {
            throw new ResponseParsingException("Fail to parse response XML",e);
        } catch (IOException e) {
            throw new ResponseParsingException("Fail to parse response XML",e);
        } catch (NullPointerException e) {
            throw new ResponseParsingException("Fail to parse response XML",e);
        }
    }
}