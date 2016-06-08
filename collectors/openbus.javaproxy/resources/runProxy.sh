java -server \
    -Dcom.sun.management.jmxremote \
    -Dcom.sun.management.jmxremote.port=8068 \
    -Dcom.sun.management.jmxremote.ssl=false \
    -Dcom.sun.management.jmxremote.authenticate=false \
    -Djava.rmi.server.hostname=localhost \
    -Xms1G -Xmx1G \
    -XX:NewSize=750M \
    -XX:MaxNewSize=750M \
    -jar openbus.javaproxy-0.1-SNAPSHOT-jar-with-dependencies.jar \
    proxy_config.properties \
    publisher_config.properties \
    log4j.xml &
