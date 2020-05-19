package com.admin.dao;

import java.util.List;

import com.entity.Admin;

public interface AdminMapper {
	public int insertAdmin(String username,String password);
	public List<Admin>findAll();
}	
