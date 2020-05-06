package com.user.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.entity.Step;
import com.entity.User;
import com.entity.menu;
import com.sun.org.apache.xalan.internal.xsltc.compiler.sym;
import com.user.dao.usermapper;
import com.user.service.userservice;

@Controller
@RequestMapping("user")
public class usercontroller {
	
	@Resource
	private userservice userservice;
	@Resource
	usermapper usermapper;
//	浏览列表
	@RequestMapping("/insertusers")
	public String insertuser(Model model,
			@RequestParam("id") int a1,
			@RequestParam("password") String a2,
			@RequestParam("username") String a3) {
		
		User user=new User();
		user.setId(a1);
		user.setPassword(a2);
		user.setUsername(a3);
		user.setVip(1);
		usermapper.insertuser(user);
		System.out.println(a1+a2+a3);
		String a="true";
		model.addAttribute("a",a);
		return "ss";
	}
	
	@RequestMapping("queryuser")
	@ResponseBody
	public String queryUser(Model model,
			@RequestParam("id") String b1,
			@RequestParam("password") String b2) {
		int index=0;
		int sss=Integer.parseInt(b1);
		User users=usermapper.queryUser(sss, b2);
		
		if(users!=null) {
			return users.getUsername();
		}else {
			return "aa";
		}
		
			
		
		/*String a="true";
		model.addAttribute("a", a);
		return "ss";*/
	}


}
