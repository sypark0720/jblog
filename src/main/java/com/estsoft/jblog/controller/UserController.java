package com.estsoft.jblog.controller;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.estsoft.jblog.service.UserService;
import com.estsoft.jblog.vo.UserVo;

@Controller
@RequestMapping("/user")
public class UserController {
	private static final Log LOG = LogFactory.getLog( UserController.class );
	
	@Autowired
	private UserService userService;	
	
	@RequestMapping("/joinform")
	public String joinform(){
		return "/user/joinform";
	}
	
	@RequestMapping(value = "/join", method = RequestMethod.POST)
	public String join( @ModelAttribute @Valid UserVo vo, BindingResult result, Model model ) {
		//form valication
		   if ( result.hasErrors() ) {
		       model.addAllAttributes( result.getModel() );
		       model.addAttribute("vo", vo);
		       return "/user/joinform";}
				
		   userService.join(vo);
		   return "redirect:/user/joinsuccess";
		}

	
	@RequestMapping("/joinsuccess")
	public String joinSuccess(){
		return "/user/joinsuccess";
	}
	
	@RequestMapping("/loginform")
	public String loginform(){
		return "/user/loginform";
	}
	
	@RequestMapping(value = "/logincheck", method = RequestMethod.POST)
	public String logincheck( @ModelAttribute @Valid UserVo vo, BindingResult result, Model model, RedirectAttributes redirectAttr ) {
		//form valication
		   if ( result.hasErrors() ) {
		       model.addAllAttributes( result.getModel() );
		       model.addAttribute("vo", vo);
		       return "/user/loginform";}		   
		   System.out.println("UserController 64");
		   redirectAttr.addAttribute("email", vo.getEmail());
		   redirectAttr.addAttribute("password", vo.getPassword());
		   return "redirect:/user/login";
		}
	
	@RequestMapping("/checkemail")
	@ResponseBody 
	public Map<String, Object> checkEmail(@RequestParam(value = "email", required =true, defaultValue = "") String email){
		UserVo vo = userService.getUser(email); 
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", "success");
		map.put("data", vo==null);		
		return map;
	}	
}

