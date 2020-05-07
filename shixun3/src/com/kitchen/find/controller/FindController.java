package com.kitchen.find.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.entity.FindFriend;
import com.entity.FindLable;
import com.kitchen.find.service.FindFriendService;

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
	
}
