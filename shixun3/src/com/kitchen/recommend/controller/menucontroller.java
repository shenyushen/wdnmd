package com.kitchen.recommend.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.entity.Step;
import com.entity.label;
import com.entity.menu;
import com.kitchen.recommend.service.menuservices;

@Controller
@RequestMapping("menu")
public class menucontroller {
	@Resource
	private menuservices menuservices;
//	浏览列表
	@RequestMapping("/list")
	public String findall(Model model) {
		List<menu> menus=menuservices.findall();
		model.addAttribute("menus",menus);
		return "list";
	}
//	筛选条件查找
	@RequestMapping("/findmenusbylabels")
	public String findallbylabel(Model model,@RequestParam("shaixuan") String label) {
		String [] afterlabels= label.split("'");
		String type=afterlabels[0].trim();
		System.out.println("type "+type+" "+type.equals("*"));
		String [] labels=null;
		if(!afterlabels[1].trim().equals("*")) {
			System.out.println("labels非空");
			labels=afterlabels[1].split(",");
		}
		List<menu> menus=menuservices.findallbylabel(type,labels);
		System.out.println("大小 "+menus.size());
		model.addAttribute("menus",menus);
		return "list";
	}
//	查找详细信息
	@RequestMapping("/details")
	public String findstepbymenuid(Model model,@RequestParam("menuid") String menuid) {
		List<Step> steps=menuservices.findstepbymenuid(menuid);
		model.addAttribute("steps",steps);
		return "step";
	}
//	搜索查找
	@RequestMapping("/findbyfind")
	public String findbyfind(Model model,@RequestParam("menuname") String menuname,@RequestParam("type") String type) {
		if(type.equals("1")) {
			List<menu> menus=menuservices.findbyfind(menuname);
			model.addAttribute("menus",menus);
			return "list";
		}
		return "list";	
	}
}
