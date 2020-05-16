package com.kitchen.find.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.entity.FindFriend;
import com.entity.FindLable;
import com.kitchen.find.dao.FindFriendMapper;

@Service
public class FindFriendService {
	@Resource
	private FindFriendMapper findFriendMapper;
	
	public List<FindFriend> findall(){
		return findFriendMapper.findAllFindFriend();
	}
	
	public List<FindLable> findAllLable(){
		return findFriendMapper.findAllLable();
	}
}
