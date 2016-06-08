package spring.config;

import integration.TaskFactory;
import integration.kafka.KafkaIntegrationTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
public class TaskExecutorConfig {

    private static final Logger LOGGER = LoggerFactory.getLogger(TaskExecutorConfig.class);

    @Autowired
    private Environment environment;

    @Bean
    @Qualifier("integration")
    public TaskExecutor getIntegrationThreadPoolTaskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(Integer.valueOf(environment.getProperty("integration.poolsize.core")));
        executor.setMaxPoolSize(Integer.valueOf(environment.getProperty("integration.poolsize.max")));
        executor.setQueueCapacity(Integer.valueOf(environment.getProperty("integration.pool.queue.length")));
        return executor;
    }

    @Bean
    @Qualifier("scheduling")
    public TaskExecutor getSchedulingThreadPoolTaskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(Integer.valueOf(environment.getProperty("scheduling.poolsize.core")));
        executor.setMaxPoolSize(Integer.valueOf(environment.getProperty("scheduling.poolsize.max")));
        executor.setQueueCapacity(Integer.valueOf(environment.getProperty("scheduling.pool.queue.length")));
        return executor;
    }
}
