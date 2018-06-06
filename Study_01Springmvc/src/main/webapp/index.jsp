<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
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
   <button onclick="sendJson()">json数据交互测试</button> 
  
   <script type="text/javascript">
    function sendJson() {
        $.ajax({
            type:"post",
            url:"${pageContext.request.contextPath }/json_test.action",
            data:'{"id":"1","name":"电冰箱","price":"1999"}',
            contentType:"application/json;charset=utf-8",
            success:function(data) {
                alert(data.id + ":" + data.name);
            }
        });
    }
</script>
  </body>
</html>
