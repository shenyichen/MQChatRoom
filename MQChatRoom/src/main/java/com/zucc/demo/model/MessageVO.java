package com.zucc.demo.model;

import java.io.Serializable;
public class MessageVO implements Serializable{
	private static final long serialVersionUID = 1L;
	private String uname;
	private String text;
	private String timestamp;
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
    @Override
    public String toString() {
        return "UserVO [uname=" + uname + ", text=" + text
                + ", timestamp=" + timestamp + "]";
    }
}
