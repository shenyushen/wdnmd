package com.example.a24168.myapplication.kitchen.find.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class FindFriend implements Serializable {
	private int id;
	private int author;
	private String theme;
	private String data;
	private String date;
	private int menuid;
	private int likenum;
	private String photo;
	private int type;
	private User user = new User();
	private List<Find_Photo> find_Photos = new ArrayList<>();
	private FindLable findLable = new FindLable();
	private List<FindComment> findComments = new ArrayList<>();

	public FindFriend() {
	}

	public FindFriend(int author, String theme, String data, String date, String photo, int type) {
		this.author = author;
		this.theme = theme;
		this.data = data;
		this.date = date;
		this.photo = photo;
		this.type = type;
	}

	public List<FindComment> getFindComments() {
		return findComments;
	}
	public void setFindComments(List<FindComment> findComments) {
		this.findComments = findComments;
	}
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
	public int getType() {
		return type;
	}
	public void setType(int type) {
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



	/*@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel arg0, int flags) {
		*//*private int id;
			private int author;
			private String theme;
			private String data;
			private String date;
			private int menuid;
			private int likenum;
			private String photo;*//*
		arg0.writeInt(id);
		arg0.writeInt(author);
		arg0.writeString(theme);
		arg0.writeString(data);
		arg0.writeString(date);
		arg0.writeInt(menuid);
		arg0.writeInt(likenum);
		arg0.writeString(photo);

	}
	// 1.必须实现Parcelable.Creator接口,否则在获取Person数据的时候，会报错，如下：
	// android.os.BadParcelableException:
	// Parcelable protocol requires a Parcelable.Creator object called  CREATOR on class com.um.demo.Person
	// 2.这个接口实现了从Percel容器读取Person数据，并返回Person对象给逻辑层使用
	// 3.实现Parcelable.Creator接口对象名必须为CREATOR，不如同样会报错上面所提到的错；
	// 4.在读取Parcel容器里的数据事，必须按成员变量声明的顺序读取数据，不然会出现获取数据出错
	// 5.反序列化对象
	public static final Parcelable.Creator<FindFriend> CREATOR = new Creator(){

		@Override
		public FindFriend createFromParcel(Parcel source) {
			// TODO Auto-generated method stub
			// 必须按成员变量声明的顺序读取数据，不然会出现获取数据出错
			FindFriend p = new FindFriend();
			*//*private int id;
			private int author;
			private String theme;
			private String data;
			private String date;
			private int menuid;
			private int likenum;
			private String photo;*//*
			p.setId(source.readInt());
			p.setAuthor(source.readInt());
			p.setTheme(source.readString());
			p.setData(source.readString());
			p.setDate(source.readString());
			p.setMenuid(source.readInt());
			p.setLikenum(source.readInt());
			p.setPhoto(source.readString());
			return p;
		}

		@Override
		public FindFriend[] newArray(int size) {
			// TODO Auto-generated method stub
			return new FindFriend[size];
		}
	};*/
}
