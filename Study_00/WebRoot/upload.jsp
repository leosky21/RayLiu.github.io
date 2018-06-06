
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'upload.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  <!-- 当action为post时候，浏览器把form数据封装到http body中，然后发送到server。 
  		如果没有type=file的控件，用默认的application/x-www-form-urlencoded就可以了。 
  		但是如果有type=file的话，就要用到multipart/form-data了。
  		浏览器会把整个表单以控件为单位分割，并为每个部分加上Content-Disposition(form-data或者file),
  				Content-Type(默认为text/plain),name(控件name)等信息，并加上分割符(boundary)。 -->
  <body>
  aaa
	    <form action="${pageContext.request.contextPath }/UploadServlet" enctype="multipart/form-data" method="post">
	    	用户:<input type="text" name="username"><br/>
	    	文件1:<input type="file" name="file1" ><br/>
	    	文件2:<input type="file" name="file2" ><br/>
	    	<input type="submit">
	    </form>
  </body>
</html>
