package com.example.a24168.myapplication.market.xiangqing.entity;



public class Comments {
    private int id;
    private String content;
    private String time;
    private String img;
    private String r1;
    private String r2;
    private String r3;
    private User1 user;
    private int goodsId;
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getR1() {
        return r1;
    }

    public void setR1(String r1) {
        this.r1 = r1;
    }

    public String getR2() {
        return r2;
    }

    public void setR2(String r2) {
        this.r2 = r2;
    }

    public String getR3() {
        return r3;
    }

    public void setR3(String r3) {
        this.r3 = r3;
    }

    public User1 getUser() {
        return user;
    }

    public void setUser(User1 user) {
        this.user = user;
    }

    public int getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(int goodsId) {
        this.goodsId = goodsId;
    }

    @Override
    public String toString() {
        return "Comments{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", time='" + time + '\'' +
                ", img='" + img + '\'' +
                ", r1='" + r1 + '\'' +
                ", r2='" + r2 + '\'' +
                ", r3='" + r3 + '\'' +
                ", user=" + user +
                ", goodsId=" + goodsId +
                '}';
    }
}
