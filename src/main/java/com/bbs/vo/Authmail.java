package com.bbs.vo;

public class Authmail {
	private String user_mail;
	private int auth_num;
	
	public Authmail() {
		
	}

	public Authmail(String user_mail, int auth_num) {
		super();
		this.user_mail = user_mail;
		this.auth_num = auth_num;
	}

	public String getUser_mail() {
		return user_mail;
	}

	public void setUser_mail(String user_mail) {
		this.user_mail = user_mail;
	}

	public int getAuth_num() {
		return auth_num;
	}

	public void setAuth_num(int auth_num) {
		this.auth_num = auth_num;
	}
	
	
}
