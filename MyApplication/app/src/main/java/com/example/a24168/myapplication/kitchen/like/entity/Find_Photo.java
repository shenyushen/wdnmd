package com.example.a24168.myapplication.kitchen.like.entity;

import java.io.Serializable;

public class Find_Photo implements Serializable {
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
}
