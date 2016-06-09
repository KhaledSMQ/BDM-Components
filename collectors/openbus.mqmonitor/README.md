#Requirements

Install all these dependencies from openbus.mqmonitor/src/main/java/resources/ext/

mvn install:install-file -Dfile=com.ibm.mq-7.5.0.2.jar -DgroupId=com.ibm -DartifactId=com.ibm.mq -Dversion=7.5.0.2 -Dpackaging=jar

mvn install:install-file -Dfile=com.ibm.mq.commonservices-7.5.0.2.jar -DgroupId=com.ibm -DartifactId=com.ibm.mq.commonservices -Dversion=7.5.0.2 -Dpackaging=jar

mvn install:install-file -Dfile=com.ibm.mqjms-7.5.0.2.jar -DgroupId=com.ibm -DartifactId=com.ibm.mqjms -Dversion=7.5.0.2 -Dpackaging=jar

mvn install:install-file -Dfile=com.ibm.mq.jmqi-7.5.0.2.jar -DgroupId=com.ibm -DartifactId=com.ibm.mq.jmqi -Dversion=7.5.0.2 -Dpackaging=jar

mvn install:install-file -Dfile=com.ibm.mq.pcf-7.5.0.2.jar -DgroupId=com.ibm -DartifactId=com.ibm.mq.pcf -Dversion=7.5.0.2 -Dpackaging=jar

mvn install:install-file -Dfile=com.ibm.mq.headers-7.5.0.2.jar -DgroupId=com.ibm -DartifactId=com.ibm.mq.headers -Dversion=7.5.0.2 -Dpackaging=jar

mvn install:install-file -Dfile=rmm-7.5.0.2.jar -DgroupId=com.ibm -DartifactId=rmm -Dversion=7.5.0.2 -Dpackaging=jar

mvn install:install-file -Dfile=dhbcore-7.5.0.2.jar -DgroupId=com.ibm -DartifactId=dhbcore -Dversion=7.5.0.2 -Dpackaging=jar

mvn install:install-file -Dfile=com.ibm.cl3export-7.5.0.2.jar -DgroupId=com.ibm -DartifactId=com.ibm.cl3export -Dversion=7.5.0.2 -Dpackaging=jar

mvn install:install-file -Dfile=com.ibm.cl3nonexport-7.5.0.2.jar -DgroupId=com.ibm -DartifactId=com.ibm.cl3nonexport -Dversion=7.5.0.2 -Dpackaging=jar

mvn install:install-file -Dfile=ldap-1.2.4.jar -DgroupId=com.sun.jndi -DartifactId=ldap -Dversion=1.2.4 -Dpackaging=jar

mvn install:install-file -Dfile=jndi-1.2.1.jar -DgroupId=javax.naming -DartifactId=jndi -Dversion=1.2.1 -Dpackaging=jar

mvn install:install-file -Dfile=javax.jms-api-2.0.1.jar -DgroupId=javax.jms -DartifactId=javax.jms-api -Dversion=2.0.1 -Dpackaging=jar

mvn install:install-file -Dfile=connector-api-1.5.jar -DgroupId=javax.resource -DartifactId=connector-api -Dversion=1.5 -Dpackaging=jar

#Start
Use ./Launcher.sh start|stop|debug|status

#Note

###Chanel Status

|value	| Description		|
|-------|-------------------|
|1		| MQCHS_BINDING		|
|9		| MQCHS_DISCONNECTED|
|0		| MQCHS_INACTIVE	|
|13		| MQCHS_INITIALIZING|
|8		| MQCHS_PAUSED		|
|7		| MQCHS_REQUESTING	|
|5		| MQCHS_RETRYING	|
|3		| MQCHS_RUNNING		|
|2		| MQCHS_STARTING	|
|6		| MQCHS_STOPPED		|
|4		| MQCHS_STOPPING	|
|14		| MQCHS_SWITCHING	|


###Listener Status

|Value  | Description          |
|-------|----------------------|
|4		| MQSVC_STATUS_RETRYING|
|2		| MQSVC_STATUS_RUNNING |
|1		| MQSVC_STATUS_STARTING|
|0		| MQSVC_STATUS_STOPPED |
|3		| MQSVC_STATUS_STOPPING|