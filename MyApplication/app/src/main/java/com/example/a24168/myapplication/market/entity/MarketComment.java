package com.example.a24168.myapplication.market.entity;

public class MarketComment {

    private int id;
    private String market_name;
    private String market_comment;
    private String img;
    private String goods_id;
    public MarketComment(int id, String market_name, String market_comment, String img) {
        this.id = id;
        this.market_name = market_name;
        this.market_comment = market_comment;
        this.img = img;
    }

    public String getGoods_id() {
        return goods_id;
    }

    public void setGoods_id(String goods_id) {
        this.goods_id = goods_id;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getMarket_name() {
        return market_name;
    }
    public void setMarket_name(String market_name) {
        this.market_name = market_name;
    }
    public String getMarket_comment() {
        return market_comment;
    }
    public void setMarket_comment(String market_comment) {
        this.market_comment = market_comment;
    }
}
