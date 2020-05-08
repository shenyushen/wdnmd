package com.market.type.dao;

import com.entity.Goods;
import com.entity.Type;

import java.util.List;

public interface TypeMapper {
    public List<Goods> findAll();
    public List<Goods> findAllGoods(int type_id);
    public List<Goods> findGood(int id);
    
    public int insertCourt(int user_id,String goods_content,String goods_type,String goods_price,String goods_count,String goods_id);
}
