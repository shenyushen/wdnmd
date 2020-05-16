package com.user.dao;


import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.entity.User;

public interface usermapper {
	public String insertuser(User user);
	
	public User queryUser(@Param("s1")int s1,@Param("s2")String s2);
}
