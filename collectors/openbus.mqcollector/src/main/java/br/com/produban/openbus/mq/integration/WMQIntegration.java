package br.com.produban.openbus.mq.integration;

import java.util.List;

import javax.jms.JMSException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ibm.mq.jms.MQConnectionFactory;
import com.ibm.msg.client.wmq.WMQConstants;
import com.typesafe.config.Config;

@Configuration
public class WMQIntegration {

    @Autowired
    private Config config;

    @Bean
    public long timeOutMillisecs() {

	long millisecs = config.getLong("wmq.config.timeOutMillisecs");
	return millisecs;
    }

    @Bean
    public boolean noUserEnabled() {

	boolean enabled = config.getBoolean("wmq.config.nouser");
	return enabled;
    }

    @Bean
    public String username() {

	String username = config.getString("wmq.config.usr");
	return username;
    }

    @Bean
    public String password() {

	String pwd = config.getString("wmq.config.pwd");
	return pwd;
    }

    @Bean
    public List<String> queues() throws Exception {

	return config.getStringList("wmq.config.queues");
    }

    @Bean
    public MQConnectionFactory connectionFactory() throws JMSException {

	MQConnectionFactory factory = new MQConnectionFactory();
	factory.setQueueManager(config.getString("wmq.config.manager"));
	factory.setChannel(config.getString("wmq.config.channel"));
	factory.setHostName(config.getString("wmq.config.hostname"));
	factory.setPort(config.getInt("wmq.config.port"));
	factory.setTransportType(WMQConstants.WMQ_CM_CLIENT);

	return factory;
    }

}
