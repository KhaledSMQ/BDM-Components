#BUILD

1.- Install these files as mvn dependencies
    src/main/ext/grammatica-1.5.jar
    src/main/ext/mibble-mibs-.2.9.3.jar
    src/main/ext/mibble-parser-2.9.3.jar
    src/main/ext/snmp6_0.jar
```shell
mvn install:install-file -Dfile=grammatica-1.5.jar  -DgroupId=net.percederberg -DartifactId=grammatica -Dversion=1.5 -Dpackaging=jar
...
mvn install:install-file -Dfile=snmp6_0.jar  -DgroupId=org.bouncycastle -DartifactId=crypto -Dversion=6.0 -Dpackaging=jar
...
mvn install:install-file -Dfile=mibble-mibs-2.9.3.jar  -DgroupId=net.percederberg -DartifactId=mibs -Dversion=2.9.3 -Dpackaging=jar
...
mvn install:install-file -Dfile=mibble-parser-2.9.3.jar  -DgroupId=net.percederberg -DartifactId=mibble -Dversion=2.9.3 -Dpackaging=jar
```

2.- build the module from parent maven project (/Openbus/...):
```shell
$ mvn clean install package -pl openbus.infoblox -am -Dmaven.test.skip=true
```

#RUN

3.- Specify your spring.config.location path in args as default it take from jar classpath.

```shell
java -jar openbus.openbus-0.1.0-SNAPSHOT.jar -Dspring.config.location=${full_path_of_config}
```