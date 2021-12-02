package com.bbs.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.bbs.dao.BbsDAO;
import com.bbs.util.FileUpload;
import com.bbs.vo.Boarder;
import com.bbs.vo.Paging;
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
		
		dao.fileUpload(FileUpload.upload(boarder, file, PATH));
		
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

	@Override
	public void downloadAction(HttpServletRequest request, HttpServletResponse response, UploadFile uploadFile) throws Exception {
		
		uploadFile = dao.getUploadFile(uploadFile.getFile_realName());
		
		String browser = request.getHeader("User-Agent");
		
		// 파일의 인코딩 설정
		if(browser.contains("MSIE") || browser.contains("Trident") || browser.contains("Chrome")) {
			uploadFile.setFile_realName( URLEncoder.encode(uploadFile.getFile_realName(), "UTF-8").replaceAll("\\+", "%20") );
			uploadFile.setFile_name( URLEncoder.encode(uploadFile.getFile_name(), "UTF-8").replaceAll("\\+", "%20") );
		}
		else {
			uploadFile.setFile_realName( new String(uploadFile.getFile_realName().getBytes("UTF-8"), "ISO-8859-1") );
			uploadFile.setFile_name( new String(uploadFile.getFile_name().getBytes("UTF-8"), "ISO-8859-1") );
		}
		
		String file_name = PATH + uploadFile.getFile_realName();
		if (!new File(file_name).exists()) return;
		
		response.setContentType("application/octer-stream");
		response.setHeader("Content-Transfer-Encoding", "binary;");
		response.setHeader("Content-Disposition", "attachment; filename=\"" + uploadFile.getFile_name() + "\"");
		
		OutputStream    os  = response.getOutputStream();
		FileInputStream fis = new FileInputStream(file_name);
		
		int    ncount = 0;
		byte[] bytes = new byte[512];
		
		while((ncount = fis.read(bytes)) != -1) {
			os.write(bytes, 0, ncount);
		}
		
		fis.close();
		os.close();
		
	}

	@Override
	public void updateAction(Boarder boarder, MultipartFile file) throws Exception {
		
		// 게시물 수정 기능
		dao.updateBoarder(boarder);
		
		// 파일 수정 기능
		// 파일 객체가 비었을 때 (파일 입력하지 않았을 때)
		if(file.isEmpty()) return;
		
		// uploadFile을 데이터베이스에서 불러옴
		// 만약 null 이면 원본이 존재 X
		// null이 아니면 원본이 존재 O
		UploadFile uploadFile = dao.getUploadFile(boarder.getBoarder_id());
		
		if(uploadFile == null) {
			dao.fileUpload(FileUpload.upload(boarder, file, PATH));
		}
		else {
			new File(PATH + uploadFile.getFile_realName()).delete();
			dao.updateFile(FileUpload.upload(boarder, file, PATH));
		}
		
	}

	@Override
	public HashMap<String, Object> bbs(int pageNumber) throws Exception {
		
		HashMap<String,Object> map = new HashMap<String,Object>();
		
		int max =dao.getMaxBoarder_id(); 
		
		List<Boarder> list = dao.getBbsList(max - (pageNumber-1) * 10);
		Paging paging = new Paging(pageNumber,max);
		
		map.put("list",list);
		map.put("paging", paging);
		
		return map;
	}

	@Override
	public void deleteAction(int boarder_id) throws Exception {
		dao.deleteBoarder(boarder_id);
		
	}
	
}