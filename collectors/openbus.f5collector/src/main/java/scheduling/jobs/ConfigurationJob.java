package scheduling.jobs;

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

            String tool = ConfigFactory.load("devices").getString("devices.defaultTool");

            for (Config device : getBigIPAddressList()) {
                String ip = device.getString("ipAddress");
                LOGGER.debug("BigIP detected: ["+ip+"]");
                scheduleNewVirtualServerJob(scheduler, ip);
                scheduleNewStatisticsJob(scheduler, ip, tool);
            }

        } catch (SchedulerException e) {
            throw newJobException("Fail to unschedule "+COLLECTORS_JOB_GROUP+" jobs", e);
        } catch (IOException e) {
            throw newJobException("Fail to retrieve address list to schedule new jobs", e);
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

    private List<? extends Config> getBigIPAddressList() throws IOException {
        return ConfigFactory.load("devices").getConfigList("devices.list");
    }

    private void scheduleNewStatisticsJob(Scheduler scheduler, String bigIpAddress, String tool) throws SchedulerException {

        JobDetail jobDetail = newJob(PoolStatisticsJob.class)
                .withIdentity("Pool Statistics job for ip " + bigIpAddress + "", COLLECTORS_JOB_GROUP)
                .usingJobData("ipAddress", bigIpAddress)
                .usingJobData("tool", tool)
                .build();
        CronTrigger trigger = newTrigger()
                .withIdentity("Pool Statistics trigger for ip "+bigIpAddress, COLLECTORS_TRIGGER_GROUP)
                .withSchedule(cronSchedule(environment.getProperty("cron.statisticsjob")))
                .build();
        scheduler.scheduleJob(jobDetail, trigger);
    }

    private void scheduleNewVirtualServerJob(Scheduler scheduler, String bigIpAddress) throws SchedulerException {
        JobDetail jobDetail = newJob(VirtualServerJob.class)
                .withIdentity("Virtual servers job for ip " + bigIpAddress + "", COLLECTORS_JOB_GROUP)
                .usingJobData("ipAddress", bigIpAddress)
                .build();
        CronTrigger trigger = newTrigger()
                .withIdentity("Virtual servers trigger for ip "+bigIpAddress, COLLECTORS_TRIGGER_GROUP)
                .withSchedule(cronSchedule(environment.getProperty("cron.virtualServersJob")))
                .build();
        scheduler.scheduleJob(jobDetail, trigger);
    }

    private JobExecutionException newJobException(String message, Exception cause) {
        JobExecutionException ex = new JobExecutionException(message, cause);
        ex.refireImmediately();
        return ex;
    }
}
