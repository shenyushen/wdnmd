package com.kitchen.find.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.entity.FindFriend;
import com.entity.Find_Photo;
import com.entity.User;
import com.kitchen.find.service.FindFriendService;
import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;


@Controller
@RequestMapping("/findweb")
public class FindWeb {
	@Resource
	private FindFriendService findFriendService;
	//发现
	/*@RequestMapping("/findorder")
	public String findFindFriend(HttpSession session) {
		List<FindFriend> findFriends = new ArrayList<FindFriend>();
		findFriends = findFriendService.findall();
		
		List <User> users =new ArrayList<User>();
		users = findFriendService.finduser();
		session.setAttribute("user",users);
		session.setAttribute("find",findFriends);
		return "redirect:../order-list2.jsp";
	}*/
	
	
	@RequestMapping("/delect")
	public String delectFindFriend(HttpSession session,@RequestParam(value="value")int[] data) {
		findFriendService.delectWeb(data);
		return "redirect:../order-list2.jsp";
			 
	}
	
	@RequestMapping(value = "/saveform",method = RequestMethod.POST)
	public String saveform(HttpSession session,HttpServletRequest request,@RequestParam(value="myfile")List<MultipartFile> files) {
		String theme = request.getParameter("theme");
		String data = request.getParameter("neirong");
		String date = request.getParameter("riqi");
		String type = request.getParameter("leixing");
		FindFriend findFriend = new FindFriend(123,theme,data,date,1,0,files.get(0).getOriginalFilename(),Integer.parseInt(type));
		//保存findfriend，并获取主键
		int findFriendid = findFriendService.saveFindFriend(findFriend);
		
		List<Find_Photo> findPhotos = new ArrayList<Find_Photo>();
		String rooString=request.getServletContext().getRealPath("/");
		System.out.println(rooString);
		try {
			for(int i = 0;i<files.size();i++) {
				FileCopyUtils.copy(files.get(i).getBytes(), new File(rooString+"pic",files.get(i).getOriginalFilename()));
				Find_Photo find_Photo = new Find_Photo(findFriendid,files.get(i).getOriginalFilename());
				findPhotos.add(find_Photo);
			}
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		findFriend.setFind_Photos(findPhotos);
		
		findFriendService.saveFind_Photo(findFriend.getFind_Photos());
		
		return "redirect:../order-list2.jsp";
	}
	
	@RequestMapping(value="/update",method=RequestMethod.GET)
	public void updataFindFriend(HttpSession session,@RequestParam(value="value")String[] data) {
		
		int type = findFriendService.selectlableid(data[6]);
		FindFriend findFriend = new FindFriend();
		findFriend.setId(Integer.parseInt(data[0]));
		findFriend.setAuthor(Integer.parseInt(data[1]));
		findFriend.setTheme(data[2]);
		findFriend.setData(data[3]);
		findFriend.setDate(data[4]);
		findFriend.setLikenum(Integer.parseInt(data[5]));
		System.out.println("abc");
		session.setAttribute("ff",findFriend);
		session.setAttribute("type", type);
		
	}
	
	@RequestMapping(value = "/myupdate",method = RequestMethod.POST)
	public String updateform(HttpSession session,HttpServletRequest request,@RequestParam(value="myfile")List<MultipartFile> files) {
		String theme = request.getParameter("theme");
		String data = request.getParameter("neirong");
		String date = request.getParameter("riqi");
		String type = request.getParameter("leixing");
		String id = request.getParameter("findid");
		FindFriend findFriend = new FindFriend(123,theme,data,date,1,0,files.get(0).getOriginalFilename(),Integer.parseInt(type));
		
		findFriendService.updateFindFriend(Integer.parseInt(id),findFriend);
	
		List<Find_Photo> findPhotos = new ArrayList<Find_Photo>();
		String rooString=request.getServletContext().getRealPath("/");
		System.out.println(rooString);
		try {
			for(int i = 0;i<files.size();i++) {
				FileCopyUtils.copy(files.get(i).getBytes(), new File(rooString+"pic",files.get(i).getOriginalFilename()));
				Find_Photo find_Photo = new Find_Photo(Integer.parseInt(id),files.get(i).getOriginalFilename());
				findPhotos.add(find_Photo);
			}
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		findFriend.setFind_Photos(findPhotos);
		findFriendService.delectPhoto(Integer.parseInt(id));
		findFriendService.saveFind_Photo(findFriend.getFind_Photos());
		return null;
	}
	
	@RequestMapping("/findbypage")
	public String findFindFriendByPage(HttpSession session,HttpServletRequest request) {
		int pager = Integer.parseInt(request.getParameter("page"));
		int page = (pager-1)*5;
		int pnum = findFriendService.findcount();
		List <User> users =new ArrayList<User>();
		users = findFriendService.finduser();
		session.setAttribute("user",users);
		
		List<FindFriend> findFriends = new ArrayList<FindFriend>();
		findFriends = findFriendService.findByPage(page);
		session.setAttribute("find",findFriends);
		session.setAttribute("p", pnum/5+1);
		return "redirect:../order-list2.jsp";
	}
	
	//条件筛选
	@RequestMapping("/selectbyitem")
	public String selectByItem(HttpSession session,HttpServletRequest request) {
		String author = request.getParameter("modules");
		String theme = request.getParameter("theme");
		String date = request.getParameter("date");
		String[] a = request.getParameterValues("choosebox");
		
		String like = request.getParameter("open");  //判断是否为空
		List<FindFriend> findFriends = new ArrayList<FindFriend>();
		findFriends = findFriendService.selectByItem(author,theme,date,a,like);
		session.setAttribute("find", findFriends);
		
		return "redirect:../order-list2.jsp";
	}
	
	
	
}
