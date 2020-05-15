package com.kitchen.find.dao;

import java.util.List;

import com.entity.FindFriend;
import com.entity.FindLable;
import com.entity.Find_Photo;

public interface FindFriendMapper {
	//全查
	public List<FindFriend> findAllFindFriend();
	//查lable
	public List<FindLable> findAllLable();
	
	public List<FindFriend> findFindFriendByLable(String lableid);
	
	public int saveFindFriend(FindFriend findFriend);
	
	public void saveFind_Photo(List<Find_Photo> find_Photos);
}


	
