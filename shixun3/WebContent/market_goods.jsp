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
                            <form 	 class="layui-form layui-col-space5" action="./admin/search_goods">
                               商品标题： <div class="layui-input-inline layui-show-xs-block">
                                	    <select name="title">
                                	    <option value="未填写">不选</option>
                                	    <c:forEach items="${goods_all}" var="step">
                            				<option value="${step.goodsId }">${ step.title }</option>
                            			</c:forEach>
                                        </select>
                                </div>
                                价格<div class="layui-input-inline layui-show-xs-block"style="width:80px;">

                                    <select name="price1">
                                        <c:forEach var="s"  begin="0" end="100">
                                        	<option value="${s }">￥${s }</option>
                                        </c:forEach>
                                        </select>
                                </div>
                   	—— <div class="layui-input-inline layui-show-xs-block"style="width:80px;">
                                    <select name="price2">
                                    <c:forEach items="${num}" var="step">
                            				<option value="${step}">￥${step }</option>
                            			</c:forEach>
                                        
                                        </select>
                                </div>
                         	包邮 <div class="layui-input-inline layui-show-xs-block" style="width:80px;">
                                    <select name="if_freeshiiping">
                                   <option value="未填写">不选</option>
                            			<option value="yes">yes</option>
                                        <option value="no">no</option>
                                        </select>
                                </div>
                                退换货 <div class="layui-input-inline layui-show-xs-block"style="width:80px;">
                                    <select name="return_goods">
                                   <option value="未填写">不选</option>
                            			<option value="yes">yes</option>
                                        <option value="no">no</option>
                                        </select>
                                </div>
                                <input type="submit" />
                            </form>
                        </div>
                        
                        
                        
                        <div class="layui-card-header">
                            <button class="layui-btn" onclick="xadmin.open('添加商品','./goods_add1.jsp',800,600)">
                                <i class="layui-icon"></i>添加</button></div>
                        <div class="layui-card-body ">
                            <table class="layui-table layui-form">
                                <thead>
                                    <tr>
                                        <th>
                                            <input type="checkbox" name="" lay-skin="primary"></th>
                                        <th>商品编号</th>
                                        <th>商品标题</th>
                                        <th>商品销量</th>
                                        <th>商品价格</th>
                                        <th>是否包邮</th>
                                        <th>七天退换</th>
                                        <th>商品评分</th>
                                        <th>商品图片</th>
                                        
                         				<!--  <th>配送方式</th>
                                        <th>下单时间</th> -->
                                        
                                        <th>操作</th>
                                        
                                        </tr>
                                </thead>
                                <tbody>
                                	<c:forEach items="${goods}" var="step">
                                		<tr>
                                        <td>
                                            <input type="checkbox" name="" lay-skin="primary"></td>
                                        <td>${step.goodsId }</td>
                                        <td>${step.title }</td>
                                        <td>${step.saleVolume } </td>
                                        <td>${step.price }</td>
                                        <td>${step.good.if_freeshiiping }</td>
                                        <td>${step.good.return_goods }</td>
                                        <td>${step.good.goods_score }</td>
                                        <td><img src ="http://localhost:8080/shixun3/upload/${step.img }" width="60" height="60"  / ></td>
                                        <!-- <td>其他方式</td>
                                        <td>申通物流</td>
                                        <td>2017-08-17 18:22</td> -->
                                        <td class="td-manage">
                                            <a title="查看" onclick="xadmin.open('查看','./admin/xiangqing?id=${step.goodsId}')" href="javascript:;">
                                                <i class="layui-icon">&#xe63c;</i></a>
                                            <a title="删除" onclick="member_del(this,'${step.goodsId}')" href="javascript:;">
                                                <i class="layui-icon">&#xe640;</i></a>
                                        </td>
                                    </tr>
                                	
                                	</c:forEach>
                                
            
                                   
                                  <!--   <tr>
                                        <td>
                                            <input type="checkbox" name="" lay-skin="primary"></td>
                                        <td>2017009171822298053</td>
                                        <td>老王:18925139194</td>
                                        <td>7829.10</td>
                                        <td>7854.10</td>
                                        <td>待确认</td>
                                        <td>未支付</td>
                                        <td>未发货</td>
                                        <td>其他方式</td>
                                        <td>申通物流</td>
                                        <td>2017-08-17 18:22</td>
                                        <td class="td-manage">
                                            <a title="查看" onclick="xadmin.open('编辑','order-view.html')" href="javascript:;">
                                                <i class="layui-icon">&#xe63c;</i></a>
                                            <a title="删除" onclick="member_del(this,'要删除的id')" href="javascript:;">
                                                <i class="layui-icon">&#xe640;</i></a>
                                        </td>
                                    </tr> -->
                                    
                                </tbody>
                            </table>
                        </div>
                        <div class="layui-card-body ">
                            <div class="page">
                                <div>
                                    <a class="prev"  href="./admin/page1?page=${(goods_page-1)<0?0:goods_page-1 }">&lt;&lt;</a>

                                    <a class="next" href="./admin/page1?page=${goods_page+1 }">&gt;&gt;</a></div>
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
        	 $.get("./admin/delete1", {"id":id}, function(data){
      	       	/* var re = eval(data); */
      	       	console.log(data)
      			if(data=="faile"){
      	        	$("#info").text("账号密码错误！");
      	        }else{
      	        	$(obj).parents("tr").remove();
      	        	
      	        }
      	    });
        }

        function delAll(argument) {

            var data = tableCheck.getData();

            layer.confirm('确认要删除吗？' + data,
            function(index) {
                //捉到所有被选中的，发异步进行删除
                layer.msg('删除成功', {
                    icon: 1
                });
                $(".layui-form-checked").not('.header').parents('tr').remove();
            });
        }</script>

</html>