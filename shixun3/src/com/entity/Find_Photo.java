package com.entity;

public class Find_Photo {
	private int id;
	private int photoid;
	private String photo;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getPhotoid() {
		return photoid;
	}
	public void setPhotoid(int photoid) {
		this.photoid = photoid;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public Find_Photo(int photoid, String photo) {
		super();
		this.photoid = photoid;
		this.photo = photo;
	}
	public Find_Photo() {
		super();
	}
	
	
}
