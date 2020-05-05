package com.example.a24168.myapplication.kitchen.find.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * 
��������* ��Ŀ���ƣ�kitchen   
��������* �����ƣ�label   
��������* ��������   
��������* �����ˣ�sion   
��������* ����ʱ�䣺2020��4��19�� ����11:01:14   
��������* @version        
��������
 */
public class label {
private int label_id;
private String label_name;
private List<menu> menus =new ArrayList<menu>();
public int getLabel_id() {
	return label_id;
}
public void setLabel_id(int label_id) {
	this.label_id = label_id;
}
public String getLabel_name() {
	return label_name;
}
public void setLabel_name(String label_name) {
	this.label_name = label_name;
}
public label(int label_id, String label_name) {
	super();
	this.label_id = label_id;
	this.label_name = label_name;
}
public label() {
	super();
}
public List<menu> getMenus() {
	return menus;
}
public void setMenus(List<menu> menus) {
	this.menus = menus;
}

}
