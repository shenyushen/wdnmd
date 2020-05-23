package com.example.a24168.myapplication.kitchen.like.entity;

import java.io.Serializable;

public class FindLable implements Serializable {
    private int id;
    private String lable;
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getLable() {
        return lable;
    }
    public void setLable(String lable) {
        this.lable = lable;
    }

}
