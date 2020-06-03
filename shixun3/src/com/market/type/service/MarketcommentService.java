package com.market.type.service;

import com.entity.marketComment;
import com.entity.MarketCourt;
import com.entity.Market_order;
import com.entity.User;
import com.market.type.dao.MarketcommentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = false)
public class MarketcommentService {
    @Autowired
    MarketcommentMapper MarketcommentMapper;

    public List<marketComment> find(){
        return MarketcommentMapper.findAll();
    }
    public List<MarketCourt> find2(int id){
    	return MarketcommentMapper.findAllshopping(id);
    }
    public int changeNum(int user_id,int goods_id,String goods_count) {
    	return MarketcommentMapper.change(user_id, goods_id, goods_count);
    }
    public int del(int user_id,int goods_id) {
    	return MarketcommentMapper.del(user_id, goods_id);
    }
    public int delall(int user_id) {
    	return MarketcommentMapper.delall(user_id);
    }
    public int addorder(int user_id,String price,String address,String context) {
    	return MarketcommentMapper.addorder(user_id,address,price,context);
    }
    
    public List<Market_order> findOrder(int a,int b){
		return MarketcommentMapper.findOrder(a, b);
	}
    public int deleteorder(int id) {
    	return MarketcommentMapper.deleteorder(id);
    }
    
}
