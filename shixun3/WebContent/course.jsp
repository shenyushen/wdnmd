<%@page import="com.test.delectMV"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>视频管理</title>
<style type="text/css">
        table
        {
            border-collapse: collapse;
            margin: 0 auto;
            text-align: center;
        }
        table td, table th
 
        {
            border: 1px solid #cad9ea;
            color: #666;
            height: 30px;
        }
        table thead th
        {
            background-color: #CCE8EB;
            width: 100px;
        }
        table tr:nth-child(odd)
        {
            background: #fff;
        }
        table tr:nth-child(even)
        {
            background: #F5FAFA;
        }
    </style>
</head>
<body>
	<table border="">
	<tr>
		<th>视频编号</th>
		<th>作者</th>
		<th>上传时间</th>
		<th>视频名称</th>
		<th>视频简述</th>
		<th>视频地址</th>
		<th>所属分类</th>
		<th>视频图片</th>
		<th>&nbsp;</th>
	</tr>
	<% ArrayList ss=(ArrayList)request.getAttribute("lis");
	String mv_name=null;
	String html=null;
	for(int i=0;i<ss.size();i++){
		if(i%9==0){
			out.println("<tr>");
		}if(i%9==3){
		 	mv_name=(String)ss.get(i);
		 	html="<td><a href='delectMV?mv_name="+mv_name+"'>删除</a></td>";
		 	System.out.println(html);
		}
		if(i%9==8){
			out.println(html);
			out.println("</tr>");
		}else{
			out.println("<td>"+ss.get(i)+"</td>");
		}
	}
	%>
</table>
</body>
</html>