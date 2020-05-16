package com.market.type.controller;


import com.entity.Find_Photo;
import com.entity.Goods;
import com.entity.MarketComments;
import com.entity.Type;
import com.market.type.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

import java.io.File;
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
    @RequestMapping("/lei3")
    public List<MarketComments> e(@RequestParam("id") int goods_id) {
    	
    	return typeService.find3(goods_id);
    }
    @RequestMapping("/insert33")
    public String f(HttpServletRequest request,@RequestParam("content") String content,@RequestParam("time")String time,
    		@RequestParam("user_id")String user_id,@RequestParam("goods_id")String goods_id,
    		@RequestParam("r1")String r1,@RequestParam("r2")String r2,@RequestParam("r3")String r3,
    		@RequestParam(value="selectfile")List<MultipartFile> files) {
    	String rooString=request.getServletContext().getRealPath("/");
    	String img="";
    	try {
			for(int i = 0;i<files.size();i++) {
				FileCopyUtils.copy(files.get(i).getBytes(), new File(rooString+"upload",files.get(i).getOriginalFilename()));
				img= img+files.get(i).getOriginalFilename()+",";
				
			}
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
    	
    	System.out.println(content+time+img+r1+r2+r3+user_id+goods_id);
    	
    	typeService.insertComment(content,time,img,r1,r2,r3,Integer.valueOf(user_id),Integer.valueOf(goods_id));
    	
    	return "ok";
    }
}
