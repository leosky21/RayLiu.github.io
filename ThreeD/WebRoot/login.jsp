<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8" />
<title>智能探识感知大数据平台管理系统登录</title>
<meta name="author" content="DeathGhost" />
<link rel="stylesheet" type="text/css" href="css/style.css" />
<style>
body {
	height: 100%;
	background: #16a085;
	overflow: hidden;
}

canvas {
	z-index: -1;
	position: absolute;
}
</style>
<script src="js/jquery.js"></script>
<script src="js/verificationNumbers.js"></script>
<script src="js/Particleground.js"></script>
<script>
	$(document).ready(function() {
		//粒子背景特效
		$('body').particleground({
			dotColor : '#ffffff',
			lineColor : '#ffcc00'
		});
		//验证码
		createCode();
		//测试提交，对接程序删除即可
		$(".submit_btn").click(function() {
			location.href = "${pageContext.request.contextPath }/LoginServlet";
			
		});
	});
</script>
<body>
<form action="${pageContext.request.contextPath }/LoginServlet" method="post">
	<dl class="admin_login">
		<dt>
			<strong>智能探识感知大数据平台管理系统管理系统</strong> <em>The 3D sensor data
				display platform</em>
		</dt>
		<dd class="user_icon">
			<input type="text" name="username" placeholder="账号" class="login_txtbx" />
		</dd>
		<dd class="pwd_icon">
			<input type="password" name="password" placeholder="密码" class="login_txtbx" />
		</dd>
		<dd class="val_icon">
			<div class="checkcode">
				<input type="text" id="J_codetext" placeholder="验证码" maxlength="4"
					class="login_txtbx">
				<canvas class="J_codeimg" id="myCanvas" onclick="createCode()">对不起，您的浏览器不支持canvas，请下载最新版浏览器!</canvas>
			</div>
			<input type="button" value="验证码核验" class="ver_btn"
				onClick="validate();">
		</dd>
		<dd>
			<input type="submit" value="立即登录" class="submit_btn" />
		</dd>
		<dd>
			<p>© 2018 淮海工学院计算机工程学院 版权所有</p>
			<p>201803</p>
		</dd>
	</dl>
	</form>
</body>
</html>