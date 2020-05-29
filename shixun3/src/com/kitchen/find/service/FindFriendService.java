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
	
	//分页查询
	public List<FindFriend> findByPage(int page){
		return findFriendMapper.findByPage(page);
	}
	public int findcount() {
		return findFriendMapper.findCount();
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

	public void saveGuanzhu(int userid, int findfriendid) {
		// TODO Auto-generated method stub
		findFriendMapper.insertGuanzhu(userid,findfriendid);
	}

	public int panduanGuanzhu(int userid, int findfriendid) {
		// TODO Auto-generated method stub
		return findFriendMapper.panduanGuanzhu(userid,findfriendid);
	}

	public void quxiaoGuanzhu(int userid, int findfriendid) {
		// TODO Auto-generated method stub
		findFriendMapper.quxiaoGuanzhu(userid,findfriendid);
	}
	
	public void delectWeb(int[] data) {
		// TODO Auto-generated method stub
		findFriendMapper.delectcomment(data);
		findFriendMapper.delectuserlike(data);
		findFriendMapper.delectphoto(data);
		findFriendMapper.delectWeb(data);
	}
	
	public int selectlableid(String type) {
		// TODO Auto-generated method stub
		return findFriendMapper.selectlableid(type);
	}
	
	public void updateFindFriend(int id,FindFriend findFriend){
		findFriendMapper.updateFindFriend(id,findFriend);
	}
	
	public void delectPhoto(int id){
		findFriendMapper.delectPhoto(id);
	}
}
