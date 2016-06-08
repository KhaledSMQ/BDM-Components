#Build

1.- Install src/main/ext/vim25.jar as mvn dependency. 
```shell
mvn install:install-file -Dfile=vim25.jar  -DgroupId=com.vmware.sdk -DartifactId=sdk-java -Dversion=6.0 -Dpackaging=jar
```

2.- Copy hypervisor.avsc schema to /opt/openbus/schema/ 
	Copy rejected_signal.avsc schema to /opt/openbus/schema/

3.- Specify your spring.config.location path in args as default it take from jar classpath.
 
```shell 
java -jar openbus.hypervisor-0.1.0-SNAPSHOT.jar -Dspring.config.location=${full_path_of_config}
```

#Requirements
* ESXI & VCenter 6.0 https://my.vmware.com/web/vmware/details?productId=490&downloadGroup=VC600



