package com.kitchen.find.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.entity.FindFriend;
import com.entity.FindLable;
import com.entity.Find_Photo;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kitchen.find.service.FindFriendService;
import com.sun.javafx.image.impl.IntArgb;

@RestController
@RequestMapping("find")
public class FindController {
	@Resource
	private FindFriendService findFriendService;
	
	@RequestMapping("/list")
	public List<FindFriend> findall() {
		List<FindFriend> findFriends=findFriendService.findall();
		
		//String jsonString = JSONArray.fromObject(findFriends).toString();
		return findFriendService.findall();
	}
	
	@RequestMapping("/lable")
	public List<FindLable> findAllLable() {
		List<FindLable> findLables=findFriendService.findAllLable();
		return findLables;
	}
	
	@RequestMapping("/select")
	public List<FindFriend> findByLable(HttpServletRequest request) {
		String action = request.getParameter("action");
		System.out.println(action);
		if(action!=null) {
			List<FindFriend> findFriends = findFriendService.findFindFriendByLable(action);
			return findFriends;
		}
		return findFriendService.findall();
		
	}
	
	@RequestMapping("/pic")
	public void saveFindFriend(HttpServletRequest request,@RequestParam(value="selectfile")List<MultipartFile> files) {
		String action = request.getParameter("data");
		System.out.println(action);
		
		ObjectMapper om = new ObjectMapper();
		FindFriend findFriend = new FindFriend();
		    try {
		    	findFriend = om.readValue(action,FindFriend.class);
		    	findFriend.setAuthor(1);
		    	findFriend.setMenuid(1);
		    	findFriend.setType(5);
		    	System.out.println(findFriend.getData());
		    } catch (JsonParseException e) {
		        e.printStackTrace();
		    } catch (JsonMappingException e) {
		        e.printStackTrace();
		    } catch (IOException e) {
		        e.printStackTrace();
		    }
		
		String rooString=request.getServletContext().getRealPath("/");
		System.out.println(rooString);
		int findFriendid = findFriendService.saveFindFriend(findFriend);
		System.out.println(findFriendid+"zhangxuewei");
		findFriend.setId(findFriendid);
		List<Find_Photo> findPhotos = new ArrayList<Find_Photo>();
		try {
			for(int i = 0;i<files.size();i++) {
				FileCopyUtils.copy(files.get(i).getBytes(), new File(rooString+"pic",files.get(i).getOriginalFilename()));
				Find_Photo find_Photo = new Find_Photo(findFriendid,files.get(i).getOriginalFilename());
				findPhotos.add(find_Photo);
				System.out.println(find_Photo+"fdsagdassfa");
			}
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		findFriend.setFind_Photos(findPhotos);
		
		findFriendService.saveFind_Photo(findFriend.getFind_Photos());
		
	}
	
	@RequestMapping("/comment")
	public void saveComment(HttpServletRequest request) {
		String authorid = request.getParameter("authorid");
		String comment = request.getParameter("comment");
		String findfriendid = request.getParameter("findfriendid");
		
		if(authorid!=null&&findfriendid!=null) {
			
			int a = Integer.parseInt(authorid);
			int b = Integer.parseInt(findfriendid);
			
			findFriendService.saveComment(a,comment,b);
		}
		
	}
	
	@RequestMapping("/count")
	public String findCommentCount(HttpServletRequest request) {
		int id = Integer.parseInt(request.getParameter("id"));
		
		int a = findFriendService.findCount(id);
		return a+"";
	}
	
	@RequestMapping("/dianzan")
	public void saveDianzan(HttpServletRequest request) {
		int num = Integer.parseInt(request.getParameter("dianzan"));
		int id = Integer.parseInt(request.getParameter("id"));
		System.out.println(num+"zxc"+id);
		findFriendService.saveDianzan(num,id);
	}
	
}
