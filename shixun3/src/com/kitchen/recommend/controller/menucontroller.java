package com.kitchen.recommend.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.entity.Step;
import com.entity.label;
import com.entity.menu;
import javax.servlet.http.HttpServletRequest;
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

	@RequestMapping("/addmenu")
	public void upload2(@RequestParam(value = "upfile") MultipartFile file,HttpServletRequest request) {
		String rooString=request.getServletContext().getRealPath("/");
		try {
			FileCopyUtils.copy(file.getBytes(), new File(rooString+"/upload",file.getOriginalFilename()));
			System.out.println(file.getOriginalFilename());
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}
	@RequestMapping("/addmenulist")
	public String addmenulist(Model model,
			@RequestParam("biaoti") String biaoti,
			@RequestParam("type") String type,
			@RequestParam("photo") String photo,
			@RequestParam("buzhou") String buzhou,
			@RequestParam("buzhouphoto") String buzhouphoto,
			@RequestParam("kouwei") String kouwei) {
		biaoti=biaoti.trim();
		photo=photo.trim();
		type=type.trim();
		String []a =buzhou.split("'");
		String [] b =buzhouphoto.split("'");
		String [] c =kouwei.split("'");
		List<label> labels=new ArrayList<label>();
		for(String as:c) {
			label l=new label();
			l.setLabel_id(Integer.valueOf(as));
			labels.add(l);
		}
		
		menu menu=new menu();
		menu.setDate(new Date().toLocaleString());
		menu.setMenu_photo(photo);
		menu.setMenu_name(biaoti);
		menu.setType(type);
//		添加menu到menu表
		int menuid=this.menuservices.addmenu(menu);
//		添加labelmenu直接添加到labelmenu里
		int panduan=this.menuservices.addlabelmenu(labels,menuid);
		System.out.println("pandaun "+panduan);
		
		List<Step> steps=new ArrayList<Step>();
		int stepnum=0;
		for(String as:b) {
			Step s=new Step();
			s.setContent(a[stepnum]);
			s.setStepnum(stepnum+1);
			s.setStep_photo(b[stepnum]);
			steps.add(s);
			stepnum++;
		}
		int panduan2=this.menuservices.addsteps(menuid,steps);
		
//		打印数据查看
		System.out.println("标题 "+biaoti);
		System.out.println("标题图片  "+photo);
		System.out.println("leixing  "+type);
		for(String as:a) {
			System.out.println("步骤描述 "+as);
		}
		for(String as:b) {
			System.out.println("步骤图片 "+as);
		}
		for(String as:c) {
			System.out.println("口味 "+as);
		}
		
		System.out.println("判断2 "+panduan2);
		int tag=0;
		if(panduan2!=0 && panduan!=0) {
			tag=1;
		}
		model.addAttribute("tag", tag);
		return "add";
	}

}
