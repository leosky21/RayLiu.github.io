<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>show result</title>
 </head>
  
  <body>
    <%
	  	int result=(int)request.getAttribute("result"); 
	  	
		out.println(result);
	%>
	<a href="nav.jsp">back</a>
  </body>
</html>
