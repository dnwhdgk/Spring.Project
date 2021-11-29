package com.bbs.util;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

public class Mail extends Authenticator {
	
	protected static String id;
	protected static String pw;
	
	public Mail() {
		id = "1223020@donga.ac.kr";
		pw = "Qazw1234!!";
	}
	
	protected PasswordAuthentication getPasswordAuthentication() {
		
		return new PasswordAuthentication(id, pw);
		
	}

}