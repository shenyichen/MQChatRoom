package com.zucc.demo.model;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;


import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import com.zucc.demo.model.MessageVO;
import com.google.gson.Gson;

public class MyKafkaConsumer {

	
	
	
	
		public List<MessageVO> receive(String uname,String timestamp,String topic){
		  Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("group.id", uname);
        props.put("enable.auto.commit", "true");
        props.put("auto.commit.interval.ms", "1000");
        props.put("session.timeout.ms", "30000");
        props.put("auto.offset.reset", "earliest");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        KafkaConsumer<String,String> consumer = new KafkaConsumer<String,String>(props);
        consumer.subscribe(Arrays.asList(topic));
        
     
            ConsumerRecords<String,String> consumerRecords = consumer.poll(100);
            Gson gson = new Gson();
            List<MessageVO> result=new ArrayList<MessageVO>();
            for(ConsumerRecord<String, String> consumerRecord:consumerRecords){
            		if(consumerRecord.timestamp()>Long.parseLong(timestamp)) {
            					MessageVO message=new MessageVO();
            					message=gson.fromJson(consumerRecord.value(), MessageVO.class);
            						result.add(message);
            		}
            }
            consumer.close();
            return result;
        }
    
	

	  
}
