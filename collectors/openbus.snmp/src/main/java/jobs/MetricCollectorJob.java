package jobs;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import exceptions.ResponseParsingException;
import integration.CommElementMessage;
import integration.TaskFactory;
import integration.kafka.KafkaIntegrationTask;
import integration.snmp.model.SnmpModel;
import integration.snmp.services.SnmpGetter;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.io.IOException;
import java.util.List;

public class MetricCollectorJob extends QuartzJobBean {

    private static final Logger LOGGER = LoggerFactory.getLogger(MetricCollectorJob.class);

    @Autowired
    private SnmpGetter snmpGetter;
    @Autowired
    @Qualifier("integration")
    private TaskExecutor integrationTaskExecutor;
    @Autowired
    private TaskFactory taskFactory;

    private Config config = ConfigFactory.load().getConfig("devices.platforms");

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        String deviceIp = context.getJobDetail().getJobDataMap().getString("ipAddress");
        String tool = context.getJobDetail().getJobDataMap().getString("tool");
        String platform = context.getJobDetail().getJobDataMap().getString("platform");

        if (LOGGER.isInfoEnabled())
            LOGGER.info("Running collector job for ip {} and tool {}", deviceIp, tool);

        try {

            List<SnmpModel> metrics = snmpGetter.getMetricsFor(deviceIp,config.getConfigList(platform));

            if (metrics.isEmpty()) {
                LOGGER.warn(String.format("There's no available metrics for device [IP: %s]",
                        deviceIp));
            } else {

                CommElementMessage message;

                for (SnmpModel model : metrics) {
                    KafkaIntegrationTask task = taskFactory.getKafkaIntegrationTaskInstance();
                    message = new CommElementMessage();
                    message.setModel(model);
                    message.setTool(tool);
                    task.setRecord(message);

                    integrationTaskExecutor.execute(task);
                }
            }
        } catch (ResponseParsingException e) {
            throw new JobExecutionException(String.format("Fail to parse response from ip %s",deviceIp),e);
        } catch (IOException e) {
            throw new JobExecutionException(String.format("Fatal error to to run job for ip %s",deviceIp),e);
        }

        if (LOGGER.isInfoEnabled())
            LOGGER.info(String.format("Collector job [IP: %s] done!",deviceIp));
    }
}