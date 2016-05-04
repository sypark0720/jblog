package com.estsoft.jblog.controller;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.estsoft.jblog.service.BlogService;
import com.estsoft.jblog.utility.FileUploader;

@Controller
@RequestMapping("/blog")
public class BlogController {
	
	@Autowired private BlogService service;
	@Autowired private FileUploader fileUploader;
	
	@RequestMapping("/{email:.+}")
	public String index(@PathVariable("email") String email, Model model){
		model.addAttribute("vo",service.getBlog(email));
		return "/blog/main";
	}
	
	@RequestMapping("/admin-basic")
	public String adminBasic(){
		return "/blog/admin-basic";
	}
	
	@RequestMapping("/admin-basic-submit")
	public String adminBasicSubmit(){
		return "";
	}
	
	//change Logo: Ajax로
	@RequestMapping(value = "/uploadLogo", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> uploadLogo(MultipartHttpServletRequest request){
		//추가해줄것!
		//service.updateLogo(logoaddr);
		
		Iterator<String> itr = request.getFileNames();
		String filename = null;
		Map<String, Object> map = new HashMap<String, Object>();
		if(itr.hasNext()){
			//fileUpload
			MultipartFile mpf = request.getFile(itr.next());
			filename = fileUploader.upload(mpf);			
			
			//return map
			map.put("result", "success");
			map.put("data", "/logo-images/"+filename);	
			return map;
		}else{	
		return map;
		}
	}
}
