package com.zucc.demo.dao;
import com.zucc.demo.model.PullTimeVO;

public interface PullTimeDAO {
	public Boolean PushTime(PullTimeVO PushTime);
	public PullTimeVO PullTime(String uname);
}
