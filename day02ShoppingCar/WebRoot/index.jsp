<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>


 <%@page isELIgnored="false" %> 
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>网站首页</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="Content-Type" content="text/html;charset=utf-8">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  </head>
  
  <body>
  EL表达式不起作用 : 加一句:[%@page isELIgnored="false" %] 
 	${pageContext.request.contextPath }
   <a href="${pageContext.request.contextPath }/ListBookServlet">浏览书籍</a>
  </body>
</html>
