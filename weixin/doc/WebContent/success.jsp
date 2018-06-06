<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>操作成功</title>
</head>
<body>
<div>

 ${result}
 
 
</div>
	<div>
		<c:forEach items="${list}" var="m" varStatus="l">
		  <br>微博=${m.wid}<br>
		</c:forEach>
	</div>
</body>
</html>