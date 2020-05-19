package com.example.a24168.myapplication.kitchen.like.entity;

import java.util.Date;

/**
 * 
��������* ��Ŀ���ƣ�kitchen   
��������* �����ƣ�menu_comment   
��������* ��������   
��������* �����ˣ�sion   
��������* ����ʱ�䣺2020��4��19�� ����11:02:23   
��������* @version        
��������
 */
public class menu_comment {
	private int menu_comment_id;
	private String content;
	private Date time;
	
	public int getMenu_comment_id() {
		return menu_comment_id;
	}
	public void setMenu_comment_id(int menu_comment_id) {
		this.menu_comment_id = menu_comment_id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public menu_comment() {
		super();
	}
	
}
