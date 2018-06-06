<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>首页</title>
</head>
<body style="text-align: center;">
    <h1>xxxx网站</h1>
    <br/>

    <div style="text-align: right;">
        <c:if test="${user!=null }">
            欢迎您：${user.nickname }  
            <a href="${pageContext.request.contextPath }/LogoutServlet">注销</a>
        </c:if>

        <c:if test="${user==null }">
            <a href="${pageContext.request.contextPath }/RegisterUIServlet">注册</a>
            <a href="${pageContext.request.contextPath }/LoginUIServlet">登录</a>
        </c:if>
    </div>
    <hr/>
</body>
</html>