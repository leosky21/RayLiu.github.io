<%@page import="cn.ray.listener.UserBeanSessionActivationListener"%>
<%@page import="cn.ray.listener.UserBeanHttpSessionBindingListener"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'attributeLis.jsp' starting page</title>
    
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
        // 往application域对象中添加属性
        application.setAttribute("name", "aaaa");
        // 替换application域对象中name属性的值
        application.setAttribute("name", "aaaaaaaaa");
        // 移除application域对象中name属性
        application.removeAttribute("name");
        
        // 往request域对象中添加属性
        request.setAttribute("request", "request1");
        // 替换request域对象中name属性的值
        request.setAttribute("request", "request2");
        // 移除request域对象中name属性
        request.removeAttribute("request");
        
        // 往request域对象中添加属性
        session.setAttribute("session", new UserBeanHttpSessionBindingListener());
        // 替换request域对象中name属性的值
        session.setAttribute("session", new UserBeanHttpSessionBindingListener());
        // 移除request域对象中name属性
        session.removeAttribute("session");
    %>
    
     一访问JSP页面，HttpSession就创建了，创建好的Session的Id是：${pageContext.session.id}
    <%  
        session.setAttribute("bean", new UserBeanSessionActivationListener()); // 当session一分钟没人用，session就和bean一起到硬盘中去了
    %>
  </body>
</html>
