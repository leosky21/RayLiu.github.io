<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"
	isErrorPage="true"
%>
<!-- 显式 声明isErrorPage 
将error.jsp页面显式声明为错误处理页面后，有什么好处呢，
	好处就是Jsp引擎在将jsp页面翻译成Servlet的时候，
	在Servlet的 _jspService方法中会声明一个exception对象，
	然后将运行jsp出错的异常信息存储到exception对象中，
	然后可以在 error页面获取异常信息
-->
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'errorShow.jsp' starting page</title>
    
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
    <%-- 注意：error.jsp的页面大小要超过1k，否则不会出现试验效果 --%>
    对不起，出错了
    对不起，出错了
    对不起，出错了
    对不起，出错了
    对不起，出错了
    对不起，出错了
    对不起，出错了
    对不起，出错了
    对不起，出错了
    对不起，出错了
    对不起，出错了
    对不起，出错了
    对不起，出错了
    对不起，出错了
    对不起，出错了
    对不起，出错了<br/>
    <%=exception.getMessage() %>
  </body>
</html>
