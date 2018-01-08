package com.zucc.demo.model;
import java.io.Serializable;
public class RankVO implements Serializable{
	private static final long serialVersionUID = 1L;
	
    private String uname;
    private int count;
    private int rank;
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}


	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public int getRank() {
		return rank;
	}
	public void setRank(int rank) {
		this.rank = rank;
	}

    @Override
    public String toString() {
        return "RankVO [Rank=" + rank + ", uname=" + uname+", Count=" + count+"]";
    }

}