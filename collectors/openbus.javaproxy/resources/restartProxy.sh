kill -9 `ps -ef | grep -v grep | grep javaproxy | awk '{ print $2 }'`
./runProxy.sh
