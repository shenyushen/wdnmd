package com.example.a24168.myapplication.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class menu {
    private int menu_id;
    private String date;
    private String menu_photo;
    private String menu_name;
    private String type;
    private List<label> labels=new ArrayList<label>();

    public int getMenu_id() {
        return menu_id;
    }

    public void setMenu_id(int menu_id) {
        this.menu_id = menu_id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMenu_photo() {
        return menu_photo;
    }

    public void setMenu_photo(String menu_photo) {
        this.menu_photo = menu_photo;
    }

    public String getMenu_name() {
        return menu_name;
    }

    public void setMenu_name(String menu_name) {
        this.menu_name = menu_name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<label> getLabels() {
        return labels;
    }

    public void setLabels(List<label> labels) {
        this.labels = labels;
    }
    public String tointent(){
        return menu_id+","+date+","+menu_photo+","+menu_name+","+type;
    }
    @Override
    public String toString() {
        String label="";
        for(label a:labels){
            label+="label_id="+a.getLabel_id()+" label_name="+a.getLabel_name()+",";
        }



        return "menu{" +
                "menu_id=" + menu_id +
                ", date='" + date + '\'' +
                ", menu_photo='" + menu_photo + '\'' +
                ", menu_name='" + menu_name + '\'' +
                ", type='" + type + '\'' +
                ", labels={" + label +
                " } }";
    }
}
