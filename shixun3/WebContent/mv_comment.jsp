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
			html="<td><a href='delcomment?mv_id="+id+"'>删除</a></td>";
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