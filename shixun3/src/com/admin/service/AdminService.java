package com.admin.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.admin.dao.AdminMapper;
import com.entity.Admin;
import com.entity.User;

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
	public List<User> find1(){
		return adminMapper.findUser();
	}
	
	public int deleteuser(int s1) {
		return adminMapper.deleteUser(s1);
	}
	
	public List<User> findUPage(int a,int b){
		return adminMapper.findUserPaging(a, b);
	}
	
	public int insertuser(int userid,String username,String password,String sex,String photo,String profession,String home,String birthday,String label) {
		return adminMapper.insertuser(userid, username, password, sex, photo, profession, home, birthday, label);
	}
	
	public List<User>selectuser(int userid){
		return adminMapper.selectuser(userid);
	}
}
