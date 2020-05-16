package com.entity;

import java.util.ArrayList;
import java.util.List;

public class FindFriend {
	private int id;
	private int author;
	private String theme;
	private String data;
	private String date;
	private int menuid;
	private int likenum;
	private String photo;
	private String type;
	private User user = new User();
	private List<Find_Photo> find_Photos = new ArrayList<>();
	private FindLable findLable = new FindLable();
	
	public FindLable getFindLable() {
		return findLable;
	}
	public void setFindLable(FindLable findLable) {
		this.findLable = findLable;
	}
	public List<Find_Photo> getFind_Photos() {
		return find_Photos;
	}
	public void setFind_Photos(List<Find_Photo> find_Photos) {
		this.find_Photos = find_Photos;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getAuthor() {
		return author;
	}
	public void setAuthor(int author) {
		this.author = author;
	}
	public String getTheme() {
		return theme;
	}
	public void setTheme(String theme) {
		this.theme = theme;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public int getMenuid() {
		return menuid;
	}
	public void setMenuid(int menuid) {
		this.menuid = menuid;
	}
	public int getLikenum() {
		return likenum;
	}
	public void setLikenum(int likenum) {
		this.likenum = likenum;
	}
	
	
}
