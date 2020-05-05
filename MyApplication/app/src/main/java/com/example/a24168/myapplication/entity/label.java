package com.example.a24168.myapplication.entity;

import java.util.ArrayList;
import java.util.List;

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

    public List<menu> getMenus() {
        return menus;
    }

    public void setMenus(List<menu> menus) {
        this.menus = menus;
    }
}
