package com.bbs.util;

import java.io.File;
import java.util.UUID;

import org.apache.commons.io.FilenameUtils;
import org.springframework.web.multipart.MultipartFile;

import com.bbs.vo.Boarder;
import com.bbs.vo.UploadFile;

public class FileUpload {

	public static UploadFile upload(Boarder boarder, MultipartFile file, String PATH) throws Exception {
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
		
		return uploadFile;
	}
	
	
	
}