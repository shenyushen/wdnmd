package com.market.type.dao;

import com.entity.Goods;
import com.entity.MarketComments;
import com.entity.MarketCourt;
import com.entity.Type;

import java.util.List;

public interface TypeMapper {
    public List<Goods> findAll();
    public List<Goods> findAllGoods(int type_id);
    public List<Goods> findGood(int id);
    public List<Goods> findAllGoods1();
    public int insertCourt(int user_id,String goods_content,String goods_type,String goods_price,String goods_count,String goods_id);
    public List<MarketComments> findAllComments(int id);
    public List<MarketCourt>findAllCourt(int id);
    public int updateCourt(String type,String count,int id);
    public int insertComment(String content,String time,String img,String r1,String r2,String r3,int user_id,int goods_id);
    
    public List<MarketComments> findAllComment();
    
    public int deleteComment(int id1,int id2);
    public List<MarketComments> selectPage(int a,int b);
    public List<Goods> findPage(int a,int b);
    public int deleteGood(int id);
    public int deleteGood1(int id);
    
    public int insertGood(int goods_id, String title,String little_content,int count,double price,String img,int type_id);
    public int insertGood1(String goods_img,String goods_type,String return_goods,String if_freeshiiping,String goods_score,int goods_id);
}
