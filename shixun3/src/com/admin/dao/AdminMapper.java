package com.admin.dao;

import java.util.List;

import com.entity.Admin;
import com.entity.User;

public interface AdminMapper {
	public int insertAdmin(String username,String password);
	public List<Admin>findAll();
	public List<User>findUser();
	
	public int deleteUser(int s1);
	
	public List<User> findUserPaging(int a,int b);
	
	public int insertuser(int userid,String username,String password,String sex,String photo,String profession,String home,String birthday,String label);
}	
