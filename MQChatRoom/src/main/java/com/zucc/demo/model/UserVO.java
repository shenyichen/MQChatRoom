package com.zucc.demo.model;
import java.io.Serializable;
public class UserVO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	 private int id;
    private String uname;
    private String pwd;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
    @Override
    public String toString() {
        return "UserVO [id=" + id + ", uname=" + uname
                + ", pwd=" + pwd + "]";
    }
}
