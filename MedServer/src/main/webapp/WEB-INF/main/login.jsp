<%@ page import="java.security.SecureRandom"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/public/head.jspf"%>
<%
	SecureRandom random = new SecureRandom();
	random.setSeed(8738);
	double _csrf = random.nextDouble();
	session.setAttribute("_csrf", _csrf);
%>
</head>
<body>
	
			<div class="section_container">
				<form action="${Med}/user/login.action" method="post">
					<div>
						<label for="login">登录账号:&nbsp;</label> <input type="text"
							name="uphone" />
					</div>
					<div>
						<label for="login">登录名(nick):&nbsp;</label> <input type="text"
							name="nick" />
					</div>
					<div>
						<label for="pass">密码:&nbsp;</label> <input type="password"
							name="upass" />
					</div>
					<div>
						<input name="remember-me" type="checkbox">30天内自动登录
					</div>
					<div>
						<input type="submit" value="登录" style="width:60px;height:30px" />
						<input type="button" value="注册"
							onclick="window.open('${shop}/index.jsp','_self')"
							style="width:60px;height:30px" />
					</div>
					<div>${Msg }</div>
					<input type="hidden" name="_csrf" value="<%=_csrf%>" />
				</form>
				<div style="clear:both"></div>
			</div>
	
</body>
</html>