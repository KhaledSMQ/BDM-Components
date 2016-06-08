package spring.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import jobs.ConfigurationJob;
import spring.AutowiringSpringBeanJobFactory;

import java.io.IOException;
import java.text.ParseException;

import static org.quartz.DateBuilder.IntervalUnit;
import static org.quartz.DateBuilder.futureDate;
import static org.quartz.TriggerBuilder.newTrigger;

@Configuration
public class SchedulingConfig {

    private static final Logger LOGGER = LoggerFactory.getLogger(SchedulingConfig.class);

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private TaskExecutorConfig taskExecutorConfig;

    @Autowired
    private Environment environment;

    @Bean
    public SchedulerFactoryBean getSchedulerFactoryBean() throws ParseException, IOException {
        SchedulerFactoryBean bean = new SchedulerFactoryBean();
        bean.setConfigLocation(new ClassPathResource("quartz.properties"));
        bean.setApplicationContext(applicationContext);
        bean.setTaskExecutor(taskExecutorConfig.getSchedulingThreadPoolTaskExecutor());
        // custom job factory of spring with DI support for @Autowired!
        AutowiringSpringBeanJobFactory jobFactory = new AutowiringSpringBeanJobFactory();
        jobFactory.setApplicationContext(applicationContext);
        bean.setJobFactory(jobFactory);

        bean.setTriggers(getConfigurationJobTriggerFactoryBean().getObject(),
                         newTrigger()
                         .withIdentity("startupTrigger")
                         .startAt(futureDate(10, IntervalUnit.SECOND))
                         .forJob(getConfigurationJobDetailFactoryBean().getObject())
                         .build());

        return bean;
    }

    @Bean
    public CronTriggerFactoryBean getConfigurationJobTriggerFactoryBean() {
        CronTriggerFactoryBean bean = new CronTriggerFactoryBean();
        bean.setCronExpression(environment.getProperty("cron.configurationjob"));
        bean.setJobDetail(getConfigurationJobDetailFactoryBean().getObject());
        return bean;
    }

    @Bean
    @Scope(BeanDefinition.SCOPE_PROTOTYPE)
    public JobDetailFactoryBean getConfigurationJobDetailFactoryBean() {
        JobDetailFactoryBean bean = new JobDetailFactoryBean();
        bean.setJobClass(ConfigurationJob.class);
        return bean;
    }

}
