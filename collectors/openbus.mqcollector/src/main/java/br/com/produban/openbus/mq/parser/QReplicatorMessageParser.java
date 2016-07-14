package br.com.produban.openbus.mq.parser;

import java.util.HashMap;
import java.util.Map;

import javax.jms.JMSException;
import javax.jms.TextMessage;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.produban.openbus.mq.parser.exception.MessageParsingException;

public class QReplicatorMessageParser extends MessageParser {

	private static final Logger LOGGER = LoggerFactory.getLogger(QReplicatorMessageParser.class);

	@Override
	public HashMap<String, String> parseMetadataFromMessage(TextMessage msg) throws MessageParsingException, JMSException {

		HashMap<String, String> extraInfo = new HashMap<String, String>();

		if (msg == null) {
			throw new MessageParsingException("returned a null message object");
		}

		if (StringUtils.isEmpty(msg.getText())) {
			throw new MessageParsingException("returned a empty or null text message");
		}

		String[] arrValues = msg.getText().split("\\|");

		if (arrValues.length > 0) {
			for (QReplicatorData item : QReplicatorData.values()) {
				putValue(arrValues, item.getIndex(), item.getName(), extraInfo);
			}

		} else {

			LOGGER.error("The message wasn't parsed, check the pipe ('|') token!");
			LOGGER.error(String.format("MESSAGE : \n %s", msg.getText()));

			throw new MessageParsingException("The message wasn't parsed, check the pipe ('|') token!");
		}

		return extraInfo;
	}
	
	private void putValue(String[] source, int index, String name, Map<String, String> target) {
		if(source.length > index) {
			target.put(name, source[index].replaceAll("^\"", "").trim());
		}
	}

}
