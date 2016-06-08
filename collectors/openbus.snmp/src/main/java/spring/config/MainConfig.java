package spring.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan(basePackages = {"spring","jobs","integration"})
@PropertySource("classpath:config.properties")
@Import({TaskExecutorConfig.class,SchedulingConfig.class})
public class    MainConfig {
}
