<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
    欢迎您：${user.username }&nbsp;&nbsp;&nbsp;<a href="/Study_00/session/login.html">登录</a>&nbsp;&nbsp;&nbsp;<a href="/Study_00/LogoutServlet">退出登录</a>
    <br/><br/><br/>
    <a href="/Study_00/SessionDemo01">购买</a><br/>
    <a href="/Study_00/SessionDemo02">结账</a>
</body>
</html>
