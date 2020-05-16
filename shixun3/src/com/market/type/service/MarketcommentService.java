package com.market.type.service;

import com.entity.marketComment;
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
    
}
