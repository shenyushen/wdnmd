package com.example.a24168.myapplication.kitchen.find.entity;

import android.graphics.Bitmap;

public class Show {

    private String image;
    private String title;

    public Show(String image, String title) {
        this.image = image;
        this.title = title;
    }

    public Show() {
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
