package com.admin.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import com.admin.service.AdminService;
import com.entity.Admin;
import com.entity.menu;
import com.kitchen.recommend.dao.labelmapper;
import com.kitchen.recommend.service.menuservices;

import com.entity.Goods;
import com.entity.Step;
import com.entity.User;
import com.entity.label;
import com.market.type.service.TypeService;


@Controller
@RequestMapping("admin")
public class AdminController {
	int thispage=1;
	int maxpage=0;
	int maxsize=0;
	List<menu> menus=new ArrayList<menu>();
	int menunum=-1;
	List<Step> steps= new ArrayList<Step>();
	@Resource
	AdminService adminService;
	@Resource
	private menuservices menuservices;
	@Resource
	TypeService typeService;
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
				menus=menuservices.findall();
				session.setAttribute("menus",menus);
				fenye(1,session);
				session.setAttribute("yonghu", user);
				session.setAttribute("goods",good);
				
				return "redirect:../index.jsp";
			}
		}
		
		return null;
		//return "error.html";
	}
//	添加菜单功能
	@RequestMapping(value="menuadd",method=RequestMethod.POST)
	public String menuadd(@RequestParam("menu_name")String menu_name,@RequestParam("menu_photo") MultipartFile photo,@RequestParam("kouwei") String kouwei,@RequestParam("type")String type,HttpSession session,HttpServletRequest request) {
		System.out.println(menu_name+" "+photo.getName()+" "+type+" "+kouwei);
		String [] c=kouwei.split(",");
		List<label> labels=new ArrayList<label>();
		for(String as:c) {
			label l=new label();
			l.setLabel_id(Integer.valueOf(as));
			labels.add(l);
		}
		
//		上传文件
		String rootPath=request.getServletContext().getRealPath("/");
		
		try {
			if(!photo.isEmpty()) {
				FileCopyUtils.copy(photo.getBytes(), 
						new File(rootPath+"/upload",photo.getOriginalFilename()));
			}
			else {
				System.out.println("不存在");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		menu menu=new menu();
		menu.setDate(new Date().toLocaleString());
		menu.setMenu_photo(photo.getOriginalFilename());
		menu.setMenu_name(menu_name);
		if(type.equals("mi")) {
			type="米";
		}
		else if(type.equals("mian")) {
			type="面";
		}
		else{
			type="菜";
		}
		menu.setType(type);
//		添加menu到menu表
		int menuid=this.menuservices.addmenu(menu);
//		添加labelmenu直接添加到labelmenu里
		int panduan=this.menuservices.addlabelmenu(labels,menuid);
		menus=menuservices.findall();
		session.setAttribute("menus",menus);
		fenye(thispage, session);
		return "ok";
		
	}
//	修改menu
	@RequestMapping(value="menuedit",method=RequestMethod.GET)
	public String menuedit(@RequestParam("count")int count,HttpSession session) {
		System.out.println(menus.get(count).getMenu_name());
		session.setAttribute("menu", menus.get(count));
		return "redirect:../menu_edit.html";
	}
	@RequestMapping(value="menuedit",method=RequestMethod.POST)
	public String menuedit(@RequestParam("menu_name")String menu_name,@RequestParam("menu_photo") MultipartFile photo,@RequestParam("kouwei") String kouwei,@RequestParam("type")String type,HttpSession session,HttpServletRequest request) {
		menu menu=(com.entity.menu) session.getAttribute("menu");
//		上传文件
		String rootPath=request.getServletContext().getRealPath("/");
		
		try {
			if(!photo.isEmpty()) {
				FileCopyUtils.copy(photo.getBytes(), 
						new File(rootPath+"/upload",photo.getOriginalFilename()));
				menu.setMenu_photo(photo.getOriginalFilename());
			}
			else {
				System.out.println("不存在");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
//		更改属性
		menu.setDate(new Date().toLocaleString());
		if(!menu_name.equals("")) {
			menu.setMenu_name(menu_name);
		}
		if(type.equals("mi")) {
			type="米";
		}
		else if(type.equals("mian")) {
			type="面";
		}
		else{
			type="菜";
		}
		menu.setType(type);
		int n=menuservices.editmenu(menu);
//		构建label
		String [] c=kouwei.split(",");
		List<label> labels=new ArrayList<label>();
		for(String as:c) {
			label l=new label();
			l.setLabel_id(Integer.valueOf(as));
			labels.add(l);
		}
		
		int m=menuservices.deletelabel(menu.getMenu_id());
		int m1=menuservices.addlabelmenu(labels, menu.getMenu_id());
		menus=menuservices.findall();
		session.setAttribute("menus",menus);
		fenye(thispage, session);
		return "ok";
	}
//	删除菜单功能
	@RequestMapping("menudelete")
	public void menudelete(@RequestParam("count")int count,HttpSession session) {
		System.out.println(menus.get(count).getMenu_name()+menus.get(count).getMenu_id());
		menuservices.deletelabel(menus.get(count).getMenu_id());
		menuservices.deletemenu(menus.get(count).getMenu_id());
		menus.remove(count);
		session.setAttribute("menus",menus);
		fenye(thispage, session);
	}
	@RequestMapping("menudeletecheck")
	public void menudeletecheck(@RequestParam("value")String value,HttpSession session) {
		if(!value.equals("")) {
			System.out.println(value.substring(0,value.length()));
			String [] count=value.substring(0,value.length()).split(",");
			
			System.out.println("长度 "+count.length);
			for(String a:count) {
				System.out.println("这是字符串"+a);
				menuservices.deletelabel(menus.get(Integer.valueOf(a)).getMenu_id());
				menuservices.deletemenu(menus.get(Integer.valueOf(a)).getMenu_id());
				
			}
			menus=menuservices.findall();
			session.setAttribute("menus",menus);
			fenye(thispage, session);
			
		}
		else {
			System.out.println("字符穿为空");
		}
		
	}
	
	
//	step操作
//	step操作
//	step操作
//	step操作
//	step操作
	
	
//	浏览step
	@RequestMapping("menustep")
	public String menustep(@RequestParam("count")int count,HttpSession session) {
		
		steps=menuservices.findstepbymenuid(menus.get(count).getMenu_id()+"");
		menunum=count;
		session.setAttribute("steps", steps);
		if(steps.size()!=0) {
			for(Step a:steps) {
				System.out.println(a.getContent());
			}
		}
		return "redirect:../stepadmin.jsp";
		
	}
//	添加步骤
	@RequestMapping(value="stepadd",method=RequestMethod.POST)
	public String stepadd(@RequestParam("context")String context,@RequestParam("step_photo") MultipartFile photo,HttpSession session,HttpServletRequest request) {
		
		Step newstep=new Step();
//		上传文件
		String rootPath=request.getServletContext().getRealPath("/");
		
		try {
			if(!photo.isEmpty()) {
				FileCopyUtils.copy(photo.getBytes(), 
						new File(rootPath+"/upload",photo.getOriginalFilename()));
					newstep.setStep_photo(photo.getOriginalFilename());
			}
			else {
				System.out.println("不存在");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		if(menunum!=-1) {
			System.out.println(context+" "+photo.getOriginalFilename()+" "+menunum);
		}
		menus=(List<menu>) session.getAttribute("menus");
		System.out.println(menus.get(menunum).getMenu_name()+" "+menus.get(menunum).getMenu_id()+" ");
		
		newstep.setMenu_id(menus.get(menunum).getMenu_id());
		
		newstep.setContent(context);
		if(steps.size()!=0) {
			newstep.setStepnum(steps.get(steps.size()-1).getStepnum()+1);
		}
		else {
			newstep.setStepnum(1);
		}
		int m=menuservices.addstep(newstep);
		steps=menuservices.findstepbymenuid(menus.get(menunum).getMenu_id()+"");
		if(steps.size()!=0) {
			for(Step a:steps) {
				System.out.println(a.getContent());
			}
		}
		session.setAttribute("steps", steps);
		return "ok";
	}
//	删除步骤功能
	@RequestMapping("stepdelete")
	public void stepdelete(@RequestParam("count")int count,HttpSession session) {
		System.out.println(steps.get(count).getContent()+steps.get(count).getStep_id());
		int m=menuservices.deletestep(steps.get(count).getStep_id());
		steps.remove(count);
		session.setAttribute("steps", steps);
		
	}
//	修改step
	@RequestMapping(value="stepedit",method=RequestMethod.GET)
	public String stepedit(@RequestParam("count")int count,HttpSession session) {
		System.out.println("修改"+steps.get(count).getContent());
		session.setAttribute("step", steps.get(count));
		return "redirect:../step_edit.html";
	}
	@RequestMapping(value="stepedit",method=RequestMethod.POST)
	public String stepedit(@RequestParam("context")String context,@RequestParam("step_photo") MultipartFile photo,HttpSession session,HttpServletRequest request) {
		Step step= (com.entity.Step) session.getAttribute("step");
//		上传文件
		String rootPath=request.getServletContext().getRealPath("/");
		
		try {
			if(!photo.isEmpty()) {
				FileCopyUtils.copy(photo.getBytes(), 
						new File(rootPath+"/upload",photo.getOriginalFilename()));
				step.setStep_photo(photo.getOriginalFilename());
			}
			else {
				System.out.println("不存在");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
//		更改属性
		step.setContent(context);
		int n=menuservices.editstep(step);
		steps=menuservices.findstepbymenuid(menus.get(menunum).getMenu_id()+"");
		session.setAttribute("steps",steps);
		return "ok";
		
	}
	
//	多选删除
	@RequestMapping("stepdeletecheck")
	public void stepdeletecheck(@RequestParam("value")String value,HttpSession session) {
		if(!value.equals("")) {
			System.out.println(value.substring(0,value.length()));
			String [] count=value.substring(0,value.length()).split(",");
			for(String a:count) {
				menuservices.deletestep(steps.get(Integer.valueOf(a)).getStep_id());
			}
			steps= menuservices.findstepbymenuid(menus.get(menunum).getMenu_id()+"");
			session.setAttribute("steps",steps);
		}
		else {
			System.out.println("字符穿为空");
		}
	}
	
//	筛选
	@RequestMapping("selectmenu")
	public String selectmenu(@RequestParam("menu_name")String menu_name,@RequestParam(value="kouwei",required=false) String kouwei,@RequestParam("type")String type,HttpSession session,HttpServletRequest request) {
		if(type.equals("mi")) {
			type="米";
		}
		else if(type.equals("mian")) {
			type="面";
		}
		else{
			type="菜";
		}
		String[] s = null;
		if(kouwei!=null) {
			s=kouwei.split(",");
		}
		
		System.out.println("筛选"+menu_name+"试试"+kouwei+"试试"+type);
		menus=menuservices.selectmenu(menu_name,s,type);
		session.setAttribute("menus", menus);
		return "redirect:../tuijian.jsp";
	}
	
	@RequestMapping("resit")
	public String resit(HttpSession session) {
		menus=menuservices.findall();
		session.setAttribute("menus",menus);
		fenye(thispage, session);
		return "redirect:../tuijian.jsp";
	}

//	分页查询
	@RequestMapping("fenye")
	public String fenye(@RequestParam("page")int page,HttpSession session) {
		thispage=page;
		maxsize=menuservices.findall().size();
		if(maxsize%6!=0) {
			maxpage=maxsize/6+1;
		}
		else {
			maxpage=maxsize/6;
		}
		
		List<Integer> pagelist=new ArrayList<Integer>();
		if(page-1>=1) {
			pagelist.add(page-1);
		}
		pagelist.add(page);
		if(page+1<=maxpage) {
			pagelist.add(page+1);
		}
		if(maxpage>=3) {
			if(pagelist.size()!=3) {
				if(pagelist.get(0)==1) {
					pagelist.add(3);
				}
				else {
					pagelist.add(0, pagelist.get(0)-1);
				}
			}
		}
		
		for(int a:pagelist) {
			System.out.println("page: "+a);
		}
		
		menus=menuservices.fenye((page-1)*6,6);
		session.setAttribute("menus",menus);
		session.setAttribute("maxpage", maxpage);
		session.setAttribute("thispage", thispage);
		session.setAttribute("pagelist", pagelist);
		return "redirect:../tuijian.jsp";
	}
}

