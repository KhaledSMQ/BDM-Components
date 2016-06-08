package integration.bigip.response.handlers;

import integration.bigip.response.model.MemberStatistic;
import integration.bigip.response.model.VirtualServer;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

public class GetDestinationResponse extends DefaultHandler {

    private String bigIpAddress;
    private List<String> virtualServers;

    public List<VirtualServer> getResponse() {
        return response;
    }

    private final List<VirtualServer> response = new ArrayList<>();

    public GetDestinationResponse(String bigIpAddress, List<String> virtualServers) {
        this.bigIpAddress = bigIpAddress;
        this.virtualServers = virtualServers;
    }

    private boolean adressInProgress;

    private int offset = 0;

    private VirtualServer wrapper;

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        switch (qName) {
            case "item" : {
                wrapper = new VirtualServer();
                wrapper.setBigIp(bigIpAddress);
                wrapper.setName(virtualServers.get(offset));
                break;
            }
            case "address" : {
                adressInProgress = true;
                break;
            }
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        switch (qName) {
            case "item" : {
                response.add(wrapper);
                wrapper = null;
                offset++;
                break;
            }
            case "address" : {
                adressInProgress = false;
                break;
            }
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        if (adressInProgress) {
            wrapper.setDestination(String.copyValueOf(ch, start, length).trim());
        }
    }
}
