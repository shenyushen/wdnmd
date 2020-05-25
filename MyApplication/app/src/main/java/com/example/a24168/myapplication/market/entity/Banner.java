package com.example.a24168.myapplication.market.entity;

public class Banner {
    private int id;
   private int pic;

    public Banner(int id, int pic) {
        this.id = id;
        this.pic = pic;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPic() {
        return pic;
    }

    public void setPic(int pic) {
        this.pic = pic;
    }
}
