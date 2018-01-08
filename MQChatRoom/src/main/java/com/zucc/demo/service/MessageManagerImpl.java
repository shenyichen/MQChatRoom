package com.zucc.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zucc.demo.dao.MessageDAO;
import com.zucc.demo.dao.PullTimeDAO;
import com.zucc.demo.model.MessageVO;
import com.zucc.demo.model.PullTimeVO;
@Service
public class MessageManagerImpl implements MessageManager{

	 @Autowired
	 MessageDAO dao;
	 @Autowired
	 PullTimeDAO tdao;
	public List<MessageVO> getNewMessage(String Topic,String uname)
	{
		String timestamp=tdao.PullTime(uname).getPushtimestamp();
		PullTimeVO push=new PullTimeVO();
		push.setPushtimestamp(Long.toString(System.currentTimeMillis()));
		push.setUname(uname);
		tdao.PushTime(push);
		push.setPushtimestamp(timestamp);
		return dao.getAllMessage(Topic,push);
	}
}
