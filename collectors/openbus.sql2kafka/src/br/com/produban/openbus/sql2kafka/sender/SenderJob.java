package br.com.produban.openbus.sql2kafka.sender;

import br.com.produban.openbus.sql2kafka.util.properties.CollectProperties;
import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.produban.openbus.sql2kafka.queue.SendQueue;
import br.com.produban.openbus.sql2kafka.util.properties.Sql2KafkaProperties;

public abstract class SenderJob implements Job {

	private Logger LOG = LoggerFactory.getLogger(this.getClass());

	protected Sql2KafkaProperties sql2KafkaProperties;
    protected CollectProperties collectProperties;
	protected SendQueue sendQueues;
	
	@Override
	public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
		try {
			sql2KafkaProperties = (Sql2KafkaProperties) jobExecutionContext.getJobDetail().getJobDataMap().get("sql2KafkaProperties");
            collectProperties = (CollectProperties) jobExecutionContext.getJobDetail().getJobDataMap().get("collectProperties");
			sendQueues = (SendQueue) jobExecutionContext.getJobDetail().getJobDataMap().get("sendQueue");
			
			send();
		} catch (Exception e) {
			LOG.error("Error in sender. Exiting...", e);
			try {
				jobExecutionContext.getScheduler().shutdown(true);
			} catch (SchedulerException e1) {
				LOG.error("Error pausing job.", e);
			} finally {
				System.exit(-1);
			}
		}
	}
	
	public abstract void send();

}
