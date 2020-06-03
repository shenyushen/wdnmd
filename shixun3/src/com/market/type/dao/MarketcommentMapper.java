package com.market.type.dao;

import com.entity.MarketCourt;
import com.entity.Market_order;
import com.entity.User;
import com.entity.marketComment;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestParam;

public interface MarketcommentMapper {
    public List<marketComment> findAll();
    public List<MarketCourt> findAllshopping(int id);
    public int change(@Param("user_id") int user_id,@Param("goods_id")int goods_id,@Param("goods_count")String goods_count);
    public int del(@Param("user_id") int user_id,@Param("goods_id")int goods_id);
    public int delall(@Param("user_id") int user_id);
    
    public int addorder(int user_id ,String address,String price,String context);
    
    
    public List<Market_order> findOrder(int a,int b);
    public int deleteorder(int id);
}
