package com.admin.dao;

import java.util.List;

import com.entity.Admin;
import com.entity.User;

public interface AdminMapper {
	public int insertAdmin(String username,String password);
	public List<Admin>findAll();
	public List<User>findUser();
}	
