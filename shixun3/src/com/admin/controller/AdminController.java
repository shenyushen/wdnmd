package com.admin.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.admin.service.AdminService;
import com.entity.Admin;
<<<<<<< HEAD
import com.entity.menu;
import com.kitchen.recommend.service.menuservices;
=======
import com.entity.Goods;
import com.entity.User;
import com.market.type.service.TypeService;
>>>>>>> a661484829fcb48c5c15e5fd6ed3891839a39a9a

@Controller
@RequestMapping("admin")
public class AdminController {
	@Resource
	AdminService adminService;
	@Resource
<<<<<<< HEAD
	private menuservices menuservices;
=======
	TypeService typeService;
>>>>>>> a661484829fcb48c5c15e5fd6ed3891839a39a9a
	@RequestMapping("login")
	public String login(@RequestParam("username")String username,@RequestParam("password") String password,HttpSession session) {
		
		List<Admin> admins = adminService.find();
		List<User> user = adminService.find1();
		List<Goods> goods = typeService.find3();
		List<Goods> good = new ArrayList<>();
		for(int i = 0; i < goods.size(); i++) {
			if (i == 10) break;
			good.add(goods.get(i));
		}
		for(int i = 0; i < admins.size(); i++) {
			if (admins.get(i).getUsername().equals(username) && admins.get(i).getPassword().equals(password)) {
				session.setAttribute("user", admins.get(i));
<<<<<<< HEAD
				List<menu> menus=menuservices.findall();
				session.setAttribute("menus",menus);
=======
				session.setAttribute("yonghu", user);
				session.setAttribute("goods",good);
				
>>>>>>> a661484829fcb48c5c15e5fd6ed3891839a39a9a
				return "redirect:../index.jsp";
			}
		}
		
		
		
		return null;
		//return "error.html";
	}
}

