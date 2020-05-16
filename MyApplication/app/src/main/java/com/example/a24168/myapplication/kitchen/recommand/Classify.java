package com.example.a24168.myapplication.kitchen.recommand;

import java.io.Serializable;
/*筛选条件构造器所用来判断是否被选中的自定义类*/
public class Classify implements Serializable {
    private boolean isChecked;
    private String str;

    public boolean isChecked() {
        return isChecked;
    }
    public Classify(String str){
        this.str=str;
    }
    public void setChecked(boolean checked) {
        isChecked = checked;
    }
    public String getStr() {
        return str;
    }
    public void setStr(String str1) {
        this.str = str1;
    }
}
