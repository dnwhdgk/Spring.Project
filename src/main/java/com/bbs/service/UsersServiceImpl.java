package com.bbs.service;

import java.util.Properties;
import java.util.Random;

import javax.inject.Inject;
import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.stereotype.Service;

import com.bbs.dao.UsersDAO;
import com.bbs.util.Mail;
import com.bbs.vo.Authmail;
import com.bbs.vo.Users;

@Service
public class UsersServiceImpl implements UsersService {

	@Inject
	UsersDAO dao;
	
	@Override
	public int idCheck(String user_id) throws Exception {
		int result = 0;
		if(dao.idCheck(user_id) != null) result = 1;
		

		return result;
	}

	@Override
	public int setAuthnum(String user_mail) throws Exception {
		
			int result = 0;
			Random rd = new Random();
			
			// 4자리의 임의의 인증번호 생성
			int auth_num = rd.nextInt(9999) + 1;
			
			String from = "1223020@gmail.com";
			String to   = user_mail;
			String subject = "인증번호 메일";
			String content = "다음 인증번호를 입력하세요. <br> <h2>" + auth_num + "</h2>";
			
			// 해당 email 주소의 인증번호가 존재하는지 확인
			Integer exist = dao.getAuthnum(to);
			
			if(exist == null) dao.setAuthnum(new Authmail(to, auth_num));
						 else dao.resetAuthnum(new Authmail(to, auth_num));
			
			
			Properties p = new Properties();
			
			p.put("mail.smtp.user", from);
			p.put("mail.smtp.host", "smtp.gmail.com");
			p.put("mail.smtp.port", "587");
			p.put("mail.smtp.starttls.enable", "true");
			p.put("mail.smtp.auth", "true");
			p.put("mail.smtp.debug", "true");
			p.put("mail.smtp.socketFactory.port", "587");
			p.put("mail.smtp.socketFactory.fallback", "false");
			p.put("mail.smtp.ssl.trust", "smtp.gmail.com");
			p.put("mail.smtp.ssl.protocols", "TLSv1.2");
			
			try {
				
				Authenticator auth = new Mail();
				Session s = Session.getInstance(p, auth);
				s.setDebug(true);
				
				MimeMessage msg = new MimeMessage(s);
				
				Address fromAddr = new InternetAddress(from);
				Address toAddr = new InternetAddress(to);

				msg.setFrom(fromAddr);
				msg.setRecipient(Message.RecipientType.TO, toAddr);
				msg.setSubject(subject);
				msg.setContent(content, "text/html;charset=UTF-8");
				
				Transport.send(msg);
				
			} catch(Exception e) {
				e.printStackTrace();
				result = -1;
			}
		
		return result;
	}

	@Override
	public int checkAuthnum(Authmail authmail) throws Exception {
		
		
		int result = 1;
		Integer exist = dao.getAuthnum(authmail.getUser_mail());
		
		// 맞을 때
		if(exist.equals(authmail.getAuth_num()) ) {
			dao.deleteAuthmail(authmail.getUser_mail());
			result = 0;
		}
		// 틀릴 때
		//else {
		//result = 1;
		//}
		
		return result;
	}

	@Override
	public void joinAction(Users users) throws Exception {
		dao.join(users);
		
	}

	@Override
	public int loginAction(Users users) throws Exception {
		
		
		if(dao.login(users) == null) return 1;
		
		return 0;
	}
	
	

}
