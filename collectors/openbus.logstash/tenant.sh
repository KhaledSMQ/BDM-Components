#!/bin/bash

# Script to package all tenant-specific configuration for Openbus Logstash

# Required script arguments:
#   CONFIG: Full path to this script's configuration file, at position 1
#   OS: Flag to indicate which OS will be supported, at position 2
#     Valid options: 'LINUX' or 'WIN'
#   TENANT_ID: Tenant ID to be prepended at Kafka output 'topic_id' property, at position 3

USAGE="USAGE: ./tenant.sh ./config.properties WIN|LINUX TENANT_ID"

CONFIG=$1
OS=$2
TENANT_ID=$3

if [[ -z "$CONFIG" || ! -f $CONFIG ]]; then
  echo "A valid config file (.properties) is required at position 1"
  echo $USAGE
  exit 1
fi

if [[ -z "$OS" || "$OS" != "WIN" && "$OS" != "LINUX" ]]; then
  echo "A valid OS flag is required at position 2"
  echo $USAGE
  exit 1
fi

if [[ -z "$TENANT_ID" ]]; then
  echo "A valid TENANT_ID is required at position 1"
  echo $USAGE
  exit 1
fi

. $CONFIG

PROJECT_PATH=$(dirname $0)

function setup () {

  if [ ! -d "$SANDBOX_PATH" ]; then
    mkdir $SANDBOX_PATH
  fi

  TENANT_PATH=$SANDBOX_PATH/configuration/$TENANT_ID

  if [ -d "$TENANT_PATH" ]; then
    rm -Rf $TENANT_PATH
  fi

  mkdir -p $TENANT_PATH/conf
  mkdir -p $TENANT_PATH/release

  if [ "$OS" = "WIN" ]; then
    PLATFORM='windows'
  else
    PLATFORM='linux'
  fi

}

function copy_platform_config () {
  cp $PROJECT_PATH/logstash/$PLATFORM/conf/* $TENANT_PATH/conf
}

function apply_tenant_id () {
  sed -i -r "s/topic_id => \"(.*)\"/topic_id => \"$(echo $TENANT_ID)\.\1\"/" $TENANT_PATH/conf/*.conf
}

function release () {
  pushd $TENANT_PATH
  zip -r ./release/$TENANT_ID.zip ./conf
  popd
}

setup
copy_platform_config
apply_tenant_id
release

echo 'Done!'