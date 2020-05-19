package com.admin.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.admin.dao.AdminMapper;
import com.entity.Admin;

@Service
public class AdminService {
	@Resource
	AdminMapper adminMapper;
	
	public int insert(String username,String password) {
		
		return adminMapper.insertAdmin(username, password);
	}

	public List<Admin>find(){
		return adminMapper.findAll();
	}
}
