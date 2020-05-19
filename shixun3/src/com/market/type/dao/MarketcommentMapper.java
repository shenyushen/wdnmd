package com.market.type.dao;

import com.entity.MarketCourt;
import com.entity.marketComment;
import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface MarketcommentMapper {
    public List<marketComment> findAll();
    public List<MarketCourt> findAllshopping(int id);
    public int change(@Param("user_id") int user_id,@Param("goods_id")int goods_id,@Param("goods_count")String goods_count);
    public int del(@Param("user_id") int user_id,@Param("goods_id")int goods_id);
    public int delall(@Param("user_id") int user_id);
}
