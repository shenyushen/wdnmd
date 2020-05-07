package com.market.type.dao;

import com.entity.Goods;
import com.entity.Type;

import java.util.List;

public interface TypeMapper {
    public List<Goods> findAll();
    public List<Goods> findAllGoods(int type_id);
    public List<Goods> findGood(int id);
}
