package com.bbs.service;

import org.springframework.web.multipart.MultipartFile;

import com.bbs.vo.Boarder;

public interface BbsService {
	public void writeAction(Boarder boarder,MultipartFile file) throws Exception;
}
