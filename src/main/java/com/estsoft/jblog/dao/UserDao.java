package com.estsoft.jblog.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.estsoft.jblog.vo.UserVo;

@Repository
public class UserDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	//1.insert
	public UserVo insert( UserVo vo ) {
			sqlSession.insert("user.insert", vo);
			return vo;
	}
	
	//2.get(UserVo vo)
	public UserVo get(UserVo vo){
		UserVo userVo = sqlSession.selectOne("user.getByVo", vo);
		return userVo;
	}	
	
	//3. get(String email)
	public UserVo get(String email){
		UserVo vo = sqlSession.selectOne("user.getByEmail", email);
		return vo;
	}
}
