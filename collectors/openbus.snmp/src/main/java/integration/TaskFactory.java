package integration;

import integration.kafka.KafkaIntegrationTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class TaskFactory {

    private static final Logger LOGGER = LoggerFactory.getLogger(TaskFactory.class);

    public KafkaIntegrationTask getKafkaIntegrationTaskInstance() {
        return KafkaIntegrationTask.getInstance();
    }
}
