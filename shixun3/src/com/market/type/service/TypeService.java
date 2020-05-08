package com.market.type.service;

import com.entity.Goods;
import com.entity.Type;
import com.market.type.dao.TypeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = false)
public class TypeService {
    @Autowired
    TypeMapper typeMapper;

    public List<Goods> find(){
        return typeMapper.findAll();
    }
    public List<Goods>find1(int id){
        return typeMapper.findAllGoods(id);
    }
    public List<Goods> find2(int id){
    	return typeMapper.findGood(id);
    }
    public int insertCourt(int user_id,String goods_content,String goods_type,String goods_price,String goods_count,String goods_id) {
    	return typeMapper.insertCourt(user_id, goods_content, goods_type, goods_price, goods_count, goods_id);
    	
    }
    
}
