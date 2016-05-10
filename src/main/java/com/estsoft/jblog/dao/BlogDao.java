package com.estsoft.jblog.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.estsoft.jblog.vo.BlogVo;
import com.estsoft.jblog.vo.CategoryVo;
import com.estsoft.jblog.vo.PostVo;

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
	

	public void updateLogo(long blog_id, String filePath) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("blog_id", blog_id);
		map.put("filePath", filePath);
		sqlSession.update("blog.updateLogo",map);	
	}

	public void updateTitle(long blog_id, String title) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("blog_id", blog_id);
		map.put("title", title);
		sqlSession.update("blog.updateTitle",map);
		
	}

	public List<CategoryVo> getCategoryList(long blog_id) {
		List<CategoryVo> list = sqlSession.selectList("blog.selectCategoryList", blog_id);
		return list;
	}

	public long insertCategory(long blog_id, String name,
			String description) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("blog_id", blog_id);
		map.put("name", name);
		map.put("description", description);
		sqlSession.insert("blog.insertCategory", map);
		return (long) map.get("category_id");
	}

	public CategoryVo getCategoryById(long category_id){
		CategoryVo categoryVo = sqlSession.selectOne("blog.getCategoryById", category_id);
		return categoryVo;
	}

	public void deleteCategory(long category_id) {
		sqlSession.delete("blog.deleteCategory", category_id);
		
	}

	public void insertPost(long category_id, String title, String content) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("category_id", category_id);
		map.put("title", title);
		map.put("content", content);
		sqlSession.delete("blog.insertPost", map);
		
	}

	public List<PostVo> getPost(long category_id) {
		List<PostVo> list = sqlSession.selectList("blog.selectPost", category_id);
		return list;
		
	}
	
	
	
}
