package com.estsoft.jblog.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CategoryDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	//1.insert default category to new Blog
	public void insertDefault(long blog_id) {
		sqlSession.insert("category.insertDefault", blog_id);
	}

}
