package com.entity;

public class Type {
    private int id;
    private String type;
    private String img;

    public Type(int id, String type, String img) {
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

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    @Override
    public String toString() {

        return "Type{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", img='" + img + '\'' +
                '}';
    }
}
