<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>书籍列表页面</title>
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body style="text-align: center;">
   	<h1>书籍列表</h1>
   	<table width="70%" border="1" align="center">
   		<tr>
   			<td>书名</td>
   			<td>作者</td>
   			<td>售价</td>
   			<td>描述</td>
   			<td>操作</td>
   		</tr>
   		<c:forEach var="entry" items="${AllBook }">
   			<tr>
   				<td>${entry.value.name }</td>	
   				<td>${entry.value.author }</td>
   				<td>${entry.value.price }</td>
   				<td>${entry.value.description}</td>
   				<td>
   					<a href="${pageContext.request.contextPath }/BuyServlet?id=${entry.key}" target="_blank">购买</a>
   				</td>
   			</tr>
   		</c:forEach>
   	</table>
  </body>
</html>
