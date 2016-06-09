package br.com.produban.openbus.mq.parser;

import java.util.HashMap;

import javax.jms.JMSException;
import javax.jms.TextMessage;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.produban.openbus.mq.parser.exception.MessageParsingException;

public class QReplicatorMessageParser extends MessageParser {

    private static final Logger LOGGER = LoggerFactory.getLogger(QReplicatorMessageParser.class);

    @Override
    public HashMap<String, String> parseMetadataFromMessage(TextMessage msg)
	    throws MessageParsingException, JMSException {

	HashMap<String, String> extraInfo = new HashMap<String, String>();

	if (msg == null) {
	    throw new MessageParsingException("returned a null message object");
	}

	if (StringUtils.isEmpty(msg.getText())) {
	    throw new MessageParsingException("returned a empty or null text message");
	}

	String[] arrValues = msg.getText().split("\\|");

	if (arrValues.length > 0) {

	    String fieldName = null;

	    for (int position = 0; position < arrValues.length; position++) {

		fieldName = null;

		switch (position) {

		case 19:
		    fieldName = "tclTimestamp";
		    break;

		case 20:
		    fieldName = "tclTransaction";
		    break;

		case 21:
		    fieldName = "tclUser";
		    break;

		case 22:
		    fieldName = "tclAccount";
		    break;

		case 23:
		    fieldName = "tclTerminalAccount";
		    break;

		case 24:
		    fieldName = "tclSystemIdCICS";
		    break;

		case 25:
		    fieldName = "tclDatOriginals";
		    break;

		default:
		    fieldName = null;
		    break;

		}

		if (fieldName != null) {

		    String value = arrValues[position];
		    value = value.replaceAll("^\"", "").trim();
		    extraInfo.put(fieldName, value);
		}
	    }

	} else {

	    LOGGER.error("The message wasn't parsed, check the pipe ('|') token!");
	    LOGGER.error(String.format("MESSAGE : \n %s", msg.getText()));

	    throw new MessageParsingException("The message wasn't parsed, check the pipe ('|') token!");
	}

	return extraInfo;
    }

}
