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
<c:forEach items="${users }" var="user">
  ${user.id }----${user.userName }------${user.password }
</c:forEach>

<form action="/shixun3/type/ceshi">
  <input type="submit" value="submit"/>
</form>
</body>
</html>