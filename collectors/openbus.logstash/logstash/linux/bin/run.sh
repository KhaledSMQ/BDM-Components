#!/usr/bin/env bash
export BASE="$(dirname $0)"
export APP=$1
export PORT=$2
export JAVA_OPTS="$JAVA_OPTS -XX:NewSize=256M -XX:MaxNewSize=356M -XX:PermSize=256M -XX:MaxPermSize=356M"
cd $BASE
if [[ $# -eq 0 ]] ; then
    echo Modo de uso: $0 APP PORTA
    exit 1
fi
if [[ $# -eq 2 ]]; then
export JAVA_OPTS="$JAVA_OPTS -Dcom.sun.management.jmxremote -Dcom.sun.management.jmxremote.port=$PORT -Dcom.sun.management.jmxremote.ssl=false -Dcom.sun.management.jmxremote.authenticate=false -Djava.rmi.server.hostname=`hostname`"
fi

export CLASSPATH="$CLASSPATH:$BASE/vendor/jruby/lib/ruby/shared/openbus/*"

nohup $BASE/bin/logstash agent -f $BASE/conf/$APP.conf  >> $BASE/logs/$APP.log &
