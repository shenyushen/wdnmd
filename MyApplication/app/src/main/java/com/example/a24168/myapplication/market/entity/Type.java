package com.example.a24168.myapplication.market.entity;

public class Type {
    private int id;
    private String type;
    private int img;

    public Type(int id, String type, int img) {
        this.id = id;
        this.type = type;
        this.img = img;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }
}
