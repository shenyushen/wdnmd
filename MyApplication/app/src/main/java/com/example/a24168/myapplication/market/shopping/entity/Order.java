package com.example.a24168.myapplication.market.shopping.entity;

import com.example.a24168.myapplication.market.entity.Good;


public class Order {
    private int userId;
    private String goodsContent;
    private String goodsType;
    private String goodsPrice;
    private String goodsCount;
    private String goodsId;
    private int id;

    public Boolean getChecked() {
        return checked;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }

    private Boolean checked;
    public Good getGoods() {
        return goods;
    }

    public void setGoods(Good goods) {
        this.goods = goods;
    }

    private Good goods;

    public int getUserId() {
        return userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }
    public String getGoodsContent() {
        return goodsContent;
    }
    public void setGoodsContent(String goodsContent) {
        this.goodsContent = goodsContent;
    }
    public String getGoodsType() {
        return goodsType;
    }
    public void setGoodsType(String goodsType) {
        this.goodsType = goodsType;
    }
    public String getGoodsPrice() {
        return goodsPrice;
    }
    public void setGoodsPrice(String goodsPrice) {
        this.goodsPrice = goodsPrice;
    }
    public String getGoodsCount() {
        return goodsCount;
    }
    public void setGoodsCount(String goodsCount) {
        this.goodsCount = goodsCount;
    }
    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }


}
