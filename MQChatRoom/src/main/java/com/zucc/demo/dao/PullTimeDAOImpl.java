package com.zucc.demo.dao;

import org.springframework.stereotype.Repository;

import com.zucc.demo.model.PullTimeVO;

import redis.clients.jedis.Jedis;
@Repository
public class PullTimeDAOImpl implements PullTimeDAO{
	public Boolean PushTime(PullTimeVO PushTime)
	{
		Jedis jedis = new Jedis("127.0.0.1", 6379);
		jedis.select(1);
		jedis.set(PushTime.getUname(), PushTime.getPushtimestamp());
		jedis.close();
		return true;
	}
	
	public PullTimeVO PullTime(String uname)
	
	{
		Jedis jedis = new Jedis("127.0.0.1", 6379);
		jedis.select(1);
		String timestamp=jedis.get(uname);
		jedis.close();
		PullTimeVO result=new PullTimeVO();
		result.setPushtimestamp(timestamp);
		result.setUname(uname);
		return result;
	}
}
