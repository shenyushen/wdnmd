package com.user.dao;


import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.entity.User;

public interface usermapper {
	public String insertuser(User user);
	
	public User queryUser(@Param("s1")int s1,@Param("s2")String s2);
	
	public int updateUser(@Param("s1")int s1,@Param("s2")String s2,@Param("s3")String s3,
			@Param("s4")String s4,@Param("s5")String s5,@Param("s6")String s6,@Param("s7")String s7,@Param("s8")String s8);

	public User secondquery(@Param("m") int m);
}
