package com.entity;

import java.util.Date;

public class marketComment {
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	private int id;
	private String market_name;
	private String market_comment;
	private String img;

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public String getMarket_name() {
		return market_name;
	}
	public void setMarket_name(String market_name) {
		this.market_name = market_name;
	}
	public String getMarket_comment() {
		return market_comment;
	}
	public void setMarket_comment(String market_comment) {
		this.market_comment = market_comment;
	}

}
