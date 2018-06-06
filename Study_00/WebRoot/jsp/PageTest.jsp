<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>My JSP 'page.jsp' starting page</title>
  </head>
  <body>
 1. page 指令: <br/>
     [ language="java" ]  <br/>
     [ extends="package.class" ]  <br/>
     [ import="{package.class | package.*}, ..." ]  <br/>
     [ session="true | false" ]  <br/>
     [ buffer="none | 8kb | sizekb" ]  <br/>
     [ autoFlush="true | false" ]  <br/>
     [ isThreadSafe="true | false" ]  <br/>
     [ info="text" ]  <br/>
     [ errorPage="relative_url" ]  <br/>
     [ isErrorPage="true | false" ]  <br/>
     [ contentType="mimeType [ ;charset=characterSet ]" | "text/html ; charset=ISO-8859-1" ]  <br/>
     [ pageEncoding="characterSet | ISO-8859-1" ]  <br/>
     [ isELIgnored="true | false" ]  <br/>
2. page指令中的import属性 <br/>
	2.1 jsp引擎会自动导入如下的包 <br/>
	--	java.lang.*; <br/>
	-- 	javax.servlet.*; <br/>
	-- 	javax.servlet.jsp.*; <br/>
	--	javax.servlet.http.*; <br/>
		
3. page指令的errorPage属性
	:: errorPage的设置值必须使用相对路径,如果以"/"开头,表示相对于当前web应用程序的根目录(不是站点目录).否则表示当前页面
	:: 可以在web.xml使用  errorPage 元素为整个应用程序设置错误处理页面
	:: error-page> 元素有3个子元素，error-code>、exception-type>、location>。其中的error-code>子元素指定错误的状态码，例如：error-code>404/error-code>；exception-type>子元素指定异常类的完全限定名，例如：exception-type>java.lang.ArithmeticException /exception-type>；location>子元素指定以“/”开头的错误处理页面的路径，例如：location>/ErrorPage/404Error.jsp /location>。	
	
	:: 如果设置了某个JSP页面的errorPage属性，那么在web.xml文件中设置的错误处理将不对该页面起作用。	
  </body>
</html>

