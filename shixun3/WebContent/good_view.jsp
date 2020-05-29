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

                        <div class="layui-card-body ">
                            <table class="layui-table layui-form">
                                
                                <tbody>
                                	<tr>
                                	<td>商品编号</td>
                                	<td>${good1.goods_id }</td>
                                	</tr>
                                	<tr>
                                	<td>商品标题</td>
                                	<td>${good1.title }</td>
                                	</tr>
                                	<tr>
            						<td>商品简介</td>
                                	<td>${good1.littile_content }</td>
                                	</tr>
                                   	
                                   	<tr>
            						<td>商品销量</td>
                                	<td>${good1.sale_volume}</td>
                                	</tr>
                                	<tr>
            							<td>商品图片</td>
                                		<td>
                                			<c:forEach items="${good1.img}" var="step">
                                				<img src ="http://localhost:8080/shixun3/upload/${step }" width="40" height="40" />
                                			</c:forEach>
                                		</td>
                                	</tr>
                                	<tr>
            							<td>商品种类</td>
                                		<td>
                                			<c:forEach items="${good1.goods_type}" var="step" >
                                				${step }; 
                                			</c:forEach>
                                		</td>
                                	</tr>
                                	<tr>
                                	<td>是否包邮</td>
                                	<td>${good1.if_freeshiiping }</td>
                                	</tr>
                                	<tr>
                                	<td>七天退换</td>
                                	<td>${good1.return_goods}</td>
                                	</tr>
                                	<tr>
                                	<td>商品评分</td>
                                	<td>${good1.goods_score}</td>
                                	</tr>
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
