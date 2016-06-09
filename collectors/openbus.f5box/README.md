#Build

1.- Copy f5box.avsc schema to /produtos/schema/f5box.avsc
	Copy rejected_signal.avsc schema to /produtos/schema/f5box.avsc

2.- go to the OpenBus root directory and execute:
```shell
mvn package -pl openbus.f5box/ -am -Dmaven.test.skip=true
```

3.- Specify your spring.config.location path in args as default it take from jar classpath.

```shell
java -jar openbus.f5-0.1.0-SNAPSHOT.jar -Dspring.config.location=${full_path_of_config}
```

#Requirements
* F5 installed Download F5 trial edition from https://www.f5.com/trial/secure/big-ip-ltm-virtual-edition.php import appliance into workstation.

#Confluence
http://192.168.10.150:8090/display/PROD/F5