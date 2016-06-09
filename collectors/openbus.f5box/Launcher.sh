#!/bin/bash

##
# Starts the Openbus F5Box Collector.
#
# Usage:
# ./execute.sh start|stop|status|debug
#
# Author: cmsandiga
##

java_exe="java";
app_name="openbus.f5box";
debug_opts="-Xdebug -Xrunjdwp:transport=dt_socket,address=8999,server=y,suspend=y";
java_opts="-Xms128m -Xmx256m";
jar_path="/produtos/${app_name}/${app_name}-0.1.0-SNAPSHOT.jar";
properties_path="/produtos/${app_name}/application.properties";

getPid ()
{
	sleep 1;
	PID=`ps auxwww | grep java | grep ${app_name} |  awk '{print $2}'`;
}

case "${1}" in
	start)
		nohup ${java_exe} ${java_opts} -jar ${jar_path} --spring.config.properties=${properties_path} > /dev/null 2>&1 &
		getPid
		if [ -n "${PID}" ]; then	
			echo ${app_name} "started with pid ${PID}"
		else
			echo "Error starting" ${app_name}
		fi
		;;
	debug)
		nohup ${java_exe} ${java_opts} ${debug_opts} -jar ${jar_path} --spring.config.properties=${properties_path} > /dev/null 2>&1 &
		getPid
		if [ -n "${PID}" ]; then	
			echo ${app_name} "start debugging with pid ${PID}"
		else
			echo "Error start debugging" ${app_name}
		fi
		;;
	stop)
		getPid
		if [ -z "${PID}" ]
		then
			echo ${app_name} "is not running";
		else
			echo "Killing ${app_name} [${PID}]"
			kill $PID
		fi
		;;
	status)
		getPid
		if [ -z "${PID}" ]; then
			echo "${app_name} is not running"
		else
			echo "${app_name} is running [${PID}]"
		fi
		;;
	*)
		echo "Usage: $ME (start|stop|status|debug)"
		;;
esac
