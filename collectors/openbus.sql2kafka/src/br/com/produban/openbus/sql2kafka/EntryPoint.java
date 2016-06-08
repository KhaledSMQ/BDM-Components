package br.com.produban.openbus.sql2kafka;

import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

import java.util.Set;

import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.produban.openbus.sql2kafka.collector.DeltaTimeCollector;
import br.com.produban.openbus.sql2kafka.collector.DumpCollector;
import br.com.produban.openbus.sql2kafka.collector.SequenceCollector;
import br.com.produban.openbus.sql2kafka.collector.store.StatePersister;
import br.com.produban.openbus.sql2kafka.queue.SendQueue;
import br.com.produban.openbus.sql2kafka.queue.serialization.Serializer;
import br.com.produban.openbus.sql2kafka.sender.KafkaSender;
import br.com.produban.openbus.sql2kafka.util.properties.CollectProperties;
import br.com.produban.openbus.sql2kafka.util.properties.CollectTypeEnum;
import br.com.produban.openbus.sql2kafka.util.properties.Constants;
import br.com.produban.openbus.sql2kafka.util.properties.ResourceUtils;
import br.com.produban.openbus.sql2kafka.util.properties.Sql2KafkaProperties;

public class EntryPoint {

	private static Logger LOG = LoggerFactory.getLogger(EntryPoint.class);

	public static void main(String[] args) {

		try {
			if (args.length == 0) {
				LOG.error("Please, specify the configuration directory containing the property files:"
						+ " \n - collect.properties \n - sql2kafka.properties");
				System.exit(0);
			}

			String collectorName = System.getProperty(Constants.COLLECTOR_NAME);

			if (collectorName == null || collectorName.isEmpty()) {
				LOG.error("Please especify the collector name, as a system property. Ex.: -D"
						+ Constants.COLLECTOR_NAME + "=\"MyDBsCollector\"."
						+ " \nThis will define BerkeleyDB path, LogBack filename, etc.");
				System.exit(0);
			}

			String propertiesDir = args[0];
			ResourceUtils resourceUtils = new ResourceUtils(propertiesDir, collectorName);
			Sql2KafkaProperties sql2KafkaProperties = resourceUtils.getSql2KafkaProperties();

			SchedulerFactory schedFact = new StdSchedulerFactory();
			Scheduler sched = schedFact.getScheduler();
			sched.start();

			Set<String> collectNames = resourceUtils.getCollectNames();

			for (String collectName : collectNames) {
				CollectProperties collectProperties = resourceUtils.getCollectProperties(collectName);

				Serializer serializer = new Serializer(collectProperties.getAvroSchemaClass());

				SendQueue sendQueue = new SendQueue(collectName, sql2KafkaProperties.getCollectorName(), serializer);
				
				StatePersister statePersister = new StatePersister(collectorName); 

				JobDataMap jobDataMap = new JobDataMap();
				jobDataMap.put("collectProperties", collectProperties);
				jobDataMap.put("sql2KafkaProperties", sql2KafkaProperties);
				jobDataMap.put("sendQueue", sendQueue);
				jobDataMap.put("statePersister", statePersister);

				JobDetail collectorJob = null;
				switch (CollectTypeEnum.valueOf(collectProperties.getType())) {
				case DUMP:
					collectorJob = newJob(DumpCollector.class)
							.withIdentity("DumpCollectorJob_" + collectName, "collector").usingJobData(jobDataMap)
							.build();
					break;
				case DELTATIME:
					collectorJob = newJob(DeltaTimeCollector.class)
							.withIdentity("TimeCollectorJob_" + collectName, "collector").usingJobData(jobDataMap)
							.build();
					break;
				case SEQUENCE:
					collectorJob = newJob(SequenceCollector.class)
							.withIdentity("OffsetCollectorJob_" + collectName, "collector").usingJobData(jobDataMap)
							.build();
					break;
				default:
					break;
				}

				JobDetail senderJob = newJob(KafkaSender.class).withIdentity("SenderJob_" + collectName, "sender")
						.usingJobData(jobDataMap).build();

				Trigger collectorTrigger = newTrigger().withIdentity("CollectorTrigger_" + collectName, "collector")
						.withSchedule(cronSchedule("0 0/" + collectProperties.getCronEveryMin() + " * * * ?")).build();

				Trigger senderTrigger = newTrigger().withIdentity("SenderTrigger_" + collectName, "sender")
						.withSchedule(simpleSchedule().withIntervalInMilliseconds(1500).repeatForever()).build();

				sched.scheduleJob(collectorJob, collectorTrigger);
				sched.scheduleJob(senderJob, senderTrigger);
			}

		} catch (Throwable t) {
			LOG.error("Error in main thread.", t);
		}
	}

}
