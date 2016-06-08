#!/bin/bash

if [ -z "$JAVA_HOME" ] ; then
  JAVA_HOME="../jre7"
fi

. ./logstash "$@"
