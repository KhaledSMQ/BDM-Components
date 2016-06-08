package jobs;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import org.quartz.*;
import org.quartz.impl.matchers.GroupMatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

public class ConfigurationJob extends QuartzJobBean {

    private static final Logger LOGGER = LoggerFactory.getLogger(ConfigurationJob.class);

    private static final String COLLECTORS_JOB_GROUP = "collectors";
    private static final String COLLECTORS_TRIGGER_GROUP = "triggers";

    private final Config config = ConfigFactory.load();

    @Autowired
    private ApplicationContext context;

    @Autowired
    private Environment environment;

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {

        LOGGER.info("Running configuration job");

        Scheduler scheduler = jobExecutionContext.getScheduler();
        try {
            removeAllScheduledJobs(scheduler);

            for (Config device : config.getConfigList("devices.list")) {
                String ip = device.getString("ip");
                String tool = device.getString("tool");
                String platform = device.getString("platform");
                LOGGER.debug(
                        MessageFormat.format("Device detected: [{0} - {1} - {2}]",ip,tool,platform)
                );
                scheduleNewStatisticsJob(scheduler, ip, tool, platform);
            }

        } catch (SchedulerException e) {
            throw newJobException("Fail to unschedule "+COLLECTORS_JOB_GROUP+" jobs", e);
        } catch (Exception e) {
            throw newJobException("Fail to execute configuration job", e);
        }

        LOGGER.info("Configuration job done!");
    }

    private void removeAllScheduledJobs(Scheduler scheduler) throws SchedulerException {
        Set<JobKey> scheduledJobs = scheduler.getJobKeys(GroupMatcher.<JobKey>groupEquals(COLLECTORS_JOB_GROUP));
        if (!scheduledJobs.isEmpty()) {
            List<JobKey> keys = new ArrayList<>();
            keys.addAll(scheduledJobs);
            scheduler.deleteJobs(keys);
        }
    }

    private void scheduleNewStatisticsJob(Scheduler scheduler, String deviceIp, String tool, String platform) throws SchedulerException {

        JobDetail jobDetail = newJob(MetricCollectorJob.class)
                .withIdentity(
                        MessageFormat.format("Metric collector job for ip {0}, tool {1}",deviceIp,tool)
                        ,COLLECTORS_JOB_GROUP)
                .usingJobData("ipAddress", deviceIp)
                .usingJobData("tool", tool)
                .usingJobData("platform",platform)
                .build();
        CronTrigger trigger = newTrigger()
                .withIdentity(
                        MessageFormat.format("Metric collector trigger for ip {0}, tool {1}",deviceIp,tool)
                        ,COLLECTORS_TRIGGER_GROUP)
                .withSchedule(cronSchedule(environment.getProperty("cron.metricCollectorJob")))
                .build();
        scheduler.scheduleJob(jobDetail, trigger);
    }

    private JobExecutionException newJobException(String message, Exception cause) {
        JobExecutionException ex = new JobExecutionException(message, cause);
        ex.refireImmediately();
        return ex;
    }
}
