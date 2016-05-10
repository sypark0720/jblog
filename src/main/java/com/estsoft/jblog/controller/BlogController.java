package com.estsoft.jblog.controller;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.estsoft.jblog.annotation.Auth;
import com.estsoft.jblog.annotation.AuthUserBlog;
import com.estsoft.jblog.service.BlogService;
import com.estsoft.jblog.utility.FileUploader;
import com.estsoft.jblog.vo.BlogVo;
import com.estsoft.jblog.vo.CategoryVo;

@SessionAttributes("authUserBlog")
@Controller
@RequestMapping("/blog/{email:.+}")
public class BlogController {
	
	@Autowired private BlogService service;
	@Autowired private FileUploader fileUploader;
	
	@RequestMapping(value="", method=RequestMethod.GET)
	public String index(@RequestParam(value="sel", required=true, defaultValue = "0" ) int category_index, @PathVariable("email") String email, Model model){
		model.addAttribute("UserBlog",service.getBlog(email));
		List<CategoryVo> categoryList = service.getCategoryList(service.getBlog(email).getBlog_id());
		model.addAttribute("categoryList",categoryList);
		model.addAttribute("postList", service.getPost(categoryList.get(category_index).getCategory_id()));
		return "/blog/main";
	}
	
	//1. admin-basic
	@Auth
	@RequestMapping("/admin-basic")
	public String adminBasic(@PathVariable("email") String email, Model model){
		model.addAttribute("authUserBlog",service.getBlog(email));
		return "/blog/admin-basic";
	}
	
	@Auth
	@RequestMapping("/admin-basic-submit")
	public String adminBasicSubmit(@AuthUserBlog BlogVo authUserBlog, @RequestParam("title") String title, @PathVariable("email") String email){
		long blog_id = authUserBlog.getBlog_id();
		service.updateAdminBasic(blog_id, title);
		return "redirect:/blog/"+email+"/admin-basic";
	}
	
	//change Logo(Ajax)
	@Auth
	@RequestMapping(value = "/uploadLogo", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> uploadLogo(@AuthUserBlog BlogVo authUserBlog, MultipartHttpServletRequest request){
		
		Iterator<String> itr = request.getFileNames();
		String filename = null;
		Map<String, Object> map = new HashMap<String, Object>();
		if(itr.hasNext()){
			//fileUpload
			MultipartFile mpf = request.getFile(itr.next());
			filename = fileUploader.upload(mpf);		
			String filePath = "/logo-images/"+filename;
			
			//upload logo-url in DB
			long blog_id = authUserBlog.getBlog_id();
			service.updateLogo(blog_id, filePath);
			
			//return map
			map.put("result", "success");
			map.put("data", filePath);	
			return map;
		}else{	
		return map;
		}
	}
	
	//2. admin-category
	@Auth
	@RequestMapping("/admin-category")
	public String adminCategory(){
		return "/blog/admin-category";
	}
	
	
	@Auth
	@RequestMapping("/admin-category-add")
	@ResponseBody
	public Map<String, Object> adminCategoryAdd(
								@PathVariable("email") String email,
								@RequestParam("blog_id") long blog_id,
								@RequestParam(value = "name", required = true, defaultValue = "No Category Name") String name,
						   	    @RequestParam(value = "description", required = true, defaultValue = "No Description") String description,
								Model model){
		CategoryVo categoryVo = service.addCategory(blog_id, name, description);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", "success");
		map.put("data", categoryVo);
		map.put("listsize", service.getCategoryList(service.getBlog(email).getBlog_id()).size());
		return map;
	}
	
	@Auth
	@RequestMapping("/admin-category-delete")
	@ResponseBody
	public Map<String, Object> adminCategoryDelete(@RequestParam(value = "category_id", required = true, defaultValue = "-1") long category_id){
		System.out.println("BlogController 116:" +category_id);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", "success");
		return map;
	}
	
	@Auth
	@RequestMapping("/admin-category-ajaxlist")
	@ResponseBody
	public Map<String, Object> adminCategoryAjaxList(@PathVariable("email") String email){
		
		List<CategoryVo> list = service.getCategoryList(service.getBlog(email).getBlog_id());
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", "success");
		map.put("data", list);
		return map;
	}
	
	//3. admin-write
	@Auth
	@RequestMapping("/admin-write")
	public String adminWrite(@PathVariable("email") String email, Model model){
		model.addAttribute("categoryList", service.getCategoryList(service.getBlog(email).getBlog_id()));		
		return "/blog/admin-write";
	}
	
	@Auth
	@RequestMapping("/admin-write-submit")
	public String adminWriteSubmit(
			@PathVariable("email") String email,
			@RequestParam(value = "category_id") long category_id,
			@RequestParam(value = "title", required = true, defaultValue = "No Title") String title,
			@RequestParam(value = "content", required = true, defaultValue = "No Content") String content
			){
		service.addPost(category_id, title, content);
		return "redirect:/blog/"+email;
	}
}
