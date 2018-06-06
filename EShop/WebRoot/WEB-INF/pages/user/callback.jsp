<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<base href="<%=basePath%>">
	<title>SpringMVC Cookie Demo</title>
</head>
<body>
	<div align="center">
		<h2>SpringMVC Cookie Demo</h2>
		测试 callback 页面跳转
	</div>
</body>
</html>