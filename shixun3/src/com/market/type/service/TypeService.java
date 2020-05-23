package com.market.type.service;

import com.entity.Goods;
import com.entity.MarketComments;
import com.entity.MarketCourt;
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
    public int insert11(int goods_id, String title,String little_content,int count,double price,String img,int type_id) {
    	return typeMapper.insertGood(goods_id, title, little_content, count,price, img, type_id);
    }
    public int insert22(String goods_img,String goods_type,String return_goods,String if_freeshiiping,String goods_score,int goods_id) {
    	return typeMapper.insertGood1(goods_img, goods_type, return_goods, if_freeshiiping, goods_score, goods_id);
    }
    
    public List<MarketComments>find22(int a, int b){
    	return typeMapper.selectPage(a, b);
    }
    public List<Goods>find33(int a, int b){
    	return typeMapper.findPage(a, b);
    }
    public int deleteGood(int id) {
    	typeMapper.deleteGood(id);
    	return typeMapper.deleteGood1(id);
    }
    
    public List<MarketComments>find11(){
    	return typeMapper.findAllComment();
    }
    public int delete(int id1,int id2) {
    	return typeMapper.deleteComment(id1, id2);
    }
    public List<Goods> find(){
        return typeMapper.findAll();
    }
    public List<Goods>find1(int id){
        return typeMapper.findAllGoods(id);
    }
    public List<Goods> find2(int id){
    	return typeMapper.findGood(id);
    }
    public List<Goods> find3(){
    	return typeMapper.findAllGoods1();
    }
    public int insertCourt(int user_id,String goods_content,String goods_type,String goods_price,String goods_count,String goods_id) {
    	return typeMapper.insertCourt(user_id, goods_content, goods_type, goods_price, goods_count, goods_id);
    	
    }
    
    public List<MarketComments> find3(int id){
    	return typeMapper.findAllComments(id);
    }
    public int insertComment(String content,String time,String img,String r1,String r2,String r3,int user_id,int goods_id) {
    	return typeMapper.insertComment(content,time,img,r1,r2,r3,user_id,goods_id);
    }
    
    public List<MarketCourt> find4(int id){
    	return typeMapper.findAllCourt(id);
    }
    public int update(String type,String count,int id) {
    	return typeMapper.updateCourt(type, count, id);
    }
} 
