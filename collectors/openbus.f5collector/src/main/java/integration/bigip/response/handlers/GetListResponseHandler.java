package integration.bigip.response.handlers;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

public class GetListResponseHandler extends DefaultHandler {

    private final List<String> response = new ArrayList<>();

    public List<String> getResponse() {
        return response;
    }

    private boolean itemInProgress = false;

    private String item;

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes)
            throws SAXException {

        switch (qName) {
            case "item" : {
                itemInProgress = true;
                break;
            }
        }
    }


    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        switch (qName) {
            case "item" : {
                itemInProgress = false;
                response.add(item == null ? "" : item);
                item = null;
                break;
            }

        }
    }


    @Override
    public void characters(char ch[], int start, int length) throws SAXException {
        if (itemInProgress) {
            item = String.copyValueOf(ch, start, length).trim();
        }
    }
}
