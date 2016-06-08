#!/usr/bin/env bash

java -server \
    -Dcom.sun.management.jmxremote \
    -Dcom.sun.management.jmxremote.port=8171 \
    -Dcom.sun.management.jmxremote.ssl=false \
    -Dcom.sun.management.jmxremote.authenticate=false \
    -Djava.rmi.server.hostname=localhost \
    -Xms128m -Xmx256m \
    -classpath "openbus.snmp.jar:lib/*:conf" \
    boot.Launcher &