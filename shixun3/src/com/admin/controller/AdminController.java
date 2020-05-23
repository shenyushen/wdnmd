package com.admin.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import com.admin.service.AdminService;
import com.entity.Admin;
import com.entity.menu;
import com.kitchen.recommend.service.menuservices;

import com.entity.Goods;
import com.entity.MarketComments;
import com.entity.User;
import com.market.type.service.TypeService;


@Controller
@RequestMapping("admin")
public class AdminController {
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
		List<MarketComments> comments = typeService.find11();
		
		
		
		
		List<Goods> good = new ArrayList<>();
		
		for(int i = 0; i < goods.size(); i++) {
			if (i == 5) break;
			good.add(goods.get(i));
		}
		for(int i = 0; i < admins.size(); i++) {
			if (admins.get(i).getUsername().equals(username) && admins.get(i).getPassword().equals(password)) {
				session.setAttribute("user", admins.get(i));
				List<menu> menus=menuservices.findall();
				session.setAttribute("menus",menus);
				session.setAttribute("yonghu", user);
				session.setAttribute("goods",good);
				session.setAttribute("comments", comments);
				session.setAttribute("comments_page",0);
				session.setAttribute("goods_page",0);
				session.setAttribute("goods_id", goods.size());
				return "redirect:../index.jsp";
			}
		}
		
		return null;
		//return "error.html";
	}
	
	@RequestMapping("/delete")
	@ResponseBody
	public String d(@RequestParam("id1") String id1, @RequestParam("id2") String id2,HttpSession session) {
		typeService.delete(Integer.valueOf(id1), Integer.valueOf(id2));
		List<MarketComments> comments = (List<MarketComments>) session.getAttribute("comments");
		for(int i = 0; i < comments.size(); i++) {
			if(comments.get(i).getUser().getId()== Integer.valueOf(id1) && comments.get(i).getGoodsId() == Integer.valueOf(id2)) {
				comments.remove(i);
				break;
			}
		}
		return "ok";
	}
	@RequestMapping("page")
	public String e(@RequestParam("page") String page,HttpSession session) {
		System.out.print(page);
		int a = Integer.valueOf(page)*8;
		int b = a+8;
		List<MarketComments> comments=typeService.find22(a, b);
		session.setAttribute("comments", comments);
		session.setAttribute("comments_page",Integer.valueOf(page));
		return "redirect:../marekt_comments.jsp";
	}
	@RequestMapping("page1")
	public String f(@RequestParam("page") String page,HttpSession session) {
		System.out.print(page);
		int a = Integer.valueOf(page)*5;
		int b = a+5;
		List<Goods> good=typeService.find33(a, b);
		
		session.setAttribute("goods", good);
		session.setAttribute("goods_page",Integer.valueOf(page));
		return "redirect:../market_goods.jsp";
	}
	@RequestMapping("/delete1")
	@ResponseBody
	public String h(@RequestParam("id") String id,HttpSession session) {
		typeService.deleteGood(Integer.valueOf(id));
		
		List<Goods> goods = (List<Goods>) session.getAttribute("goods");
		for(int i = 0; i < goods.size(); i++) {
			if(goods.get(i).getGoodsId() == Integer.valueOf(id)) {
				goods.remove(i);
				break;
			}
		}
		return "ok";
	}
	 @RequestMapping("/insert")
	 @ResponseBody
	    public String f(HttpServletRequest request,@RequestParam("content") String content,@RequestParam("time")String time,
	    		@RequestParam("user_id")String user_id,@RequestParam("goods_id")String goods_id,
	    		@RequestParam("r1")String r1,@RequestParam("r2")String r2,@RequestParam("r3")String r3,
	    		@RequestParam(value="selectfile")List<MultipartFile> files,HttpSession session) {
	    	String rooString=request.getServletContext().getRealPath("/");
	    	String img="";
	    	try {
				for(int i = 0;i<files.size();i++) {
					FileCopyUtils.copy(files.get(i).getBytes(), new File(rooString+"upload",files.get(i).getOriginalFilename()));
					img= img+files.get(i).getOriginalFilename()+",";
				}
			}catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
	    	System.out.println(content+time+img+r1+r2+r3+user_id+goods_id);
	    	typeService.insertComment(content,time,img,r1,r2,r3,Integer.valueOf(user_id),Integer.valueOf(goods_id));
	    	List<MarketComments> comments = typeService.find11();
	    	session.setAttribute("comments", comments);
	    	
	    	
	    	return "<script>parent.location.reload(); window.close();</script>";
	    }
	 
	 @RequestMapping("/insert1")
	 @ResponseBody
	 public String qq(HttpServletRequest request,@RequestParam("goods_id")String goods_id,@RequestParam("title")String title
			 ,@RequestParam("little_content") String little_content,@RequestParam("price")String price,@RequestParam(value="selectfile")List<MultipartFile> files,
			 @RequestParam("type_id")String type_id,@RequestParam("goods_type") String goods_type,@RequestParam("return_goods")String return_goods,
			 @RequestParam("if_freeshiiping") String if_freeshiiping,HttpSession session) {
		 String rooString=request.getServletContext().getRealPath("/");
	    	String img="";
	    	String a="";
	    	a = files.get(0).getOriginalFilename();
	    	try {
				for(int i = 0;i<files.size();i++) {
					FileCopyUtils.copy(files.get(i).getBytes(), new File(rooString+"upload",files.get(i).getOriginalFilename()));
					img= img+files.get(i).getOriginalFilename()+",";
				}
			}catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		 typeService.insert11(Integer.valueOf(goods_id)+1, title, little_content, 0,Double.parseDouble(price), a, Integer.valueOf(type_id));
		 typeService.insert22(img, goods_type, return_goods, if_freeshiiping, "4", Integer.valueOf(goods_id)+1);
		 List<Goods> good=typeService.find33(0, 5);
		 session.setAttribute("goods", good);
		 session.setAttribute("goods_id", Integer.valueOf(goods_id)+1);
		 
		 return "<script>parent.location.reload(); window.close();</script>";
	 }
}

