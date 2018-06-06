<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"
	errorPage="./errorShow.jsp"
%>
<!-- 也可以在web.xml文件中使用 <error-page> 标签 -->
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>My JSP 'errorTest.jsp' starting page</title>
  </head>
  
  <body>
    <%
    	int x = 1/0;
     %>
  </body>
</html>
