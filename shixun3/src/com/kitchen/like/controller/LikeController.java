package com.kitchen.like.controller;

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
import com.kitchen.like.service.LikeService;
import com.sun.javafx.image.impl.IntArgb;

@RestController
@RequestMapping("like")
public class LikeController {
	@Resource
	private LikeService likeService;
	
	@RequestMapping("/list")
	public List<FindFriend> findall(HttpServletRequest request) {
		if(request.getParameter("id")!=null) {
			int id = Integer.parseInt(request.getParameter("id"));
			List<FindFriend> findFriends=likeService.findall(id);
			return findFriends;
		}
		//String jsonString = JSONArray.fromObject(findFriends).toString();
		return null;
	}
	
}
