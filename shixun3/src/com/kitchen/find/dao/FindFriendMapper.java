package com.kitchen.find.dao;

import java.util.List;

import com.entity.FindFriend;
import com.entity.FindLable;

public interface FindFriendMapper {
	//全查
	public List<FindFriend> findAllFindFriend();
	//查lable
	public List<FindLable> findAllLable();
}


	
