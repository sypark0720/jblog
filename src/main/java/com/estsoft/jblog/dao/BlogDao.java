package com.estsoft.jblog.dao;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.estsoft.jblog.vo.BlogVo;

@Repository
public class BlogDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	//1.insert new Blog
	public long insert(long user_id, String user_name) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("user_id", user_id);
		map.put("title", user_name+"'s Blog");
		sqlSession.insert("blog.insert", map);	
		return (long) map.get("blog_id");
	}

	public BlogVo getBlogByEmail(String email) {
		BlogVo vo = sqlSession.selectOne("blog.getBlogByEmail", email);
		return vo;
	}

	public void updateLogo(String logoaddr) {
		sqlSession.update("blog.updateLogo", logoaddr);			
	}
	
	
	
}
