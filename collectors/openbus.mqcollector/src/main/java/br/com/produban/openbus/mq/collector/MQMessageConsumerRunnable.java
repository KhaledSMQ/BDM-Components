package br.com.produban.openbus.mq.collector;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.produban.openbus.mq.collector.listener.MQMessageListener;

public class MQMessageConsumerRunnable implements Runnable {

	private static final Logger LOGGER = LoggerFactory.getLogger(MQMessageConsumerRunnable.class);

	/** Consumer which is gonna listen messages from WMQ */
	private MessageConsumer consumer;

	private String queueName;

	/** Listener that trigger the ingest */
	private MQMessageListener listener;

	public void run() {

		if (consumer != null) {

			try {

				consumer.setMessageListener(messageListener);

			} catch (JMSException e) {

				LOGGER.error(e.getMessage());
			}

		}

	}

	public MQMessageConsumerRunnable(MessageConsumer consumer) {
		this.consumer = consumer;
	}

	public MessageConsumer getConsumer() {

		return consumer;
	}

	public void setConsumer(MessageConsumer consumer) {

		this.consumer = consumer;
	}

	public MQMessageListener getListener() {

		return listener;
	}

	public void setWMQListener(MQMessageListener listener) {

		this.listener = listener;
	}

	public String getQueueName() {

		return queueName;
	}

	public void setQueueName(String queueName) {

		this.queueName = queueName;
	}

	/** This is the listener for asynchronous messages */
	private MessageListener messageListener = new MessageListener() {

		public void onMessage(Message msg) {

			if (msg != null && listener != null) {

				listener.onMessageReceived(queueName, msg);
			}
		}
	};

}
