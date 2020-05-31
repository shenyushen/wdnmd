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
                <form class="layui-form" action="/shixun3/admin/insert" method="post" enctype="multipart/form-data" accept-charset="UTF-8">
                	<div class="layui-form-item">
                        <label for="L_email" class="layui-form-label">
                            <span class="x-red">*</span>用户评价</label>
                        <div class="layui-input-inline">
                            <input type="text" id="content" name="content"  required=""  autocomplete="off" class="layui-input"></div>
                        
                    </div>
                    <div class="layui-form-item">
                        <label for="username" class="layui-form-label">
                            <span class="x-red">*</span>时间</label>
                        <div class="layui-input-inline layui-show-xs-block">
                                    <input type="date" class="layui-input" placeholder="日期" name="time" id="time"></div>
                    </div>
                    <div class="layui-form-item">
                        <label for="username" class="layui-form-label">
                            <span class="x-red">*</span>用户</label>
                        <div class="layui-input-inline">
                            <select id="user_id" name="user_id" class="valid">
                            	<c:forEach items="${yonghu}" var="step">
                            		<option value="${step.id }">${ step.username }</option>
                            	</c:forEach>
                                
                                
                                </select>
                        </div>
                        </div>
                    <div class="layui-form-item">
                        <label for="username" class="layui-form-label">
                            <span class="x-red">*</span>商品</label>
                        <div class="layui-input-inline">
                            <select id="goods_id" name="goods_id" class="valid">
                            	<c:forEach items="${goods}" var="step">
                            		<option value="${step.goodsId }">${ step.title }</option>
                            	</c:forEach>
                                
                                
                                </select>
                        </div>
                        </div>
                   <div class="layui-form-item">
                        <label for="username" class="layui-form-label">
                            <span class="x-red">*</span>物流服务</label>
                        <div class="layui-input-inline">
                            <select id="r1" name="r1" class="valid">
                                <option value="1">1分</option>
                                <option value="2">2分</option>
                                <option value="3">3分</option>
                                <option value="4">4分</option>
                                <option value="5">5分</option>
                                
                                </select>
                        </div>
                        </div>
                    <div class="layui-form-item">
                        <label for="username" class="layui-form-label">
                            <span class="x-red">*</span>商家服务</label>
                        <div class="layui-input-inline">
                            <select id="r2" name="r2" class="valid">
                                <option value="1">1分</option>
                                <option value="2">2分</option>
                                <option value="3">3分</option>
                                <option value="4">4分</option>
                                <option value="5">5分</option>
                                
                                </select>
                        </div>
                        </div>
                    <div class="layui-form-item">
                        <label for="username" class="layui-form-label">
                            <span class="x-red">*</span>描述相符</label>
                        <div class="layui-input-inline">
                            <select id="r3" name="r3" class="valid">
                                <option value="1">1分</option>
                                <option value="2">2分</option>
                                <option value="3">3分</option>
                                <option value="4">4分</option>
                                <option value="5">5分</option>
                                
                                </select>
                        </div>
                        </div>
                    <div class="layui-form-item">
                        <label for="username" class="layui-form-label">
                            <span class="x-red">*</span>上传图片</label>
                        <div class="layui-input-inline">
                            <input type="file" id="selectfile" name="selectfile" multiple="multiple" accept="image/*" required="" lay-verify="required" autocomplete="off" class="layui-input"></div>
                    </div>
                    
                    <!-- <div class="layui-form-item">
                        <label for="username" class="layui-form-label">
                            <span class="x-red">*</span>配送物流</label>
                        <div class="layui-input-inline">
                            <select id="shipping" name="shipping" class="valid">
                                <option value="shentong">申通物流</option>
                                <option value="shunfeng">顺丰物流</option></select>
                        </div>
                    </div> -->
                    <!-- <div class="layui-form-item">
                        <label for="username" class="layui-form-label">
                            <span class="x-red">*</span>支付方式</label>
                        <div class="layui-input-inline">
                            <select name="contrller">
                                <option>支付方式</option>
                                <option>支付宝</option>
                                <option>微信</option>
                                <option>货到付款</option></select>
                        </div>
                    </div> -->
                    
                   
        <div class="layui-form-item layui-form-text">
            <label for="desc" class="layui-form-label">商品评价</label>
            <div class="layui-input-block">
                <input placeholder="请输入内容" id="content" name="desc" class="layui-textarea" />
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