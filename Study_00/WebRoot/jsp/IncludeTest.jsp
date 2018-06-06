<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'IncludeTest.jsp' starting page</title>
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
   <%--使用include指令静态引入其它JSP页面--%>
 	   
 	 <%@include file="/1.html" %> <!--是在翻译阶段执行,当应用程序中所有的页面的某些部分（例如标题、页脚和导航栏）都相同的时候，我们就可以考虑用include。-->  
  
   
  
  <!-- 动态引入 -->
   <%-- <jsp:include page="/1.html"/><!-- 在请求处理阶段执行. --> --%> 
  	<%
   	request.getRequestDispatcher("/1.html").include(request, response);
   	 %> 

   
  </body>
</html>
