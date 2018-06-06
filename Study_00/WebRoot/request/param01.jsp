<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'param01.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
  </head>
  
  <body>
     <!-- url后面如果跟了中文数据，要编码后再提交 -->
    <!--   <a href="day06/RequestDemo4?username=< %=URLEncoder.encode('中国', 'UTF-8')%>">点点</a>   -->
    <a href= "${pageContext.request.contextPath }/ParamGet01?username=中国">点点</a>

    <form action="/Study_00/ParamGet01" method="Get">
        用户名1：<input type="text" name="username1"><br/>
        用户名2：<input type="text" name="username2"><br/>
        密码：<input type="text" name="password"><br/>
        验证密码：<input type="text" name="password"><br/>
        <input type="submit" value="提交">
    </form>
  <br/>
 	使用普通方式取出存储在request对象中的数据：
    <% 
        String data = (String)request.getAttribute("data");
        out.write(data);
    %><br/>
    使用EL表达式取出存储在request对象中的数据：
    ${data }
  </body>
</html>
