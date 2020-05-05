package com.example.a24168.myapplication.kitchen.find.entity;

public class Step {
	private int step_id;
	private int stepnum;
	private int menu_id;
	private String content;
	private String step_photo;
	public int getStep_id() {
		return step_id;
	}
	public void setStep_id(int step_id) {
		this.step_id = step_id;
	}
	public int getStepnum() {
		return stepnum;
	}
	public void setStepnum(int stepnum) {
		this.stepnum = stepnum;
	}
	public int getMenu_id() {
		return menu_id;
	}
	public void setMenu_id(int menu_id) {
		this.menu_id = menu_id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getStep_photo() {
		return step_photo;
	}
	public void setStep_photo(String step_photo) {
		this.step_photo = step_photo;
	}
}
