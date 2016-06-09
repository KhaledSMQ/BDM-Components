package br.com.produban.openbus.mq.parser;

import java.util.HashMap;

import br.com.produban.openbus.mq.parser.exception.MessageParserNotDefinedException;

public class MessageParserDictionary {

    private static HashMap<String, MessageParser> parserMap = new HashMap<String, MessageParser>();

    public static MessageParser findByQueueName(String queueName) throws MessageParserNotDefinedException {

	if (!parserMap.containsKey(queueName)) {

	    register(queueName);
	}

	return parserMap.get(queueName);

    }

    private static void register(String queueName) throws MessageParserNotDefinedException {

	MessageParser parser = null;

	switch (queueName) {

	case "IDR.QL.DB0K.ZBDT163": {
	    parser = new QReplicatorMessageParser();
	    break;
	}
	default: {
	    parser = null;
	    break;
	}

	}

	if (parser == null)
	    throw new MessageParserNotDefinedException(String.format("MessageParser not defined. You must register a parser for queue '%s'", queueName));

	parserMap.put(queueName, parser);

    }

    
}
