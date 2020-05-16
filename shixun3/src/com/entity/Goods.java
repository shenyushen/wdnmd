package com.entity;

public class Goods {
    private int goodsId;
    private String title;
    private String littleContent;
    private int saleVolume;
    private double price;
     private String img;
    private Type type;
    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public int getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(int goodsId) {
        this.goodsId = goodsId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLittleContent() {
        return littleContent;
    }

    public void setLittleContent(String littleContent) {
        this.littleContent = littleContent;
    }



    public int getSaleVolume() {
        return saleVolume;
    }

    public void setSaleVolume(int saleVolume) {
        this.saleVolume = saleVolume;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }



    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
