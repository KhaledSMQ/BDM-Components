#Setting properties (application.properties)

1.- Set the kafka connection by setting kafka properties. For example:
```shell
    kafka.config.brokers.host=192.168.11.45:9092
    kafka.config.required.acks=false
    kafka.config.async=true
    kafka.config.batch.messages=100
```

2.- Set the kafka topics. For example:
```shell
    kafka.social.success.topic=BR.SOCIAL.SUCCESS
    kafka.social.failure.topic=BR.SOCIAL.FAILURE
```

3.- Set the facebook credentials AppId and AppSecret.
    IMPORTANT: The facebook proxy is disabled by default. Set the proxy connection by setting the 'social.facebook.proxy.enabled' as true. Also,
    set 'social.facebook.proxy.host' and 'social.facebook.proxy.port' properties. For example:

```shell
    social.facebook.appid=946608178756660
    social.facebook.secret=f5e1be5cf8d73a9011bb1c50c5469588
    social.facebook.proxy.enabled=false
    social.facebook.proxy.host=76.110.179.69
    social.facebook.proxy.port=8080
```

4.- Set the location of the 'logback.xml' file by modifying the 'logging.config' property. For example:
```shell
    logging.config=/openbus.social/resources/logback.xml
```

#Run

Specify your spring.config.location path in args as default it take from jar classpath.

```shell
java -jar openbus.social-0.1.0-SNAPSHOT.jar -Dspring.config.location=${full_path_of_config}
```