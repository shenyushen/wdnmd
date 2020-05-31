package com.market.type.controller;


import com.entity.Goods;
import com.entity.MarketCourt;
import com.entity.Type;
import com.entity.marketComment;
import com.market.type.service.MarketcommentService;
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
@RequestMapping("marketcomment")
public class MarketcommentController {
    @Autowired
    MarketcommentService MarketcommentService;

    @RequestMapping("/123")
    public List<marketComment> a(){

        return MarketcommentService.find();
    }
   
    @RequestMapping("/1234")
    public List<MarketCourt> b(@RequestParam("id") int id ){
        return MarketcommentService.find2(id);
    }
    
    @RequestMapping("/12345")
    public int d(@RequestParam("user_id") int user_id ,@RequestParam("goods_id") int goods_id,@RequestParam("goods_count") String goods_count){
        MarketcommentService.changeNum(user_id,goods_id,goods_count);
        return 0;
    }
    
    @RequestMapping("/123456")
    public int f(@RequestParam("user_id") int user_id ,@RequestParam("goods_id") int goods_id){
        MarketcommentService.del(user_id,goods_id);
        return 0;
    }
    @RequestMapping("/1234567")
    public int g(@RequestParam("user_id") int user_id ){
        MarketcommentService.delall(user_id);
        return 0;
    }
    @RequestMapping("/12345addorder")
    public int add(@RequestParam("user_id") int user_id ,@RequestParam("address") String address,@RequestParam("price") String price,@RequestParam("context") String context){
        MarketcommentService.addorder(Integer.valueOf(user_id),address,price,context);
        return 0;
    }
    
    
}
