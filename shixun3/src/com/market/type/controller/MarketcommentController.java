package com.market.type.controller;


import com.entity.Goods;
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
   


}
