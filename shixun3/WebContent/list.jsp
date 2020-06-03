<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<c:forEach items="${menus}" var="menus">
	${menus.menu_id},${menus.date},${menus.menu_photo},${menus.menu_name},${menus.text},
	<c:forEach items="${menus.labels }" var ="label">
		${label.label_id}-${label.label_name}'
	</c:forEach>
	''
</c:forEach>
</body>
</html>