<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
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
  <% 
  		System.out.print(" to test filter!!!");
   %>
    This is my JSP page. <br>
    压缩before:${before }
    压缩after:${after }
    
    messgae: ${messgae }
    <a href="/Study_00/RefererTest">防盗链测试</a>
    
    <br/>
     <h1>username：<%=request.getParameter("username") %></h1>
    <h1>password：<%=request.getParameter("password") %></h1>
    
    <form action="/${pageContext.request.contextPath }/ServletDemo1" method="get">
        <input type="text" name="username"><br/>
        <input type="submit" value="提交">
    </form>
  </body>
</html>
