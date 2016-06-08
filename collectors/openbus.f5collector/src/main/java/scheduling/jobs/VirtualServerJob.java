package scheduling.jobs;

import database.model.Pool;
import database.repository.VirtualServerRepository;
import exceptions.ResponseParsingException;
import integration.bigip.response.model.VirtualServer;
import integration.bigip.services.VirtualServerServices;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestClientException;

import java.util.ArrayList;
import java.util.List;

public class VirtualServerJob extends QuartzJobBean {

    private static final Logger LOGGER = LoggerFactory.getLogger(VirtualServerJob.class);

    @Autowired
    private VirtualServerRepository repository;

    @Autowired
    private VirtualServerServices service;

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {

        LOGGER.info("Running virtual servers job");

        String bigIpAddress = context.getJobDetail().getJobDataMap().getString("ipAddress");

        try {
            List<VirtualServer> availableServers = service.getVirtualServers(bigIpAddress);

            if (availableServers.isEmpty()) {
                LOGGER.warn("There's no available virtual servers for this BigIP [IP: {}]", bigIpAddress);
            } else {

                repository.deleteAllFrom(bigIpAddress);

                List<database.model.VirtualServer> serversToPersist = new ArrayList<>();
                for (VirtualServer server : availableServers) {
                    database.model.VirtualServer toPersist = new database.model.VirtualServer(
                            bigIpAddress,
                            server.getName(),
                            server.getDestination()
                    );

                    if (server.getDefaultPoolName()!=null && !server.getDefaultPoolName().isEmpty())
                        toPersist.getPools().add(new Pool(server.getDefaultPoolName(), toPersist));

                    serversToPersist.add(toPersist);
                }

                repository.insertAll(serversToPersist);
            }
        } catch (ResponseParsingException e) {
            throw new JobExecutionException(String.format("Fail to parse response from ip %s",bigIpAddress),e);
        } catch (RestClientException e) {
            throw new JobExecutionException(String.format("Fail to get HTTP response from ip %s",bigIpAddress),e);
        }

        LOGGER.info("Virtual servers job is done.");
    }

}
