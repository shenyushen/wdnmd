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
        <script type="text/javascript">
	        function add() {
	        	
	        	 $("#fileDiv").append($("<div>æä»¶:<input type='file' name='myfile'/><input type='button' value='å é¤' onclick='removeButton(this);'/></div>"));
	        	}
	        function removeButton(obj) {
	        	$(obj).parent("div").remove();
	        	}
	        
	        function aaa() {
	        	if (myform.theme.value=="")
	            {
	                myform.theme.focus();
	                return false;
	            }
	        	if (myform.riqi.value=="")
	            {
	                myform.riqi.focus();
	                return false;
	            }
	        	if (myform.myfile.value.length==0)
	            {
	        		document.getElementsByName('myfile').focus();
	        		return false;
	            }
	        	alert("å¢å æå");
	        	var index = parent.layer.getFrameIndex(window.name);
                //å³é­å½åframe
                parent.layer.close(index);
	        	}
	        
        </script>
        <!-- è®©IE8/9æ¯æåªä½æ¥è¯¢ï¼ä»èå¼å®¹æ æ ¼ -->
        <!--[if lt IE 9]>
            <script src="https://cdn.staticfile.org/html5shiv/r29/html5.min.js"></script>
            <script src="https://cdn.staticfile.org/respond.js/1.4.2/respond.min.js"></script>
        <![endif]--></head>
    
    <body>
        <div class="layui-fluid">
            <div class="layui-row">
                <form class="layui-form" name="myform" action="/shixun3/marketcomment/12345addorder" method="post" enctype="multipart/form-data" accept-charset="UTF-8" >
                    <div class="layui-form-item" >
                        <label for="menu_name" class="layui-form-label">
                            <span class="x-red">*</span>用户ID</label>
                        <div class="layui-input-inline">
                            <input type="text" id="user_id" name="user_id" required="" lay-verify="required" autocomplete="off" class="layui-input"></div>
                    </div>
                    
                    <div class="layui-form-item">
                        <label for="menu_name" class="layui-form-label">
                            <span class="x-red">*</span>总价格</label>
                        <div class="layui-input-inline">
                            <input type="text" id="price" name="price" required="" lay-verify="required" autocomplete="off" class="layui-input"></div>
                    </div>
                    
                    <div class="layui-form-item layui-form-text">
			            <label for="desc" class="layui-form-label">详细地址</label>
			            <div class="layui-input-block">
			                <textarea placeholder="请输入详细地址" id="address" name="address" class="layui-textarea"></textarea>
			            </div>
			        </div>
                    <div class="layui-form-item layui-form-text">
			            <label for="desc" class="layui-form-label">备注</label>
			            <div class="layui-input-block">
			                <textarea placeholder="备注信息" id="context" name="context" class="layui-textarea"></textarea>
			            </div>
			        </div>
						
                    
                   
                    
      		<div class="layui-form-item">
	            <label for="L_repass" class="layui-form-label"></label>
	            <input type="submit" class="layui-btn" onclick="aaa()" ></input>
            </div>
        </form>
        
        
        
        
      </div>
   </div>
 
        <script>layui.use(['form', 'layer'],
            function() {
                $ = layui.jquery;
                var form = layui.form,
                layer = layui.layer;

                //èªå®ä¹éªè¯è§å
                form.verify({
                    nikename: function(value) {
                        if (value.length < 5) {
                            return 'æµç§°è³å°å¾5ä¸ªå­ç¬¦å';
                        }
                    },
                    pass: [/(.+){6,12}$/, 'å¯ç å¿é¡»6å°12ä½'],
                    repass: function(value) {
                        if ($('#L_pass').val() != $('#L_repass').val()) {
                            return 'ä¸¤æ¬¡å¯ç ä¸ä¸è´';
                        }
                    }
                });

                //çå¬æäº¤
                form.on('submit(add)',
                function(data) {
                    console.log(data);
                    //åå¼æ­¥ï¼ææ°æ®æäº¤ç»php
                    layer.alert("å¢å æå", {
                        icon: 6
                    },
                    function() {
                        // è·å¾frameç´¢å¼
                        var index = parent.layer.getFrameIndex(window.name);
                        //å³é­å½åframe
                        parent.layer.close(index);
                    });
                    return false;
                });

            });
        
      
        
        
        
        
        </script>
        <script>var _hmt = _hmt || []; (function() {
                var hm = document.createElement("script");
                hm.src = "https://hm.baidu.com/hm.js?b393d153aeb26b46e9431fabaf0f6190";
                var s = document.getElementsByTagName("script")[0];
                s.parentNode.insertBefore(hm, s);
            })();</script>
            
        
    </body>
	
</html>