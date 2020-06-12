cd /usr/local/etc/zookeeper/
zkServer start

cd /usr/local/Cellar/kafka/2.4.0
./kafka-server-start /usr/local/etc/kafka/server.properties &
