<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<!DOCTYPE html>
<html class="x-admin-sm">
    
    <head>
        <meta charset="UTF-8">
        <title>欢迎页面-X-admin2.2</title>
        <meta name="renderer" content="webkit">
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
        <meta name="viewport" content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8,target-densitydpi=low-dpi" />
        <link rel="stylesheet" href="./css/font.css">
        <link rel="stylesheet" href="./css/xadmin.css">
        <script type="text/javascript" src="./lib/layui/layui.js" charset="utf-8"></script>
        <script type="text/javascript" src="./js/xadmin.js"></script>
        <!-- 让IE8/9支持媒体查询，从而兼容栅格 -->
        <!--[if lt IE 9]>
            <script src="https://cdn.staticfile.org/html5shiv/r29/html5.min.js"></script>
            <script src="https://cdn.staticfile.org/respond.js/1.4.2/respond.min.js"></script>
        <![endif]--></head>
    
    <body onunload="window.opener.location.reload();">
        <div class="layui-fluid">
            <div class="layui-row">
                <form class="layui-form" action="/shixun3/admin/insert1" method="post" enctype="multipart/form-data" accept-charset="UTF-8">
                	<input id="goods_id" name="goods_id" type="hidden" value ="${goods_id }"/>
                	<div class="layui-form-item">
                        <label for="L_email" class="layui-form-label">
                            <span class="x-red">*</span>商品标题</label>
                        <div class="layui-input-inline">
                            <input type="text" id="title" name="title"  required=""  autocomplete="off" class="layui-input"></div>
                        
                    </div>
                    <div class="layui-form-item">
                        <label for="L_email" class="layui-form-label">
                            <span class="x-red">*</span>商品简介</label>
                        <div class="layui-input-inline">
                            <input type="text" id="little_content" name="little_content"  required=""  autocomplete="off" class="layui-input"></div>
                        
                    </div>
                    <div class="layui-form-item">
                        <label for="username" class="layui-form-label">
                            <span class="x-red">*</span>价格</label>
                        <div class="layui-input-inline">
                            <input type="text" id="price" name="price" required="" lay-verify="required" autocomplete="off" class="layui-input"></div>
                    </div>
                    <div class="layui-form-item">
                        <label for="username" class="layui-form-label">
                            <span class="x-red">*</span>商品图片</label>
                        <div class="layui-input-inline">
                            <input type="file" id="selectfile" name="selectfile" multiple="multiple" accept="image/*" required="" lay-verify="required" autocomplete="off" class="layui-input"></div>
                    </div>
                    
                    <div class="layui-form-item">
                        <label for="username" class="layui-form-label">
                            <span class="x-red">*</span>商品种类</label>
                        <div class="layui-input-inline">
                            <select id="type_id" name="type_id" class="valid">  
                            		<option value="0">烘培</option>
                            		<option value="1">果蔬生鲜</option>
                            		<option value="2">器具</option>
                            		<option value="3">领券</option>
                            		<option value="4">方便食品</option>
                            		<option value="5">饮品茶酒</option>
                            		<option value="6">零食</option>
                            		<option value="7">腌制腊肉</option>
                            		<option value="8">南北干货</option>
                            		<option value="9">进口食品</option>
                            		<option value="10">米面粮油</option>
                            		<option value="11">厨房电器</option>
                            		<option value="12">礼盒</option>
                            		<option value="13">调味品</option>
                                </select>
                        </div>
                        </div>
                        <div class="layui-form-item">
                        <label for="username" class="layui-form-label">
                            <span class="x-red">*</span>商品种类</label>
                        <div class="layui-input-inline">
                            <input type="text" id="goods_type" name="goods_type" required="" lay-verify="required" autocomplete="off" class="layui-input" />
                            </div>
                    </div>
                    <div class="layui-form-item">
                        <label for="username" class="layui-form-label">
                            <span class="x-red">*</span>七天退换货</label>
                        <div class="layui-input-inline">
                            <select id="return_goods" name="return_goods" class="valid">  
                            		<option value="yes">是</option>
                            		<option value="no">不是</option>
                            		
                                </select>
                        </div>
                        </div>
                      <div class="layui-form-item">
                        <label for="username" class="layui-form-label">
                            <span class="x-red">*</span>是否包邮</label>
                        <div class="layui-input-inline">
                            <select id="if_freeshiiping" name="if_freeshiiping" class="valid">  
                            		<option value="yes">是</option>
                            		<option value="no">不是</option>
                            		
                                </select>
                        </div>
                        </div>
        <div class="layui-form-item">
            <label for="L_repass" class="layui-form-label"></label>
            <input type="submit" ></div>
            
        </form>
        </div>
        </div>
        <script>
        function btn() {
            // 获得frame索引
            console.log(4);
            Document.charset="utf-8";
            var index = parent.layer.getFrameIndex(window.name);
            //关闭当前frame
            // 获得frame索引

		window.location.reload();
		var index = parent.layer.getFrameIndex(window.name);
          //关闭当前frame
        parent.layer.close(index);
                       
           
            /*
            $.get("./admin/insert", {"content":$("#content").val(),"time":$("#time").val(),"user_id":$("#user_id option:selected")}, function(data){
     	       	/* var re = eval(data); 
     	       	console.log(data)
     			if(data=="faile"){
     	        	$("#info").text("账号密码错误！");
     	        }else{
     	        	$(obj).parents("tr").remove();
     	        	
     	        }
     	    });*/
        }
        
        
        layui.use(['form', 'layer'],
            function() {
                $ = layui.jquery;
                var form = layui.form,
                layer = layui.layer;

                //自定义验证规则
                form.verify({
                    nikename: function(value) {
                        if (value.length < 5) {
                            return '昵称至少得5个字符啊';
                        }
                    },
                    pass: [/(.+){6,12}$/, '密码必须6到12位'],
                    repass: function(value) {
                        if ($('#L_pass').val() != $('#L_repass').val()) {
                            return '两次密码不一致';
                        }
                    }
                });

                //监听提交
                form.on('submit(add)',
                function(data) {
                    console.log(data);
                    //发异步，把数据提交给php
                    layer.alert("增加成功", {
                        icon: 6
                    },
                    function() {
                        // 获得frame索引
                        var index = parent.layer.getFrameIndex(window.name);
                        //关闭当前frame
                        parent.layer.close(index);
                    });
                    return false;
                });

            });</script>
        <script>var _hmt = _hmt || []; (function() {
                var hm = document.createElement("script");
                hm.src = "https://hm.baidu.com/hm.js?b393d153aeb26b46e9431fabaf0f6190";
                var s = document.getElementsByTagName("script")[0];
                s.parentNode.insertBefore(hm, s);
            })();</script>
    </body>

</html>