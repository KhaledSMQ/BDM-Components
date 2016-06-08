#!/bin/bash

# Script that builds the Openbus Logstash version with all of its dependencies.

# Required environment variables:
#   M2_REPO -> Full path to Maven local repository

# Required script arguments:
#   CONFIG: Full path to this script's configuration file, at position 1
#   OS: Flag to indicate which OS will be supported, at position 2
#     Valid options: 'LINUX' or 'WIN'

# Optional script arguments:
#   JRE_INCLUDED: Flag to indicate if a valid JRE should be included into the final bundle, at position 3

USAGE="USAGE: env M2_REPO='path_to_maven_repo' ./assembly.sh ./config.properties WIN|LINUX 1"

CONFIG=$1
OS=$2
JRE=$3

if [[ -z "$M2_REPO" || ! -d $M2_REPO ]]; then
  echo "A valid M2_REPO path is required"
  echo $USAGE
  exit 1
fi

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

. $CONFIG

setup () {
  if [ ! -d "$SANDBOX_PATH" ]; then
    mkdir $SANDBOX_PATH
  fi

  if [ -d "$SANDBOX_PATH/tmp" ]; then
    rm -Rf $SANDBOX_PATH/tmp
  fi

  if [ ! -d "$SANDBOX_PATH/release" ]; then
    mkdir $SANDBOX_PATH/release
  fi

  mkdir $SANDBOX_PATH/tmp

  if [ "$OS" = "WIN" ]; then
    JRE_URL="$JRE_URL_WINDOWS"
    PLATFORM='windows'
  else
    JRE_URL="$JRE_URL_LINUX"
    PLATFORM='linux'
  fi
}

find_logstash_bundle () {
  if [ -z "$LOGSTASH_BUNDLE" ]; then
    LOGSTASH_BUNDLE=$(find $SANDBOX_PATH -iname "*logstash*$LOGSTASH_VERSION*.tar.gz" | head -n 1)
  fi
}

find_jre_bundle () {
  if [ -z "$JRE_BUNDLE" ]; then
    JRE_BUNDLE=$(find $SANDBOX_PATH -iname "*jre-7u*-$1-x64.tar.gz" | head -n 1)
  fi
}

download_logstash () {
  find_logstash_bundle
  if [ -z "$LOGSTASH_BUNDLE" ]; then
    echo "Downloading Logstash (URL: $LOGSTASH_URL) to $SANDBOX_PATH"
    wget -P $SANDBOX_PATH $LOGSTASH_URL
    find_logstash_bundle
  else
    echo "Logstash is already in the house"
  fi
}

assembly_logstash () {
  echo "Extracting Logstash ($LOGSTASH_BUNDLE) to $SANDBOX_PATH/tmp/..."
  tar xzf $LOGSTASH_BUNDLE --directory $SANDBOX_PATH/tmp/

  LOGSTASH_FULLPATH=$(find $SANDBOX_PATH/tmp/ -type d -name '*logstash*' -prune | head -n 1)

  apply_kafka_output
  apply_dependencies
  apply_openbus_logstash
  apply_openbus_model

  if [ -n "$JRE" ]; then
    apply_jre
  fi

  apply_openbus_logstash_addons
}

apply_kafka_output () {
  echo 'Downloading logstash-kafka output...'
  wget -P $LOGSTASH_FULLPATH/lib/logstash/outputs $LOGSTASH_KAFKA_BASE_URL/lib/logstash/outputs/kafka.rb
}

apply_dependencies () {
  local JRUBY_JAR_PATH=$(find $LOGSTASH_FULLPATH/vendor/jar -iname 'jruby-complete*.jar')

  echo 'Installing jruby-kafka ruby gem...'
  env GEM_HOME=$LOGSTASH_FULLPATH/vendor/bundle/jruby/1.9 GEM_PATH="" java -jar $JRUBY_JAR_PATH -S gem install jruby-kafka -v "$JRUBY_KAFKA_GEM_VERSION"

  if [ "$PLATFORM" = "windows" ]; then
    echo 'Installing Windows dependencies...'

    echo 'Installing jruby-win32ole ruby gem...'
    env GEM_HOME=$LOGSTASH_FULLPATH/vendor/bundle/jruby/1.9 GEM_PATH="" java -jar $JRUBY_JAR_PATH -S gem install jruby-win32ole
  fi
}

apply_openbus_logstash () {
  echo 'Applying openbus.logstash jar...'
  local JAR_PATH="$M2_REPO/$OPENBUS_LOGSTASH_RELATIVE_JARPATH"
  local RELEASED_JAR_PATH="$LOGSTASH_FULLPATH/vendor/jar/kafka-openbus/libs"

  if [ ! -f $JAR_PATH ]; then
    echo "Openbus.logstash jar file not found"
    exit 1
  else
    mkdir -p $RELEASED_JAR_PATH
    cp $JAR_PATH $RELEASED_JAR_PATH
  fi
}

apply_openbus_model () {
  echo 'Applying openbus.model jar...'
  local JAR_PATH="$M2_REPO/$OPENBUS_MODEL_RELATIVE_JARPATH"
  local RELEASED_JAR_PATH="$LOGSTASH_FULLPATH/vendor/jar/kafka-openbus/libs"

  if [ ! -f $JAR_PATH ]; then
    echo "Openbus.model jar file not found"
    exit 1
  else
    mkdir -p $RELEASED_JAR_PATH
    cp $JAR_PATH $RELEASED_JAR_PATH
  fi
}

apply_jre () {
  echo 'Applying Java Runtime Environment...'
  
  find_jre_bundle $PLATFORM

  if [ -z $JRE_BUNDLE ]; then
    echo "Downloading JRE (URL: $LOGSTASH_URL) to $SANDBOX_PATH"
    wget -P $SANDBOX_PATH --no-check-certificate --no-cookies --header "Cookie: oraclelicense=accept-securebackup-cookie" $JRE_URL
  else
    echo "JRE is already in the house"
  fi

  find_jre_bundle $PLATFORM

  tar xzf $JRE_BUNDLE --directory $SANDBOX_PATH/tmp/
  find $SANDBOX_PATH/tmp/ -type d -name '*jre*' -prune -exec mv {} $SANDBOX_PATH/tmp/jre7 \;

  mv $SANDBOX_PATH/tmp/jre7 $LOGSTASH_FULLPATH
}

apply_openbus_logstash_addons () {

  local PROJECT_PATH=$(dirname $0)

  cp $PROJECT_PATH/logstash/$PLATFORM/bin/* $LOGSTASH_FULLPATH/bin
  mkdir $LOGSTASH_FULLPATH/conf
#  cp $PROJECT_PATH/logstash/$PLATFORM/conf/* $LOGSTASH_FULLPATH/conf

  cp $PROJECT_PATH/logstash/$PLATFORM/inputs/* $LOGSTASH_FULLPATH/lib/logstash/inputs
}

release_logstash () {
  echo 'Releasing Openbus.logstash bundle...'

  local FOLDER=$(basename $LOGSTASH_FULLPATH)

  tar czf $SANDBOX_PATH/release/openbus.logstash-$OPENBUS_LOGSTASH_VERSION.tar.gz -C $SANDBOX_PATH/tmp $FOLDER
}

setup
download_logstash
assembly_logstash
release_logstash

echo "Done!"
