<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head> 
    <title>测试登陆页面</title>
    <%String no=".";
		if(!session.isNew())
		{no=(String)session.getAttribute("userName");
		if (no==null)
			no="";}
	%>
  </head>
  <body>
  	${userName }	${userType } <br> 
  	Login success!! <%out.print(request.getAttribute("userName")+":"+no+":"+session.getAttribute("userType")); %><br>
  		<br/>
  </body>
</html>
