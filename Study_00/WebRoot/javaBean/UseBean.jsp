<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>JSP 'UseBean.jsp'</title>
  </head>
  
  <body>
  <ul>
  	<li>[jsp:useBean>标签：用于在JSP页面中查找或实例化一个JavaBean组件。</li>
	<li>[jsp:setProperty>标签：用于在JSP页面中设置一个JavaBean组件的属性。</li>
	<li>[jsp:getProperty>标签：用于在JSP页面中获取一个JavaBean组件的属性。</li>
  </ul>
  <br/>
  <!-- userbean标签的标签体只在userbean标签实例化bean时才执行  -->
    <jsp:useBean id="person" class="cn.ray.session.DemoLogin02.User" scope="page">
    userbean标签的标签体只在userbean标签实例化bean时才执行 <br>
    </jsp:useBean> <br>
  	<%=person.getUsername() %> 
  	<br/>
  	<br/>
  	<jsp:useBean id="perso2" class="cn.ray.session.DemoLogin02.User" scope="session">
    第一次运行时，JavaBean对象不存在则实例化一个新的JavaBean对象并将它以指定的名称存储到指定的域范围session中；第二次运行时，在指定的域范围session内就已经查找到指定名称的JavaBean对象，所以不会再创建一个新的JavaBean对象，即[]jsp:useBean>标签的标签体只在[]jsp:useBean>标签实例化bean时才执行。<br/>
    </jsp:useBean> <br>
  	<%=perso2.getUsername() %>
  	
  	  <%-- 使用jsp:setProperty标签设置person对象的属性值
         jsp:setProperty在设置对象的属性值时会自动把字符串转换成8种基本数据类型
          但是jsp:setProperty对于复合数据类型无法自动转换
    --%>
    <jsp:setProperty property="username" name="person" value="炑炑炑炑"/>
    <jsp:setProperty property="password" name="person" value="123"/>
  	<h2>姓名：<jsp:getProperty property="username" name="person"/></h2>
    <h2>密码：<jsp:getProperty property="password" name="person"/></h2>
  </body>
</html>
