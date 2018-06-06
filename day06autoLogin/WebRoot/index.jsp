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
    <form  action="${pageContext.request.contextPath }/LoginServlet" method="post">
        用户名：<input type="text" name="username" value="${sessionScope.user.username }"><br/>
        密码：<input type="password" name="password" value="${sessionScope.user.password }"><br/>
        有效期：
        1分钟<input type="radio" name="time" value="${1*60 }"><br/>
        
        5分钟<input type="radio" name="time" value="${5*60 }"><br/>
        
        10分钟<input type="radio" name="time" value="${10*60 }"><br/>
        <input type="submit">
    </form>
     ${message}
  </body>
</html>
