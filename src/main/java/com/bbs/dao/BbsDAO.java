package com.bbs.dao;

import java.util.List;

import com.bbs.vo.Boarder;
import com.bbs.vo.UploadFile;

public interface BbsDAO {

	public Boarder write(Boarder boarder) throws Exception;
	public void fileUpload(UploadFile uploadFile) throws Exception;
	public Boarder getBoarder(Integer boarder_id) throws Exception;
	public UploadFile getUploadFile(Integer boarder_id) throws Exception;
	public UploadFile getUploadFile(String file_realName) throws Exception;
	public void updateBoarder(Boarder boarder) throws Exception;
	public void updateFile(UploadFile uploadFile) throws Exception;
	public int getMaxBoarder_id()throws Exception;
	public List<Boarder> getBbsList(int boarder_id) throws Exception;
	public void deleteBoarder(int boarder_id) throws Exception;
}