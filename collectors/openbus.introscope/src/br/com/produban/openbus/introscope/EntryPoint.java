package br.com.produban.openbus.introscope;

import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

import java.io.File;

import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.produban.openbus.introscope.core.Executor;
import br.com.produban.openbus.introscope.sender.Sender;
import br.com.produban.openbus.introscope.util.parser.ParserMetricName;
import br.com.produban.openbus.introscope.util.properties.ResourceUtils;
import br.com.produban.openbus.introscope.util.properties.ResourceUtils.Constants;
import br.com.produban.openbus.introscope.util.queue.Queues;

public class EntryPoint {

	private static Logger LOG = LoggerFactory.getLogger(EntryPoint.class);

	public static IntroscopeCollector introscopeCollector;

	public static void main(String[] args) {

		try {
			String propertiesFile = args[0];
			
			String configFileLocation = args[1];
			
			if (configFileLocation == null || configFileLocation.isEmpty()){
				throw new IllegalStateException("Config file location is mandatory!");
			}
	        else if (!(new File(configFileLocation).exists())){
	        	throw new IllegalStateException("Config file location is mandatory!");
	        }

	        System.setProperty("config.file", configFileLocation);
			
			Queues queues = new Queues();

			introscopeCollector = new IntroscopeCollector();
			introscopeCollector.initializeAgent(propertiesFile);

			ResourceUtils resourceUtils = introscopeCollector.getResourceUtils();
			
			Integer collectCron = Integer.valueOf(resourceUtils.getProperty(Constants.COLLECT_CRON_EVERY_MIN));
			
			if (collectCron < 0 || collectCron > 59) {
				throw new IllegalArgumentException("Both collect and discovery minutes must be 0-59, in a \"every minute\" behavior.");
			}

			SchedulerFactory schedFact = new StdSchedulerFactory();
			Scheduler sched = schedFact.getScheduler();
			sched.start();

			JobDataMap jobDataMap = new JobDataMap();
			jobDataMap.put("resourceUtils", resourceUtils);
			jobDataMap.put("queues", queues);

			JobDetail executorJob = newJob(Executor.class).withIdentity("ExecutorJob", "executor")
					.usingJobData(jobDataMap).build();
			
			JobDetail parserExecutorJob = newJob(ParserMetricName.class).withIdentity("ParserExecutorJob", "executor")
					.usingJobData(jobDataMap).build();

			Trigger executorTrigger = newTrigger().withIdentity("ExecutorTrigger", "executor")
					.withSchedule(cronSchedule("0 0/" + collectCron + " * * * ?")).build();
			
			Trigger parserExecutorTrigger = newTrigger().withIdentity("ParserExecutorTrigger", "executor")
					.withSchedule(simpleSchedule().withIntervalInMilliseconds(500).repeatForever()).build();

			JobDetail senderJob = newJob(Sender.class).withIdentity("SenderJob", "sender")
					.usingJobData(jobDataMap).build();
			
			Trigger senderTrigger = newTrigger().withIdentity("SenderTrigger", "sender")
					.withSchedule(simpleSchedule().withIntervalInMilliseconds(500).repeatForever()).build();

			sched.scheduleJob(executorJob, executorTrigger);
			sched.scheduleJob(parserExecutorJob, parserExecutorTrigger);
			
			sched.scheduleJob(senderJob, senderTrigger);

		} catch (Throwable t) {
			LOG.error("Error in main thread.", t);
		}

	}
}
