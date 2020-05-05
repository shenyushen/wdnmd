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
}
