package com.kitchen.find.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.entity.FindFriend;
import com.entity.FindLable;
import com.entity.Find_Photo;
import com.kitchen.find.dao.FindFriendMapper;

@Service
public class FindFriendService {
	@Resource
	private FindFriendMapper findFriendMapper;
	
	public List<FindFriend> findall(){
		return findFriendMapper.findAllFindFriend();
	}
	
	public List<FindLable> findAllLable(){
		return findFriendMapper.findAllLable();
	}
	
	public List<FindFriend> findFindFriendByLable(String type){
		return findFriendMapper.findFindFriendByLable(type);
	}
	
	public int saveFindFriend(FindFriend findFriend){
		findFriendMapper.saveFindFriend(findFriend);
		int findFriendid = findFriend.getId();
		return findFriendid;
	}
	
	public void saveFind_Photo(List<Find_Photo> find_Photos) {
		findFriendMapper.saveFind_Photo(find_Photos);
	}
	
	public void saveComment(int authorid,String comment,int findfriendid) {
		findFriendMapper.insertComment(authorid,comment,findfriendid);
	}
	
	public int findCount(int id) {
		return findFriendMapper.findYouCount(id);
	}

	public void saveDianzan(int num,int id) {
		findFriendMapper.insertDianzan(num,id);
	}
	
}
