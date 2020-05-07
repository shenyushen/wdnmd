package com.example.a24168.myapplication.market.entity;

public class GoodsXiangqing {
    private int goodId;
    private String goods_img;
    private String goods_type;
    private String return_goods;
    private String if_freeshiiping;
    private String goods_score;
    public int getGoodId() {
        return goodId;
    }
    public void setGoodId(int goodId) {
        this.goodId = goodId;
    }
    public String getGoods_img() {
        return goods_img;
    }
    public void setGoods_img(String goods_img) {
        this.goods_img = goods_img;
    }
    public String getGoods_type() {
        return goods_type;
    }
    public void setGoods_type(String goods_type) {
        this.goods_type = goods_type;
    }
    public String getReturn_goods() {
        return return_goods;
    }
    public void setReturn_goods(String return_goods) {
        this.return_goods = return_goods;
    }
    public String getIf_freeshiiping() {
        return if_freeshiiping;
    }
    public void setIf_freeshiiping(String if_freeshiiping) {
        this.if_freeshiiping = if_freeshiiping;
    }
    public String getGoods_score() {
        return goods_score;
    }
    public void setGoods_score(String goods_score) {
        this.goods_score = goods_score;
    }

    @Override
    public String toString() {
        return "GoodsXiangqing{" +
                "goodId=" + goodId +
                ", goods_img='" + goods_img + '\'' +
                ", goods_type='" + goods_type + '\'' +
                ", return_goods='" + return_goods + '\'' +
                ", if_freeshiiping='" + if_freeshiiping + '\'' +
                ", goods_score='" + goods_score + '\'' +
                '}';
    }
}
