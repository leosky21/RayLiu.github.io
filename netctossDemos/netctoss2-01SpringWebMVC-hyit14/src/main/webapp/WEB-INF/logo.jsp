<%@ page contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<img src="images/logo.png" alt="logo" class="left"/>
<!-- EL表达式默认从这4个对象中取值：
		pageContext, request, session, application
		也可以从cookie中取值，格式如下：
			{cookie.name,value}			 -->
<!-- <span>${cookie.adminCode.value}</span> -->
<span>${admin.adminCode}</span>
<a href="${pageContext.request.contextPath}/logout.do">[退出]</a>