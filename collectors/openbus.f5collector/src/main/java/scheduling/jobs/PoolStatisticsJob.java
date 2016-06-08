package scheduling.jobs;

import database.model.VirtualServer;
import database.repository.VirtualServerRepository;
import exceptions.ResponseParsingException;
import integration.TaskFactory;
import integration.bigip.response.model.Pool;
import integration.bigip.services.PartitionService;
import integration.bigip.services.PoolMemberService;
import integration.bigip.services.PoolService;
import integration.kafka.KafkaIntegrationTask;
import integration.kafka.KafkaMessage;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.web.client.RestClientException;

import java.util.*;

public class PoolStatisticsJob extends QuartzJobBean {

    private static final Logger LOGGER = LoggerFactory.getLogger(PoolStatisticsJob.class);

    @Autowired
    private PartitionService partitionService;
    @Autowired
    private VirtualServerRepository virtualServerRepository;
    @Autowired
    private PoolMemberService poolMemberService;
    @Autowired
    @Qualifier("integration")
    private TaskExecutor integrationTaskExecutor;
    @Autowired
    private TaskFactory taskFactory;

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        String bigIpAddress = context.getJobDetail().getJobDataMap().getString("ipAddress");
        String tool = context.getJobDetail().getJobDataMap().getString("tool");

        if (LOGGER.isInfoEnabled())
            LOGGER.info(String.format("Running collector job for ip: %s",bigIpAddress));

        try {

            List<VirtualServer> virtualServers = virtualServerRepository.getAllFrom(bigIpAddress);

            if (virtualServers.isEmpty()) {
                LOGGER.warn(String.format("There's no available virtual servers for this BigIP [IP: %s]",
                        bigIpAddress));
                return;
            }

            List<String> partitions = partitionService.getPartitionList(bigIpAddress);

            if (partitions.isEmpty()) {
                LOGGER.warn(String.format("There is no available partitions for this BigIP [IP: %s]", bigIpAddress));
            }
            else {
                Map<String,Set<VirtualServer>> map = extractPoolNames(virtualServers);

                if (map.isEmpty()) {
                    LOGGER.warn(String.format("There is no available pools for this BigIP [IP: %s]",
                            bigIpAddress));
                } else {
                    for (String partition : partitions) {
                        partitionService.setActivePartition(bigIpAddress,partition);

                        List<Pool> pools = poolMemberService.getAllStatisticsFrom(bigIpAddress,
                                new ArrayList<>(map.keySet()));
                        if (pools.isEmpty()) {
                            LOGGER.warn(String.format("There is no available data for this BigIP [IP: %s | Partition: %s]",
                                    bigIpAddress,
                                    partition));
                        } else {
                            for (Pool pool : pools) {
                                for (VirtualServer server : map.get(pool.getName())) {
                                    KafkaIntegrationTask task = taskFactory.getKafkaIntegrationTaskInstance();
                                    KafkaMessage message = new KafkaMessage();
                                    message.setPool(pool);
                                    message.setVirtualServer(server);
                                    message.setTool(tool);

                                    task.setMessage(message);
                                    integrationTaskExecutor.execute(task);
                                }
                            }
                        }
                    }
                }
            }
        } catch (ResponseParsingException e) {
            throw new JobExecutionException(String.format("Fail to parse response from ip %s",bigIpAddress),e);
        } catch (RestClientException e) {
            throw new JobExecutionException(String.format("Fail to get HTTP response from ip %s",bigIpAddress),e);
        }

        if (LOGGER.isInfoEnabled())
            LOGGER.info(String.format("Collector job [IP: %s] done!",bigIpAddress));
    }

    private Map<String,Set<VirtualServer>> extractPoolNames(List<VirtualServer> virtualServers) {
        Map<String,Set<VirtualServer>> result = new HashMap<>();
        
        for (VirtualServer server : virtualServers)
            for (database.model.Pool pool : server.getPools()) {
                if (result.get(pool.getName()) == null)
                    result.put(pool.getName(),new HashSet<VirtualServer>());

                result.get(pool.getName()).add(server);
            }

        return result;
    }
}