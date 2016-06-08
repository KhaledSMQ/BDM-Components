package boot;

import java.io.File;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import spring.config.MainConfig;

public class Launcher {
    public static void main(String[] args) {
    	
    	String configFileLocation = args[0];
		
		if (configFileLocation == null || configFileLocation.isEmpty()){
			throw new IllegalStateException("Config file location is mandatory!");
		}
        else if (!(new File(configFileLocation).exists())){
        	throw new IllegalStateException("Config file location is mandatory!");
        }

        System.setProperty("config.file", configFileLocation);
    	
        ApplicationContext context = new AnnotationConfigApplicationContext(MainConfig.class);
    }
}
