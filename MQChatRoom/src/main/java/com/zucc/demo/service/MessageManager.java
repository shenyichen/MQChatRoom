package com.zucc.demo.service;

import java.util.List;

import com.zucc.demo.model.MessageVO;


public interface MessageManager {
	public List<MessageVO> getNewMessage(String Topic,String uname);
}
