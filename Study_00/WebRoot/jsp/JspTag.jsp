<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'JspTag.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta http-equiv="Content-Type" content="text/html;charset=utf-8">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
   <%--使用jsp:include标签引入其它JSP页面--%>
    <jsp:include page="/index.jsp"></jsp:include>
    <h1>使用jsp:include标签引入其它JSP页面</h1><br/>
    [jsp:include]标签是动态引入，[jsp:include]标签涉及到的2个JSP页面会被翻译成2个servlet，
    这2个servlet的内容在执行时进行合并。 <br/>
	 <br/>
	而include指令是静态引入，涉及到的2个JSP页面会被翻译成一个servlet，其内容是在源文件级别进行合并。<br/> 
		-- 所以 , 例如:  引入的文件和 被引入文件 的变量不能重复<br/>
		 <br/>
	注释:<br/>
		- 静态包含是将全部内容包含进来之后，再进行处理，属于先包含后处理<br/>
		- 动态包含就是指先将各个页面分别处理，处理完之后再将处理后的结果包含进来。<br/>
    <br/>
    JSP规范建议使用.jspf（JSP fragments）作为静态引入文件的扩展名。 <br/>
    只有用”@include”指令的时候，jspf文件的内容才会被解析并执行其中的jsp指令和tag， <br/>
    而使用”jsp:include”和JSTL的”c:import”都没有用，jspf文件被当作纯文本文件处理了。  <br/>
	 <br/>
	比如现在有一个head.jspf页面和foot.jspf页面。 <br/>
	 <br/>
	如何让tomcat服务器能够解析执行*.jspf文件中的java代码和标签呢，有如下的几种解决办法：
 <br/>
解决办法一：修改web.xml文件，添加对扩展名为*.jspf文件的映射。  <br/>
如下所示： <br/>

<!-- 让jspf扩展名同样成为JSP Servlet处理的文件。 -->
[code] 
[servlet-mapping]<br/>
  [servlet-name>jsp[/servlet-name]<br/>
  [url-pattern>*.jspf[/url-pattern]<br/>
[/servlet-mapping><br/>
[!-- 让jsp扩展名同样成为JSP Servlet处理的文件。 --]<br/>
[servlet-mapping><br/>
    [servlet-name>jsp[/servlet-name]<br/>
    [url-pattern>*.jsp[/url-pattern]<br/>
[/servlet-mapping><br/>
[/code><br/>
上面的配置方式也可以简写成这样：<br/>
[code><br/>
[servlet-mapping><br/>
[servlet-name>jsp[/servlet-name]<br/>
[url-pattern>*.jsp[/url-pattern]<br/>
[!-- 让jspf扩展名同样成为JSP Servlet处理的文件。 --]<br/>
[url-pattern>*.jspf[/url-pattern]<br/>
[/servlet-mapping]<br/>
[/code]<br/>
<br/>
两种写法的效果都是一样的。添加这样的配置信息后，此时tomcat服务器就可以正常解析执行*.jspf文件了<br/>
	<br/>
	<br/>
	解决办法二：修改Tomcat服务器的web.xml文件，添加对扩展名为*.jspf文件的映射。<br/> 
	然后根据Servlet名称找到对应的servlet-mapping配置，如下所示：<br/>
<br/>
	[servlet-mapping><br/>
    [servlet-name>jsp[/servlet-name><br/>
    [url-pattern>*.jsp[/url-pattern><br/>
    [url-pattern>*.jspx[/url-pattern><br/>
    [url-pattern>*.jspf[/url-pattern><br/>
	[/servlet-mapping><br/>
  </body>
</html>
