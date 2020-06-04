<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>评论管理</title>
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
		<th>视频名称</th>
		<th>作者</th>
		<th>评论内容</th>
		<th>表情地址</th>
		<th>评论编号</th>
		<th>&nbsp;</th>
	</tr>
	<% ArrayList comm=(ArrayList)request.getAttribute("comment");
	String id=null;
	String html=null;
	for(int i=0;i<comm.size();i++){
		if(i%6==0){
			out.println("<tr>");
		}if(i%6==4){
			id=(String)comm.get(i);
			html="<td><a href='delcomment?mv_id="+id+"'><img src='upload/shan.jpg' width='50' height='50'/></a></td>";
		}if(i%6==5){
			out.println(html);
			out.println("</tr>");
		}else{
			out.println("<td>"+comm.get(i)+"</td>");
		}
	}
	%>
</table>
</body>
</html>