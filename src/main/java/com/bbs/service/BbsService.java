package com.bbs.service;

import java.util.HashMap;

import org.springframework.web.multipart.MultipartFile;

import com.bbs.vo.Boarder;

public interface BbsService {

	public void writeAction(Boarder boarder, MultipartFile file) throws Exception;
	public HashMap<String, Object> view(Integer boarder_id) throws Exception;
	
}