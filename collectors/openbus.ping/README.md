#Requirements

1.- Copy ping.avsc schema to /opt/openbus/schema/

2.- Specify your spring.config.location path in args as default it take from jar classpath.
 
#Start
```shell 
./Launcher start|stop|debug|status
```

#Note

##Properties of Ping 

|Property     			   | Description                             |
|--------------------------|:---------------------------------------:|
|ping.timeout=1000		   | Config the time out time in milliseconds|
|ping.retry=5			   |	Number of Retryes					 |
|ping.cron=*/5 * * * * *   | Crontab in format						 |
|ping.mode=cmd             | inet -> Java Inet cmd= Native cmd ping  |
|config.file.path 		   |  Config of devices						 |

##Properties of Threads 

|Property            		 | Description                |
|----------------------------|:--------------------------:|
|task.core.pool.size=100	 | Initial Number of threads  |
|task.max.pool.size=200		 | Max number of threads	  |			
|task.queue.capacity.size=200| Queue Capacity			  |


##Status Ping

|Value | Description	|
|------|----------------|
|0	   | Down			|
|1	   | Up 			|

