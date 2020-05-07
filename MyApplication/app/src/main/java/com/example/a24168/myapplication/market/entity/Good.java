package com.example.a24168.myapplication.market.entity;

public class Good {
    private int goodsId;
    private String title;
    private String littleContent;
    private int saleVolume;
    private double price;
    private String img;
    private Type type;
    private GoodsXiangqing good;
    public Good(int goodsId, String title, String littleContent, int saleVolume, double price, String img, Type type) {
        this.goodsId = goodsId;
        this.title = title;
        this.littleContent = littleContent;
        this.saleVolume = saleVolume;
        this.price = price;
        this.img = img;
        this.type = type;
    }


    public Good(int goodsId, String title, String littleContent, int saleVolume, double price, String img, Type type, GoodsXiangqing good) {
        this.goodsId = goodsId;
        this.title = title;
        this.littleContent = littleContent;
        this.saleVolume = saleVolume;
        this.price = price;
        this.img = img;
        this.type = type;
        this.good = good;
    }

    public GoodsXiangqing getGood() {
        return good;
    }

    public void setGood(GoodsXiangqing good) {
        this.good = good;
    }

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

    @Override
    public String toString() {
        return "Good{" +
                "goodsId=" + goodsId +
                ", title='" + title + '\'' +
                ", littleContent='" + littleContent + '\'' +
                ", saleVolume=" + saleVolume +
                ", price=" + price +
                ", img='" + img + '\'' +
                ", type=" + type +
                ", goodsXiangqing=" + good +
                '}';
    }
}
