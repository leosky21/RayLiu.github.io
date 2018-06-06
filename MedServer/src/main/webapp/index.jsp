<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <%@ include file="/public/head.jspf"%>
  </head>
  
  <body>
  	<a href="${Med}/user/test.action">测试连通性</a>
   	<a href="${Med}/send/main/login">去登录</a>
    <form action="${Med}/user/register.action"  method="post">
    	phone <input type="text" name="phone" /><br/>
    	nick :<input type="text" name="nick" /><br/>
    	name :<input type="text" name="username" /><br/>
    	pass :<input type="password" name="password"/><br/>
    	drugallergyhistory <input type="text" name="drugallergyhistory" /><br/>
    	<!-- img :<input type="text" name="nick" /><br/> -->
    	medicalhistory :<input type="text" name="medicalhistory" /><br/>
    	sex :<input type="text" name="sex"/><br/>
    	weight :<input type="text" name="weight" /><br/>
    	height :<input type="text" name="height" /><br/>
    	<input type="submit">
    </form>
  </body>
</html>
