package br.com.produban.openbus.mq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import br.com.produban.openbus.mq.collector.MQReaderService;

@SpringBootApplication
public class Launcher {

    public static void main(String[] args) {

	ConfigurableApplicationContext context = SpringApplication.run(Launcher.class, args);
	context.getBean(MQReaderService.class).doTask();

    }

}
