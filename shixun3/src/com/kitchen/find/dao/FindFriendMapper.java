package com.kitchen.find.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.entity.FindFriend;
import com.entity.FindLable;
import com.entity.Find_Photo;

public interface FindFriendMapper {
	//全查
	public List<FindFriend> findAllFindFriend();
	//分页
	public List<FindFriend> findByPage(int page);
	public int findCount();
	//查lable
	public List<FindLable> findAllLable();
	
	public List<FindFriend> findFindFriendByLable(String lableid);
	
	public int saveFindFriend(FindFriend findFriend);
	
	public void saveFind_Photo(List<Find_Photo> find_Photos);
	
	public void insertComment(@Param("authorid")int authorid,@Param("comment")String comment,@Param("findfriendid")int findfriendid);
	
	public int findYouCount(int id);
	
	public void insertDianzan(@Param("num")int num,@Param("id")int id);
	
	public void insertGuanzhu(@Param("userid")int userid, @Param("findfriendid")int findfriendid);
	
	public int panduanGuanzhu(@Param("userid")int userid, @Param("findfriendid")int findfriendid);
	
	public void quxiaoGuanzhu(@Param("userid")int userid, @Param("findfriendid")int findfriendid);
	
	//web
	public void delectcomment(int[] data);
	public void delectuserlike(int[] data);
	public void delectphoto(int[] data);
	public void delectWeb(int[] data);
	
	//查询类型id
	public int selectlableid(String type);
	
	public int updateFindFriend(@Param("id")int id,@Param("findfriend")FindFriend findFriend);
	
	public int delectPhoto(int id);
	
}


	
