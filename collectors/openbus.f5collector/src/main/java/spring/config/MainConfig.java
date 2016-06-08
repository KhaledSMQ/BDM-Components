package spring.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan(basePackages = {"integration","scheduling","spring","database"})
@PropertySource("classpath:config.properties")
@Import({TaskExecutorConfig.class,WebConfig.class,SchedulingConfig.class,DatabaseConfig.class})
public class MainConfig {
}
