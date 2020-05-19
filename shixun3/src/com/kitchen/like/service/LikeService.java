package com.kitchen.like.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.entity.FindFriend;
import com.entity.FindLable;
import com.entity.Find_Photo;
import com.kitchen.find.dao.FindFriendMapper;
import com.kitchen.like.dao.LikeMapper;

@Service
public class LikeService {
	@Resource
	private LikeMapper likeMapper;
	
	public List<FindFriend> findall(int id){
		List<Integer> aIntegers = new ArrayList<Integer>(); 
		aIntegers = likeMapper.findIdByUserId(id);
		System.out.println(aIntegers);
		return likeMapper.findAllFindFriend(aIntegers);
	}
	
	
}
