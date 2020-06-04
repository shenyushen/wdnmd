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
table.gridtable {
	font-family: verdana,arial,sans-serif;
	font-size:11px;
	color:#333333;
	border-width: 1px;
	border-color: #666666;
	border-collapse: collapse;
}
table.gridtable th {
	border-width: 1px;
	padding: 8px;
	border-style: solid;
	border-color: #666666;
	background-color: #dedede;
}
table.gridtable td {
	border-width: 1px;
	padding: 8px;
	border-style: solid;
	border-color: #666666;
	background-color: #ffffff;
}
</style>
</head>
<body>
	<table class="gridtable">
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
		 	html="<td><a href='delectMV?mv_name="+mv_name+"'><img src='upload/shan.jpg' width='50' height='50'/></a></td>";
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