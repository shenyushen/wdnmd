package com.entity;

import java.util.Date;
/**
 * 
��������* ��Ŀ���ƣ�kitchen  
��������* �����ƣ�course   
��������* ��������   
��������* �����ˣ�sion   
��������* ����ʱ�䣺2020��4��19�� ����10:58:49   
��������* @version        
��������
 */
public class course {
private int course_id;
private Date time;
private String mv_name;
private String mv_account;
private String mv_path;
private int if_vip;
public int getCourse_id() {
	return course_id;
}
public void setCourse_id(int course_id) {
	this.course_id = course_id;
}

public Date getTime() {
	return time;
}
public void setTime(Date time) {
	this.time = time;
}
public String getMv_name() {
	return mv_name;
}
public void setMv_name(String mv_name) {
	this.mv_name = mv_name;
}
public String getMv_account() {
	return mv_account;
}
public void setMv_account(String mv_account) {
	this.mv_account = mv_account;
}
public String getMv_path() {
	return mv_path;
}
public void setMv_path(String mv_path) {
	this.mv_path = mv_path;
}
public int getIf_vip() {
	return if_vip;
}
public void setIf_vip(int if_vip) {
	this.if_vip = if_vip;
}

public course() {
	super();
}

}
