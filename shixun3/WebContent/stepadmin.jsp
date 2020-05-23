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
        <script src="./lib/layui/layui.js" charset="utf-8"></script>
        <script type="text/javascript" src="./js/xadmin.js"></script>
        <!--[if lt IE 9]>
          <script src="https://cdn.staticfile.org/html5shiv/r29/html5.min.js"></script>
          <script src="https://cdn.staticfile.org/respond.js/1.4.2/respond.min.js"></script>
        <![endif]-->
    </head>
    
    <body>
        <div class="x-nav">
            
            <a class="layui-btn layui-btn-small" style="line-height:1.6em;margin-top:3px;float:right" onclick="location.reload()" title="刷新">
                <i class="layui-icon layui-icon-refresh" style="line-height:30px"></i>
            </a>
        </div>
        <div class="layui-fluid">
            <div class="layui-row layui-col-space15">

                <div class="layui-col-md12">

                    <div class="layui-card">
                        <div class="layui-card-body ">
                            
                        </div>
                        <div class="layui-card-header">

                            <button class="layui-btn layui-btn-danger" onclick="delcheck()">
                                <i class="layui-icon"></i>批量删除</button>
                            <button class="layui-btn" onclick="xadmin.open('添加菜单','./step_add.html',800,600)">
                                <i class="layui-icon"></i>添加</button></div>
                        <div class="layui-card-body ">
                            <table class="layui-table layui-form">
                                <thead>
                              	
                                    <tr>
                                        <th>批量删除</th>
                                        <th>步骤描述</th>
                                        <th>图片</th>
                                        <th>操作</th>
                                        </tr>
                                </thead>
                                <tbody>
                                <c:set var="count" value="0"></c:set>
	                                <c:forEach items="${steps}" var="step">
										<tr>
	                                        <td><input type="checkbox" name="deletecheck" lay-skin="primary"  value="${count}"></td>
	                                            <td>${step.content}</td>
	                                            <td style="align:center;text-align:center;"><img src="http://localhost:8080/shixun3/upload/${step.step_photo}" width="50" height="50" /></td>
	                                          	<td class="td-manage">
	                                             <a title="修改" onclick="xadmin.open('修改','admin/stepedit?count=${count}')">
	                                                <i class="layui-icon">&#xe60a;</i></a>
	                                            <a title="删除" onclick="member_del(this,'要删除的id',${count})"  href="javascript:;">
	                                                <i class="layui-icon" >&#xe640;</i></a>
	                                            <c:set var="count" value="${count+1}"></c:set>
	                                        </td>
	                                    </tr>
									</c:forEach>
                                </tbody>
                            </table>
                        </div>
                        <div class="layui-card-body ">
                            <div class="page">
                                <div>
                                    <a class="prev" href="">&lt;&lt;</a>
                                    <a class="num" href="">1</a>
                                    <span class="current">2</span>
                                    <a class="num" href="">3</a>
                                    <a class="num" href="">489</a>
                                    <a class="next" href="">&gt;&gt;</a></div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
    <script>layui.use(['laydate', 'form'],
        function() {
            var laydate = layui.laydate;

            //执行一个laydate实例
            laydate.render({
                elem: '#start' //指定元素
            });

            //执行一个laydate实例
            laydate.render({
                elem: '#end' //指定元素
            });
        });

    function loadXMLDoc(a)
    {
    	var xmlhttp;
    	if (window.XMLHttpRequest)
    	{
    		//  IE7+, Firefox, Chrome, Opera, Safari 浏览器执行代码
    		xmlhttp=new XMLHttpRequest();
    	}
    	else
    	{
    		// IE6, IE5 浏览器执行代码
    		xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
    	}
    	xmlhttp.open("GET","admin/menudelete?count="+a,true);
    	xmlhttp.send();
    }
    
    
        /*用户-停用*/
        function member_stop(obj, id) {
            layer.confirm('确认要停用吗？',
            function(index) {

                if ($(obj).attr('title') == '启用') {

                    //发异步把用户状态进行更改
                    $(obj).attr('title', '停用');
                    $(obj).find('i').html('&#xe62f;');

                    $(obj).parents("tr").find(".td-status").find('span').addClass('layui-btn-disabled').html('已停用');
                    layer.msg('已停用!', {
                        icon: 5,
                        time: 1000
                    });

                } else {
                    $(obj).attr('title', '启用');
                    $(obj).find('i').html('&#xe601;');

                    $(obj).parents("tr").find(".td-status").find('span').removeClass('layui-btn-disabled').html('已启用');
                    layer.msg('已启用!', {
                        icon: 5,
                        time: 1000
                    });
                }

            });
        }

        /*用户-删除*/
        function member_del(obj, id,a) {
            layer.confirm('确认要删除吗？',
            function(index) {
                //发异步删除数据
                $(obj).parents("tr").remove();
                layer.msg('已删除!', {
                    icon: 1,
                    time: 1000
                });
                var xmlhttp;
            	if (window.XMLHttpRequest)
            	{
            		//  IE7+, Firefox, Chrome, Opera, Safari 浏览器执行代码
            		xmlhttp=new XMLHttpRequest();
            	}
            	else
            	{
            		// IE6, IE5 浏览器执行代码
            		xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
            	}
            	xmlhttp.open("GET","admin/stepdelete?count="+a,true);
            	xmlhttp.send();
            });
        }

        function delcheck(argument) {

        	var id = document.getElementsByName('deletecheck');
            var sss = "";
            for(var i = 0; i < id.length; i++){
	             if(id[i].checked){
	            	 sss+=(id[i].value)+",";
	              	 console.log(i);
	              	 $(id[i]).parents("tr").remove(); 
	              	 i--;
	             }
            }
           
            var xmlhttp;
         	if (window.XMLHttpRequest)
         	{
         		//  IE7+, Firefox, Chrome, Opera, Safari 浏览器执行代码
         		xmlhttp=new XMLHttpRequest();
         	}
         	else
         	{
         		// IE6, IE5 浏览器执行代码
         		xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
         	}
         	xmlhttp.open("get","admin/stepdeletecheck?value="+sss,true);
         	xmlhttp.send();
        }</script>

</html>