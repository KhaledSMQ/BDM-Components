package br.com.produban.openbus.mq.collector.listener;

import javax.jms.Message;

public interface MQMessageListener {

    public void onMessageReceived(String queueName, Message msg);

}
