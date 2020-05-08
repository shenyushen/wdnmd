package com.market.type.controller;


import com.entity.Goods;
import com.entity.Type;
import com.market.type.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("type")
public class TypeController {
    @Autowired
    TypeService typeService;

    @RequestMapping("/lei")
    public List<Goods> a(){

        return typeService.find();
    }
    @RequestMapping("/lei1")

    public List<Goods> b(@RequestParam("id") String id ){
        System.out.println(id);
        return typeService.find1(Integer.valueOf(id));
    }

    @RequestMapping("/lei2")
    public List<Goods> c(@RequestParam("id") String id){
    	return typeService.find2(Integer.valueOf(id));
    	
    }
    @RequestMapping("insert")
    public String d(@RequestParam("user_id") String user_id,@RequestParam("goods_content") String goods_content,
    		@RequestParam("goods_type")String goods_type, @RequestParam("goods_price")String goods_price,
    		@RequestParam("goods_count")String goods_count,@RequestParam("goods_id")String goods_id) {
    	typeService.insertCourt(Integer.valueOf(user_id), goods_content, goods_type, goods_price, goods_count, goods_id);
    	return "ok";
    }

    
}
