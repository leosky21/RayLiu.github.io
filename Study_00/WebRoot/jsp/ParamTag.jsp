<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'ParamTag.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
  
  [jsp:param]标签的name属性用于指定参数名，value属性用于指定参数值。
  在[jsp:include>和[jsp:forward>标签中可以使用多个[jsp:param>标签来传递多个参数。 
   <%--  <jsp:include page="/session/login.html">
        <jsp:param value="leo" name="username"/>
        <jsp:param value="123" name="password"/>
    </jsp:include>
     --%>
     <jsp:forward page="/index.jsp"><%-- <jsp:forward page="/LoginServlet"> --%>
        <jsp:param value="leo" name="username"/>
        <jsp:param value="123" name="password"/>
    </jsp:forward>
  </body>
</html>
