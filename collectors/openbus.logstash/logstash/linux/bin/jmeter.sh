#!/usr/bin/env bash

function execute {
  BASE=/produtos/zabbix/logstash-1.5.4
  JMETER=/produtos/zabbix/monitoracao_servicos/apache-jmeter-2.11/bin/ApacheJMeter.jar
  truncate -s 0 $BASE/data/$robo/results.xml
  sleep 1
  java \
      -Djmeterengine.nongui.port=0 \
      -Djmeterengine.nongui.maxport=0 \
      -Djmeter.save.saveservice.output_format=xml \
      -Djmeter.save.saveservice.assertion_results=all \
      -Djmeter.save.saveservice.response_data.on_error=true \
      -jar $JMETER \
      -H $host \
      -P $port \
      -u $user \
      -a $pass \
      -n -t $BASE/data/$robo.jmx \
      -l $BASE/data/$robo/results.xml \
      -j $BASE/data/$robo/status.log
}

for i in "$@"; do
  case $i in
    -r=*|--robo=*)
      robo="${i#*=}"
      shift
    ;;
    -h=*|--host=*)
      host="${i#*=}"
      shift
    ;;
    -u=*|--user=*)
      user="${i#*=}"
      shift
    ;;
    -p=*|--pass=*)
      pass="${i#*=}"
      shift
    ;;
    -o=*|--port=*)
      port="${i#*=}"
      shift
    ;;
    *)
    ;;
  esac
done

if [ -z "$robo" ]; then
  echo "Script to automate the Robots execution."
  echo "Usage: ${0} [OPTION=VALUE]"
  echo "Example: ${0} -r=robot -h=0.0.0.0 -u=root -p=pass -o=80"
  echo
  echo "Parameters:"
  echo "  -r=, --robo=  ROBO´s name to be executed."
  echo "  -h=, --host=  proxy to be used on the execution."
  echo "  -u=, --user=  USER to be used on the execution."
  echo "  -p=, --pass=  password to be used on the execution."
  echo "  -o=, --port=  port to be used on the execution."
  echo
  echo "Report bugs to: <EMAIL>"
  echo
  echo "Could not create file"

  #exit 1
else
  [[ ! -z "$host" ]] || host="<HOSTNAME>"
  [[ ! -z "$user" ]] || user="<USER>"
  [[ ! -z "$pass" ]] || pass="<PASSWORD>"
  [[ ! -z "$port" ]] || port=<PORT>
  execute $robo
fi
