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
  序号	隐式对象名称	描述
①	pageContext	对应于JSP页面中的pageContext对象（注意：取的是pageContext对象。）
②	pageScope	代表page域中用于保存属性的Map对象
③	requestScope	代表request域中用于保存属性的Map对象
④	sessionScope	代表session域中用于保存属性的Map对象
⑤	applicationScope	代表application域中用于保存属性的Map对象
⑥	param	表示一个保存了所有请求参数的Map对象
⑦	paramValues	表示一个保存了所有请求参数的Map对象，它对于某个请求参数，返回的是一个string[]
⑧	header	表示一个保存了所有http请求头字段的Map对象，注意：如果头里面有“-” ，例Accept-Encoding，则要header[“Accept-Encoding”]
⑨	headerValues	表示一个保存了所有http请求头字段的Map对象，它对于某个请求参数，返回的是一个string[]数组。注意：如果头里面有“-” ，例Accept-Encoding，则要headerValues[“Accept-Encoding”]
⑩	cookie	表示一个保存了所有cookie的Map对象
⑪	initParam	表示一个保存了所有web应用初始化参数的map对象
  
    applicationScope.count 当前在线用户人数：${applicationScope.count }人<br/>
    

  </body>
</html>
