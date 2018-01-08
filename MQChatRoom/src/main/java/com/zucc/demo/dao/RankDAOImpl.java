package com.zucc.demo.dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.zucc.demo.model.RankVO;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Tuple;

@Repository
public class RankDAOImpl implements RankDAO{
	@Override
	public List<RankVO> GetRank() {
		List<RankVO> Ranks = new ArrayList<>();
		Jedis jedis = new Jedis("127.0.0.1", 6379);
		jedis.select(1);
      List<Tuple> result = new ArrayList<>(jedis.zrangeWithScores("UserCount", 0,9));
      jedis.close();
      int i=result.size();
      for (Tuple tuple: result) {  
    	  	 
    	  	 RankVO Rank=new RankVO();
    	  	 Rank.setRank(i);
    	  	 i--;	
    	  	 Rank.setCount((int)tuple.getScore());
    	  	 Rank.setUname(tuple.getElement());
    	  	 Ranks.add(Rank);
      }  	
      Collections.reverse(Ranks);
      return Ranks;
}
	
}