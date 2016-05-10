package com.estsoft.jblog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.estsoft.jblog.dao.BlogDao;
import com.estsoft.jblog.vo.BlogVo;
import com.estsoft.jblog.vo.CategoryVo;
import com.estsoft.jblog.vo.PostVo;

@Service
public class BlogService {
	@Autowired private BlogDao blogDao;

	public BlogVo getBlog(String email) {
		BlogVo vo = blogDao.getBlogByEmail(email);
		return vo;
	}

	public void updateLogo(long blog_id, String filePath) {
		blogDao.updateLogo(blog_id, filePath);	
	}

	public void updateAdminBasic(long blog_id, String title) {
		blogDao.updateTitle(blog_id, title);
		
	}

	public List<CategoryVo> getCategoryList(long blog_id) {
		List<CategoryVo> list = blogDao.getCategoryList(blog_id);
		return list;
	}

	public CategoryVo addCategory(long blog_id, String name, String description) {
		long category_id = blogDao.insertCategory(blog_id, name, description);
		CategoryVo categoryVo = blogDao.getCategoryById(category_id);
		return categoryVo;
	}

	public void deleteCategory(long category_id) {
		blogDao.deleteCategory(category_id);		
	}

	public void addPost(long category_id, String title, String content) {
		blogDao.insertPost(category_id,title,content);
		
	}

	public List<PostVo> getPost(long category_id) {	
		return blogDao.getPost(category_id);
	}
	
	}
