package com.user.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.entity.User;
import com.user.dao.usermapper;

@Service
public class userservice {
	@Resource
	private usermapper usermapper;
	public String insertuser(User user) {
		return usermapper.insertuser(user);
	}
	
	/*public List<User> queryUser(){
		return usermapper.queryUser();
	}*/
}
