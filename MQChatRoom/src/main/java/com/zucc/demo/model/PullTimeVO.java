package com.zucc.demo.model;
import java.io.Serializable;
public class PullTimeVO implements Serializable{

	private static final long serialVersionUID = 1L;
	private String uname;
	private String pushtimestamp;
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public String getPushtimestamp() {
		return pushtimestamp;
	}
	public void setPushtimestamp(String pushtimestamp) {
		this.pushtimestamp = pushtimestamp;
	}
	@Override
	public String toString() {
		return "PullTimeVO [uname=" + uname + ", pushtimestamp=" + pushtimestamp + "]";
	}
}
