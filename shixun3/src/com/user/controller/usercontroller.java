package com.user.controller;

import java.io.File;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.entity.Step;
import com.entity.User;
import com.entity.menu;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonObjectFormatVisitor;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.sun.org.apache.xalan.internal.xsltc.compiler.sym;
import com.user.dao.usermapper;
import com.user.service.userservice;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

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
	public User queryUser(Model model,
			@RequestParam("id") String b1,
			@RequestParam("password") String b2) {
	
		
		int sss=Integer.parseInt(b1);
		User users=usermapper.queryUser(sss, b2);
		if(users!=null) {
			return users;
		}else {
		User user1 = new User();
		user1.setId(-1);
			return user1;
		}
		
	}

	@RequestMapping("upload2")
	public void upload2(@RequestParam(value = "upfile") MultipartFile file,HttpServletRequest request) {
	
		String rooString=request.getServletContext().getRealPath("/");
		try {
			FileCopyUtils.copy(file.getBytes(), new File(rooString+"/pic",file.getOriginalFilename()));
			
			System.out.println("ddddddddd"+rooString);
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}
	
	@RequestMapping("updateuser")
	@ResponseBody
	public int updateUser(Model model,@RequestParam("id") String s1,@RequestParam("username") String s2,
			@RequestParam("sex") String s3,@RequestParam("profession") String s4,
			@RequestParam("home") String s5,@RequestParam("birthday") String s6,
			@RequestParam("label") String s7,@RequestParam("photo") String s8) {
		
		int ints1=Integer.parseInt(s1);
		int num=usermapper.updateUser(ints1, s2, s3, s4, s5, s6, s7, s8);
		
		return num;
	}
	
	@RequestMapping("secondquery")
	@ResponseBody
	public User secondquery(Model model,@RequestParam("id") int dd) {
		//int intdd=Integer.parseInt(dd);
		User user=usermapper.secondquery(dd);
		return user;
	}
}
