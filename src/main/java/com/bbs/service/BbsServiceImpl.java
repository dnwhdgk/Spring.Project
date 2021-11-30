package com.bbs.service;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.inject.Inject;

import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.bbs.dao.BbsDAO;
import com.bbs.vo.Boarder;
import com.bbs.vo.UploadFile;

@Service
public class BbsServiceImpl implements BbsService {

	@Inject
	BbsDAO dao;
	
	static final String PATH = "F:\\eclipse\\workspace\\Spring_Project\\src\\main\\webapp\\resources\\upload\\";

	@Override
	public void writeAction(Boarder boarder, MultipartFile file) throws Exception {
		
		// 게시글 작성 기능
		boarder = dao.write(boarder);
		
		// 파일 업로드 기능
		// 파일 객체가 비었을 때 (파일 입력하지 않았을 때)
		if(file.isEmpty()) return;
		
		// 작성자가 올린 파일의 원본 이름
		String file_name = file.getOriginalFilename();
		// 파일의 확장자를 구함
		String suffix    = FilenameUtils.getExtension(file_name);
		// 랜덤한 중복되지 않는 ID 값을 받아옴
		UUID   uuid      = UUID.randomUUID();
		// 파일이 저장될 때 이름
		String file_realName = uuid + "." + suffix;
		// 파일 업로드
		file.transferTo(new File(PATH + file_realName));
		
		UploadFile uploadFile = new UploadFile();
		
		uploadFile.setBoarder_id(boarder.getBoarder_id());
		uploadFile.setFile_name(file_name);
		uploadFile.setFile_realName(file_realName);
		
		dao.fileUpload(uploadFile);
		
	}

	@Override
	public HashMap<String, Object> view(Integer boarder_id) throws Exception {
		
		Boarder    boarder    = dao.getBoarder(boarder_id);
		UploadFile uploadFile = dao.getUploadFile(boarder_id);
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("boarder", boarder);
		map.put("uploadFile", uploadFile);
		
		return map;
	}
	
}