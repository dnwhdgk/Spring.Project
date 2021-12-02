package com.bbs.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.bbs.vo.Boarder;
import com.bbs.vo.UploadFile;

@Repository
public class BbsDAOImpl implements BbsDAO {

	@Inject
	SqlSession sqlSession;
	
	final String SESSION = "com.bbs.mappers.bbs";

	@Override
	public Boarder write(Boarder boarder) throws Exception {
		sqlSession.insert(SESSION + ".write", boarder);
		return boarder;
	}

	@Override
	public void fileUpload(UploadFile uploadFile) throws Exception {
		sqlSession.insert(SESSION + ".fileUpload", uploadFile);
	}

	@Override
	public Boarder getBoarder(Integer boarder_id) throws Exception {
		return sqlSession.selectOne(SESSION + ".getBoarder", boarder_id);
	}

	@Override
	public UploadFile getUploadFile(Integer boarder_id) throws Exception {
		return sqlSession.selectOne(SESSION + ".getUploadFile", boarder_id);
	}

	@Override
	public UploadFile getUploadFile(String file_realName) throws Exception {
		return sqlSession.selectOne(SESSION + ".getUploadFileReal", file_realName);
	}

	@Override
	public void updateBoarder(Boarder boarder) throws Exception {
		sqlSession.update(SESSION + ".updateBoarder", boarder);
	}

	@Override
	public void updateFile(UploadFile uploadFile) throws Exception {
		sqlSession.update(SESSION + ".updateFile", uploadFile);
	}

	@Override
	public int getMaxBoarder_id() throws Exception {
		
		return sqlSession.selectOne(SESSION + ".getMaxBoarder_id");
	}

	@Override
	public List<Boarder> getBbsList(int boarder_id) throws Exception {
		
		return sqlSession.selectList(SESSION + ".getBbsList",boarder_id);
	}

	@Override
	public void deleteBoarder(int boarder_id) throws Exception {
		sqlSession.update(SESSION + ".deleteBoarder",boarder_id);
		
	}
	
}