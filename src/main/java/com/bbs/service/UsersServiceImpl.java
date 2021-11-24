package com.bbs.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.bbs.dao.UsersDAO;

@Service
public class UsersServiceImpl implements UsersService {

	@Inject
	UsersDAO dao;
	
	@Override
	public int check_id(String user_id) throws Exception {
		dao.check_id(user_id);
		return 0;
	}

}
