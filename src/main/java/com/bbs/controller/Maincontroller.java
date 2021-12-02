package com.bbs.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bbs.service.BbsService;
import com.bbs.service.UsersService;
import com.bbs.vo.Authmail;
import com.bbs.vo.Users;

@Controller
public class MainController {
	
	private static final Logger logger = LoggerFactory.getLogger(MainController.class);
	
	@Inject
	UsersService usersService;
	@Inject
	BbsService   bbsService;
	
	// url 패턴이 'path/' 일 경우
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String main(Model model) throws Exception {
		return "main/main";
	}
	
	// url 패턴이 'path/join' 일 경우
	@RequestMapping(value = "/join", method = RequestMethod.GET)
	public String join(Model model) throws Exception {
		
		return "main/join";
	}
	
	// url 패턴이 'path/login' 일 경우
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Model model) throws Exception {
		
		return "main/login";
	}
	
	// url 패턴이 'path/bbs' 일 경우
	@RequestMapping(value = "/bbs", method = RequestMethod.GET)
	public String bbs(Integer pageNumber, Model model) throws Exception {
		
		if(pageNumber == null) pageNumber = 1;
		
		model.addAttribute("map", bbsService.bbs(pageNumber));
		
		return "bbs/bbs";
	}
	
	// url 패턴이 'path/joinAction' 일 경우
	@RequestMapping(value = "/joinAction", method = RequestMethod.POST)
	public String joinAction(Users users, String addr1, String addr2, String addr3) throws Exception {
		
		users.setUser_addr(addr1 + " " + addr2 + " " + addr3);
		usersService.joinAction(users);
		
		return "redirect:/login";
		
	}
	
	@RequestMapping(value = "/loginAction", method = RequestMethod.POST)
	public String loginAction(Users users, HttpSession session, RedirectAttributes ra) throws Exception {
		
		int result = usersService.loginAction(users);
		String url = null;
		
		if(result == 0) {
			session.setAttribute("user_id", users.getUser_id());
			url = "redirect:/";
		}
		else {
			ra.addFlashAttribute("msg", "로그인 정보가 일치하지 않습니다.");
			url = "redirect:/login";
		}
		
		return url;
	}
	
	// url 패턴이 'path/logout' 일 경우
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpSession session) throws Exception {
		
		session.invalidate();
		
		return "redirect:/";
		
	}
	
	// url 패턴이 'path/idCheck' 일 경우
	@RequestMapping(value = "/idCheck", method = RequestMethod.GET)
	// 반환값을 페이지에 직접 출력
	@ResponseBody
	public String idCheck(String user_id) throws Exception {
		
		int result = usersService.idCheck(user_id);
		return result + "";
	}
	
	// url 패턴이 'path/sendAuthMail' 일 경우
	@RequestMapping(value = "/sendAuthMail", method = RequestMethod.GET)
	@ResponseBody
	public String sendAuthMail(String user_mail) throws Exception {
		
		int result = usersService.setAuthnum(user_mail);
		return result + "";
		
	}
	
	// url 패턴이 'path/mailAuth' 일 경우
	@RequestMapping(value = "/mailAuth", method = RequestMethod.POST)
	@ResponseBody
	public String mailAuth(Authmail authmail) throws Exception {
		
		return usersService.checkAuthnum(authmail) + "";
		
	}
	
}