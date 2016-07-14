package br.com.produban.openbus.mq.parser;

import java.util.HashMap;

import javax.jms.JMSException;
import javax.jms.TextMessage;

import br.com.produban.openbus.mq.parser.exception.MessageParsingException;

public abstract class MessageParser {

	public abstract HashMap<String, String> parseMetadataFromMessage(TextMessage data)
			throws MessageParsingException, JMSException;

}
