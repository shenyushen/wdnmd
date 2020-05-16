package com.entity;

public class FindComment {
	private int id;
	private int authorid;
	private String comment;
	private int findfriendid;
	
	private User user = new User();
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getAuthorid() {
		return authorid;
	}
	public void setAuthorid(int authorid) {
		this.authorid = authorid;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public int getFindfriendid() {
		return findfriendid;
	}
	public void setFindfriendid(int findfriendid) {
		this.findfriendid = findfriendid;
	}
	
}
