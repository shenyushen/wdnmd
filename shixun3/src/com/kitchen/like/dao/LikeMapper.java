package com.kitchen.like.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.entity.FindFriend;
import com.entity.FindLable;
import com.entity.Find_Photo;

public interface LikeMapper {
	//全查
	public List<FindFriend> findAllFindFriend(List<Integer> aIntegers);

	public List<Integer> findIdByUserId(int id);
	
}


	
