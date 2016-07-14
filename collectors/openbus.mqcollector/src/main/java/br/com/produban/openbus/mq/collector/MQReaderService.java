package br.com.produban.openbus.mq.collector;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.avro.Schema;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericData.Record;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.ibm.mq.MQException;
import com.ibm.mq.jms.MQConnection;
import com.ibm.mq.jms.MQConnectionFactory;
import com.ibm.mq.jms.MQQueue;
import com.ibm.mq.jms.MQSession;

import br.com.produban.openbus.ingestor.OpenbusDataIngestion;
import br.com.produban.openbus.mq.collector.listener.MQMessageListener;
import br.com.produban.openbus.mq.parser.MessageParser;
import br.com.produban.openbus.mq.parser.MessageParserDictionary;

@Service
public class MQReaderService {

	private static final Logger LOGGER = LoggerFactory.getLogger(MQReaderService.class);

	/** WMQ Account's username */
	@Autowired
	private String username;

	/** WMQ Account's password */
	@Autowired
	private String password;

	/** Connection factory instance (injecting) */
	@Autowired
	private MQConnectionFactory factory;

	/** Determines if ingestor is enabled by configuration */
	@Autowired
	private boolean ingestorIsEnabled;

	/** Instance of Openbus ingestor */
	@Autowired
	private OpenbusDataIngestion ingestor;

	/** Avro Schema used for Ingestor */
	@Autowired
	private Schema schema;

	/** Queues from default QueueManager */
	@Resource
	@Qualifier("queues")
	private List<String> queues;

	/** Session instance */
	private MQSession session;

	/** WMQ Connection instance */
	private MQConnection connection;

	/** Enabling user authentication */
	@Autowired
	private boolean noUserEnabled;

	/** Timeout configurated */
	@Autowired
	private long timeOutMillisecs;

	/**
	 * This guy does the whole job
	 * 
	 * @throws JMSException
	 * @throws IOException
	 * @throws MQException
	 */
	public void doTask() {

		try {

			createSession();
			launchConsumers();
			startListening();

		} catch (Exception e) {

			LOGGER.error("Error : ", e);

			try {

				LOGGER.info(String.format("Trying to reconnect after %s millisecs", timeOutMillisecs));

				Thread.sleep(timeOutMillisecs);

				doTask();

			} catch (InterruptedException ie) {

				LOGGER.error("Error : " + e);
			}
		}

	}

	/**
	 * This method create session from WMQ Connection
	 * 
	 * @throws JMSException
	 */
	private void createSession() throws JMSException {

		LOGGER.info("Creating session...");

		if (noUserEnabled) {
			connection = (MQConnection) factory.createConnection();
		} else {
			connection = (MQConnection) factory.createConnection(username, password);
		}
		session = (MQSession) connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
	}

	/**
	 * This method instance and launch all the consumers
	 * 
	 * @throws MQException
	 * @throws IOException
	 * @throws JMSException
	 */
	private void launchConsumers() throws MQException, IOException, JMSException {

		LOGGER.info("Launching consumers...");

		for (String qname : queues) {
			MessageConsumer consumer = session.createConsumer(new MQQueue(qname));
			MQMessageConsumerRunnable task = new MQMessageConsumerRunnable(consumer);
			task.setQueueName(qname);
			task.setWMQListener(messageListener);
			task.run();
		}
	}

	/**
	 * This method starts the listening
	 * 
	 * @throws JMSException
	 */
	private void startListening() throws JMSException {

		LOGGER.info("Start listening...");

		connection.start();

		while (true)
			;
	}

	/** Once the message is processed we are gonna ingest the information */
	private MQMessageListener messageListener = new MQMessageListener() {

		public void onMessageReceived(String queueName, Message msg) {

			if (msg != null) {

				TextMessage txtMsg = (TextMessage) msg;

				String messageId = null;

				try {

					if (txtMsg != null)
						messageId = txtMsg.getJMSCorrelationID();

					MessageParser parser = MessageParserDictionary.findByQueueName(queueName);

					Record record = new GenericData.Record(schema);
					record.put("hostname", factory.getHostName());
					record.put("queueManager", factory.getQueueManager());
					record.put("queue", queueName);
					record.put("timestamp", String.valueOf(System.currentTimeMillis()));

					HashMap<String, String> extraInfo = parser.parseMetadataFromMessage(txtMsg);
					record.put("extraInfo", extraInfo);

					if (ingestorIsEnabled) {
						ingestor.ingest("QMMessage", record);
					}

				} catch (Exception e) {

					LOGGER.error(String.format(
							"Error : Exception at message 'Id : %s' from [Queue: %s, QMgr: %s, Host: %s] ", messageId,
							queueName, factory.getQueueManager(), factory.getHostName()));

					LOGGER.error("Error : " + e);
				}
			}

		}
	};

}
