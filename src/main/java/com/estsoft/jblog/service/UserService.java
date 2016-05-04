package com.estsoft.jblog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.estsoft.jblog.dao.BlogDao;
import com.estsoft.jblog.dao.CategoryDao;
import com.estsoft.jblog.dao.UserDao;
import com.estsoft.jblog.vo.UserVo;

@Service
public class UserService {
	@Autowired private UserDao userDao;
	@Autowired private BlogDao blogDao;
	@Autowired private CategoryDao categoryDao;
	
	public void join(UserVo vo){
		UserVo uservo = userDao.insert(vo);
		long blogID = blogDao.insert(uservo.getUser_id(), uservo.getUser_name());		
		categoryDao.insertDefault(blogID);		
	}
	
	public UserVo login(UserVo vo){
		UserVo authUser = userDao.get(vo);
		return authUser;
	}

	public UserVo getUser(String email) {
		UserVo vo = userDao.get(email);
		return vo;
	}
		
}
