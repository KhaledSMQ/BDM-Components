package integration.bigip.response.handlers;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

public class GetPartitionListResponseHandler extends DefaultHandler {

    private final List<String> response = new ArrayList<>();

    public List<String> getResponse() {
        return response;
    }

    private boolean partitionInProgress = false;

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes)
            throws SAXException {

        switch (qName) {
            case "partition_name" : {
                partitionInProgress = true;
                break;
            }

        }
    }


    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        switch (qName) {
            case "partition_name" : {
                partitionInProgress = false;
                break;
            }

        }
    }


    @Override
    public void characters(char ch[], int start, int length) throws SAXException {
        if (partitionInProgress) {
            response.add(String.copyValueOf(ch, start, length).trim());
        }
    }

}