from pyspark import SparkConf, SparkContext
import re
from pyspark.streaming import StreamingContext
from pyspark.streaming.kafka import KafkaUtils
import redis
import sys
import json
reload(sys)
sys.setdefaultencoding('utf8')
conf = SparkConf().setAppName("UserCount-ChatRoom1")

sc = SparkContext(conf=conf)

zk = "localhost:2181"
topic = "ChatRoom1"
ssc = StreamingContext(sc, 5)


kvs = KafkaUtils.createStream(ssc, zk, "spark-streaming-consumer", {topic:1})
#KafkaUtils.createDstream(ssc, [zk], [consumer group id], [per-topic,partitions] ) 
lines = kvs.map(lambda x: x[1])
lines.pprint()
def spl (y):
	t=json.loads(y)
	return t['uname']
rdd1=lines.map(spl)
pairs = rdd1.map(lambda word: (word, 1))
wordCounts = pairs.reduceByKey(lambda x, y: x + y)
def workersave(y):
	r = redis.Redis(host='127.0.0.1', port=6379, db=1)
	r.zincrby('UserCount', y[0] ,y[1])
	return
def save (y):
	y.foreach(workersave)
	return
rdd2=wordCounts.foreachRDD(save)
wordCounts.pprint()
ssc.start()
ssc.awaitTermination()
