package com.entity;

import java.util.List;

public class Goods_x {
	private int goods_id;
	private String title;
	private String littile_content;
	private int sale_volume;
	private double price;
	private String []img;
	private String return_goods;
	private String if_freeshiiping;
	private String goods_score;
	private List<String> goods_type;
	private List<String> type_price;
	public int getGoods_id() {
		return goods_id;
	}
	public void setGoods_id(int goods_id) {
		this.goods_id = goods_id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getLittile_content() {
		return littile_content;
	}
	public void setLittile_content(String littile_content) {
		this.littile_content = littile_content;
	}
	public int getSale_volume() {
		return sale_volume;
	}
	public void setSale_volume(int sale_volume) {
		this.sale_volume = sale_volume;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String[] getImg() {
		return img;
	}
	public void setImg(String[] s) {
		this.img = s;
	}
	public String getReturn_goods() {
		return return_goods;
	}
	public void setReturn_goods(String return_goods) {
		this.return_goods = return_goods;
	}
	public String getIf_freeshiiping() {
		return if_freeshiiping;
	}
	public void setIf_freeshiiping(String if_freeshiiping) {
		this.if_freeshiiping = if_freeshiiping;
	}
	public String getGoods_score() {
		return goods_score;
	}
	public void setGoods_score(String goods_score) {
		this.goods_score = goods_score;
	}
	public List<String> getGoods_type() {
		return goods_type;
	}
	public void setGoods_type(List<String> goods_type) {
		this.goods_type = goods_type;
	}
	public List<String> getType_price() {
		return type_price;
	}
	public void setType_price(List<String> type_price) {
		this.type_price = type_price;
	}
	
	
	
}
