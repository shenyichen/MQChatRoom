package com.zucc.demo.dao;
import java.util.List;

import com.zucc.demo.model.MessageVO;
import com.zucc.demo.model.PullTimeVO;
public interface MessageDAO {
	public List<MessageVO> getAllMessage(String Topic,PullTimeVO time);
	public Boolean PushMessage(String Topic,MessageVO Message);
	
}
