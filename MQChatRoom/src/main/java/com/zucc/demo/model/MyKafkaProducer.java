package com.zucc.demo.model;
import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
public class MyKafkaProducer {
	
    public void sendMessage(String topic,String msg) {
    	 Properties props = new Properties();
         props.put("bootstrap.servers", "localhost:9092");
         props.put("acks", "all");//acks配置控制请求被认为完成的条件
         props.put("retries", 0);
         props.put("batch.size", 16384);
         props.put("buffer.memory", 33554432);
         props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
         props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        Producer<String, String> producer = new KafkaProducer<>(props);
        ProducerRecord<String, String> record = new ProducerRecord<String, String>(topic,msg);
        producer.send(record);
        producer.close();

    }
	
}
