package com.estsoft.jblog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.estsoft.jblog.dao.BlogDao;
import com.estsoft.jblog.vo.BlogVo;

@Service
public class BlogService {
	@Autowired private BlogDao blogDao;

	public BlogVo getBlog(String email) {
		BlogVo vo = blogDao.getBlogByEmail(email);
		return vo;
	}

	public void updateLogo(String logoaddr) {
		blogDao.updateLogo(logoaddr);		
	}

}
