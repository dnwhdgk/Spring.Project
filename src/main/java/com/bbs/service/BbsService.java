package com.bbs.service;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;

import com.bbs.vo.Boarder;
import com.bbs.vo.UploadFile;

public interface BbsService {

	public void writeAction(Boarder boarder, MultipartFile file) throws Exception;
	public HashMap<String, Object> view(Integer boarder_id) throws Exception;
	public void downloadAction(HttpServletRequest request, HttpServletResponse response, UploadFile uploadFile) throws Exception;
	public void updateAction(Boarder boarder, MultipartFile file) throws Exception;
	public HashMap<String, Object> bbs(int pageNumber) throws Exception;
	public void deleteAction(int boarder_id) throws Exception;
	
}
