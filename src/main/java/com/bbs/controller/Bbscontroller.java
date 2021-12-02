package com.bbs.controller;

import java.util.HashMap;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bbs.service.BbsService;
import com.bbs.vo.Boarder;
import com.bbs.vo.UploadFile;

@Controller
@RequestMapping(value = "/bbs/*")
public class BbsController {
	
	@Inject
	BbsService bbsService;

	// url 패턴이 'path/bbs/' 일 경우
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String bbs(Model model) throws Exception {
		return "redirect:/bbs";
	}
	
	// url 패턴이 'path/bbs/write' 일 경우
	@RequestMapping(value = "/write", method = RequestMethod.GET)
	public String write(RedirectAttributes ra, HttpSession session) throws Exception {
		
		// 로그인이 안되있으면 로그인 페이지로 이동 시키고 '로그인이 필요합니다' 알려줌
		if(session.getAttribute("user_id") == null) {
			ra.addFlashAttribute("msg", "로그인이 필요합니다.");
			return "redirect:/login";
		}
		// 로그인 되있으면 글쓰기 페이지 출력
		return "bbs/write";
		
	}
	
	// url 패턴이 'path/bbs/view' 일 경우
	@RequestMapping(value = "/view", method = RequestMethod.GET)
	public String view(Integer boarder_id, Model model, RedirectAttributes ra) throws Exception {
		
		HashMap<String, Object> map = bbsService.view(boarder_id);
		
		if(map.get("boarder") == null) {
			ra.addFlashAttribute("msg", "존재하지 않는 게시물입니다.");
			return "redirect:/bbs";
		}
		
		model.addAttribute("map", map);
		
		return "bbs/view";
		
	}
	
	// url 패턴이 'path/bbs/update' 일 경우
	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public String update(Integer boarder_id, Model model, HttpSession session, RedirectAttributes ra) throws Exception {
		
		String user_id = (String) session.getAttribute("user_id");
		
		HashMap<String, Object> map = bbsService.view(boarder_id);
		Boarder boarder = (Boarder) map.get("boarder");
		
		if(user_id == null) {
			ra.addFlashAttribute("msg", "로그인이 필요합니다.");
			return "redirect:/login";
		}
		
		if(boarder == null) {
			ra.addFlashAttribute("msg", "존재하지 않는 게시물입니다.");
			return "redirect:/bbs";
		}
		
		if(!user_id.equals(boarder.getWriter())) {
			ra.addFlashAttribute("msg", "권한이 없습니다.");
			return "redirect:/bbs";
		}
		
		model.addAttribute("map", map);
		
		return "bbs/update";
		
	}
	
	// url 패턴이 'path/bbs/writeAction' 일 경우
	@RequestMapping(value = "/writeAction", method = RequestMethod.POST)
	public String writeAction(Boarder boarder, MultipartFile file, HttpSession session, RedirectAttributes ra) throws Exception {
		
		String user_id = (String) session.getAttribute("user_id");
		
		if(user_id == null) {
			ra.addFlashAttribute("msg", "로그인이 필요합니다.");
			return "redirect:/login";
		}
		
		boarder.setWriter(user_id);
		bbsService.writeAction(boarder, file);
		
		return "redirect:/bbs";
	}
	
	// url 패턴이 'path/bbs/downloadAction' 일 경우
	@RequestMapping(value = "/downloadAction", method = RequestMethod.GET)
	public String downloadAction(UploadFile uploadFile, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		bbsService.downloadAction(request, response, uploadFile);
		
		return "redirect:/bbs/view?boarder_id=" + uploadFile.getBoarder_id();
	}
	
	// url 패턴이 'path/bbs/updateAction' 일 경우
	@RequestMapping(value = "/updateAction", method = RequestMethod.POST)
	public String updateAction(Boarder boarder, MultipartFile file) throws Exception {
		
		bbsService.updateAction(boarder, file);
		
		return "redirect:/bbs/view?boarder_id=" + boarder.getBoarder_id();
		
	}
	
	// url 패턴이 'path/bbs/deleteAction' 일 경우
	@RequestMapping(value = "/deleteAction", method = RequestMethod.GET)
	public String deleteAction(int boarder_id) throws Exception{
		bbsService.deleteAction(boarder_id);
		
		return "redirect:/bbs";
	}
	
	
	
}