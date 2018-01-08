package com.zucc.demo.dao;

import java.util.List;
import org.springframework.stereotype.Repository;

import com.google.gson.Gson;
import com.zucc.demo.model.MessageVO;
import com.zucc.demo.model.MyKafkaConsumer;
import com.zucc.demo.model.MyKafkaProducer;
import com.zucc.demo.model.PullTimeVO;
@Repository
public class MessageDAOImpl implements MessageDAO{
	public Boolean PushMessage(String Topic,MessageVO Message)
	{	
		Gson gson = new Gson();  
		new MyKafkaProducer().sendMessage(Topic,gson.toJson(Message));
		return true;
	}
	

	public List<MessageVO> getAllMessage(String Topic,PullTimeVO time)
	{
		return new MyKafkaConsumer().receive(time.getUname(), time.getPushtimestamp(), Topic);
		
		
		
	}
}
