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
        <script type="text/javascript" >
	        function dianji(obj) {
	        	alert(obj.id);
	        	obj.style.backgroundColor = "blue";
	       	 }
	        
        </script>
        <!--[if lt IE 9]>
          <script src="https://cdn.staticfile.org/html5shiv/r29/html5.min.js"></script>
          <script src="https://cdn.staticfile.org/respond.js/1.4.2/respond.min.js"></script>
        <![endif]-->
    </head>
    
    <body>
        <div class="x-nav">
            <span class="layui-breadcrumb">
                <a href="">首页</a>
                <a href="">演示</a>
                <a>
                    <cite>导航元素</cite></a>
            </span>
            <a class="layui-btn layui-btn-small" style="line-height:1.6em;margin-top:3px;float:right" onclick="location.reload()" title="刷新">
                <i class="layui-icon layui-icon-refresh" style="line-height:30px"></i>
            </a>
        </div>
        <div class="layui-fluid">
            <div class="layui-row layui-col-space15">

                <div class="layui-col-md12">

                    <div class="layui-card">
                        <div class="layui-card-body ">
                            <div class="layui-collapse" lay-filter="test">
                                <div class="layui-colla-item">
                                <h2 class="layui-colla-title">条件筛选<i class="layui-icon layui-colla-icon"></i></h2>
                                <div class="layui-colla-content">
                                  <form class="layui-form" action="findweb/selectbyitem">
                                      <div class="layui-form-item">
                                        
                                        <div class="layui-inline">
                                          <label class="layui-form-label">作者:</label>
                                          <div class="layui-input-inline">
                                            <select name="modules" lay-verify="required" lay-search="">
                                            	<option value="">直接选择或搜索选择</option>
                                             	<c:forEach items="${user}" var="item" varStatus="status">
													<option value="${item.id}">${item.username}</option>
												</c:forEach>
                                            </select>
                                          </div>
                                        </div>
                                      </div>
                                      
                                      <div class="layui-form-item">
                                        <label class="layui-form-label">相关主题:</label>
                                        <div class="layui-input-inline">
                                          <input type="text" name="theme" class="layui-input">
                                          
                                        </div>
                                      </div>
                                      
                                      <div class="layui-form-item">
                                        <label class="layui-form-label">发布日期:</label>
                                        <div class="layui-input-inline">
                                          <input type="date" name="date" class="layui-input">
                                          
                                        </div>
                                      </div>
                                      
                                      <div class="layui-form-item">
                                        <label class="layui-form-label">类型筛选:</label>
                                        <div class="layui-input-block">
                                          <input type="checkbox" name="choosebox" value="1" title="早餐" checked="">
                                          <input type="checkbox" name="choosebox" value="2" title="晚餐" checked="">
                                          <input type="checkbox" name="choosebox" value="3" title="中餐" checked="">
                                          <input type="checkbox" name="choosebox" value="4" title="甜点">
                                          <input type="checkbox" name="choosebox" value="5" title="饮料" >
                                          <input type="checkbox" name="choosebox" value="6" title="火锅">
                                          <input type="checkbox" name="choosebox" value="7" title="泡面">
                                        </div>
                                      </div>
                                      
                                      <div class="layui-form-item">
                                        <label class="layui-form-label" >按人气排序:</label>
                                        <div class="layui-input-block" style="padding-top:8px;">
                                          <input type="checkbox" checked="" value="1" name="open" lay-skin="switch" lay-filter="switchTest" lay-text="ON|OFF">
                                        </div>
                                      </div>
                                      
                                      <div class="layui-form-item">
                                        <div class="layui-input-block">
                                          <input type="submit" class="layui-btn" "></input>
                                          <button type="reset" class="layui-btn layui-btn-primary">重置</button>
                                        </div>
                                      </div>
                                    </form>
                                </div>
                              </div>
                            </div>
                        </div>
                        <div class="layui-card-header">

                            <button class="layui-btn layui-btn-danger" onclick="delAll()">
                                <i class="layui-icon"></i>批量删除</button>
                                
                                
                            <button class="layui-btn" onclick="xadmin.open('添加用户','./order-add.jsp',800,600)">
                                <i class="layui-icon"></i>添加</button></div>
                        <div class="layui-card-body ">
                            <table class="layui-table layui-form">
                                <thead>
                                    <tr>
                                        <th><input type="checkbox" name="" lay-skin="primary"></th>
                                        <th>id</th>
                                        <th>作者</th>
                                        <th>主题</th>
                                        <th>内容</th>
                                        <th>时间</th>
                                        <th>点赞人数</th>
                                        <th>图片</th>
                                        <th>标签类型</th> 
                                        <th>操作</th></tr>
                                </thead>
                                
                              
                                <tbody>
	                                <c:forEach items="${find}" var="item" varStatus="status">
	                                    <tr>
	                                        <td><input type="checkbox" name="deletecheck" lay-skin="primary"></td>
	                                        <td>${item.id}</td>
	                                        <td>${item.author}</td>
	                                       	<td>${item.theme}</td>
	                                        <td>${item.data}</td>
	                                        <td>${item.date}</td>
	                                        <td>${item.likenum}</td>
	                                        <td style="align:center;text-align:center;">
	                                        	<c:forEach items="${item.find_Photos}" var="photos" varStatus="status">
	                                        		<img src="http://localhost:8080/shixun3/pic/${photos.photo}" width="50" height="50" />
	                                        	</c:forEach>
	                                        </td>
	                                        
	                                        <td >${item.findLable.lable}</td>
	                                        <td class="td-manage">
	                                        
	                                            <a title="修改" onclick="member_update(this,'id')" href="javascript:;">
	                                                <i class="layui-icon">&#xe63c;</i></a>
	                                            <a title="删除" onclick="member_del(this,'要删除的id')" href="javascript:;">
	                                                <i class="layui-icon">&#xe640;</i></a>
	                                        </td>
	                                    </tr>
	                                  </c:forEach>
                                </tbody>
                            </table>
                        </div>
                        <div class="layui-card-body ">
                            <div class="page">
                                <div name="mydiv" >
                                
	                               	
	                                <c:forEach var="i" begin="1"  end="${p}" step="1">
	                                
										<a href="findweb/findbypage?page=${i}" name="ahref"  id="${i}">${i}</a>
									</c:forEach>
									
                                    <a class="next" href="">&gt;&gt;</a>
                                </div>
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
        function member_del(obj, id) {
        	
        	var a = new Array();
        	a[0] = obj.parentNode.parentNode.children[1].innerText;
            layer.confirm('确认要删除吗？',
            function(index) {
                //发异步删除数据
                $(obj).parents("tr").remove();
                layer.msg('已删除!', {
                    icon: 1,
                    time: 1000
                });
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
         	
         	xmlhttp.open("get","findweb/delect?value="+a,true);
         	xmlhttp.send();
            
        }
        //修改函数
		function member_update(obj, id) {
			
        	var a = new Array();
        	a[0] = obj.parentNode.parentNode.children[1].innerText;
        	a[1] = obj.parentNode.parentNode.children[2].innerText;
        	a[2] = obj.parentNode.parentNode.children[3].innerText;
        	a[3] = obj.parentNode.parentNode.children[4].innerText;
        	a[4] = obj.parentNode.parentNode.children[5].innerText;
        	a[5] = obj.parentNode.parentNode.children[6].innerText;
        	a[6] = obj.parentNode.parentNode.children[8].innerText;
        	
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
         	
         	xmlhttp.open("get","findweb/update?value="+a,false);
         	xmlhttp.send(); 
         	

         	xadmin.open('编辑','./order-add2.jsp');
        }

        function delAll(argument) {

            //var data = tableCheck.getData();
            
        	var id = document.getElementsByName('deletecheck');
        	var a = new Array();
            for(var i = 0; i < id.length; i++){
	             if(id[i].checked){
	            	  a[i] = id[i].parentNode.parentNode.children[1].innerText;
	             }
            }

            layer.confirm('确认要删除吗？',
            function(index) {
                //捉到所有被选中的，发异步进行删除
                layer.msg('删除成功', {
                    icon: 1
                });
                $(".layui-form-checked").not('.header').parents('tr').remove();
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
         	
         	xmlhttp.open("get","findweb/delect?value="+a,true);
         	xmlhttp.send(); 
        }
        
        /* function choose() {
        	var id = document.getElementsByName('choosebox');
        	var a = new Array();
            for(var i = 0; i < id.length; i++){
	             if(id[i].checked){
	            	  a[i] = id[i].value;
	            	  alert(a[i]);
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
         	
         	xmlhttp.open("POST","findweb/selectbyitem",true);
         	xmlhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
         	xmlhttp.send("value="+a);
        }
         */
        
        
        </script>

</html>