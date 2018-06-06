<%@ page import="java.security.SecureRandom"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>E+跑线上商城登录界面</title>
<%
	SecureRandom random = new SecureRandom();
	random.setSeed(8738);
	double _csrf = random.nextDouble();
	session.setAttribute("_csrf", _csrf);
%>

<link rel="stylesheet"
	href="${pageContext.request.contextPath }/css/public.css"
	type="text/css"></link>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/style.css" type="text/css"></link>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/detail.css"
	type="text/css"></link>
<style>
body {
	height: 100%;
	/* background: #16a085; */
	background: #ffffef;
	/* overflow: hidden; */
}

canvas {
	z-index: -1;
	position: fixed;
}
</style>
</head>
<body>
	<div align="center">
		<div class="wrapper">
			<div class="header">
				<div class="header_container">
					<!--头部开始-->
					<div class="top_bar clear">
						<!-- logo -->
						<h1 class="logo clear fl">
							<a href="#"> <img
								src="${pageContext.request.contextPath }/images/logo.png" />
							</a>
						</h1>
						<!--头部小导航-->
						<div class="welcom fl">欢迎光临E+线上商城!</div>
						<ul class="top_links fr">
							<li><a
								href="${pageContext.request.contextPath}/send/main/pages/user/index">首页</a></li>
							<li><a href="#">我的账户</a></li>
							<li><a
								href="${pageContext.request.contextPath}/send/main/user/shoppingCart">购物车</a></li>
							<li><a href="#">商品详情</a></li>
							<li class="highlight"><a href="#">Welcome : <c:if
										test="${empty user}">未登录!</c:if> <c:if test="${!empty user}">${user.unick }</c:if>
							</a></li>
						</ul>
					</div>
				</div>
			</div>
			<!-- 头部结束 -->
			<div class="section_container">
				<!-- 购物车 -->
				<form action="${pageContext.request.contextPath }/user/login.action"
					method="post" onsubmit="return  fnLogin()">

					<div>
						<label for="login">账号:&nbsp;</label> <input type="text"
							id="uphone" name="uphone" />
					</div>
					<div>
						<label for="pass">密码:&nbsp;</label> <input type="password"
							id="upass" name="upass" />
					</div>
					<div>
						<input name="remember-me" type="checkbox">30天内自动登录
					</div>
					<div>
						<input type="submit" value="登录" style="width:60px;height:30px" />
						<input type="button" value="注册"
							onclick="window.open('${pageContext.request.contextPath}/send/main/user/uRegister','_self')"
							style="width:60px;height:30px" />
					</div>
					<div id="error_box"></div>
					<input type="hidden" name="_csrf" value="<%=_csrf%>" />
				</form>
				<div style="clear:both"></div>
			</div>
		</div>
		<!-- 导航栏结束 -->
		<div class="footer_container">
			<div class="footer">
				<ul class="footer_links">
					<li><span><a
							href="${pageContext.request.contextPath }/user/admin/login.action">后台管理</a></span>
						<ul>
							<li><a href="#">商品管理</a></li>
							<li><a href="#">类别管理</a></li>
							<li><a href="#">服务桩信息管理</a></li>
							<li><a href="#">商品销售分析</a></li>
						</ul></li>
					<li class="seperator"><span>出售的类别</span>
						<ul>
							<li><a href="#">运动饮料</a></li>
							<li><a href="#">运动服饰</a></li>
							<li><a href="#">运动配件</a></li>
							<li><a href="#">运动食品安全</a></li>
							<li><a href="#">运动补给品</a></li>
						</ul></li>
					<li><span>客户服务</span>
						<ul>
							<li><a href="#">帮助</a></li>
							<li><a href="#">速递</a></li>
							<li><a href="#">退换货</a></li>
							<li><a href="#">付款方式</a></li>
							<li><a href="#">订单跟踪</a></li>
							<li><a href="#">礼物包选项</a></li>
							<li><a href="#">国际服务</a></li>
							<li><a href="#">退运险</a></li>
						</ul></li>
					<li><span>个人账户</span>
						<ul>
							<li><a href="#">个人账户信息</a></li>
							<li><a href="#">用户密码</a></li>
							<li><a href="#">订单历史</a></li>
							<li><a href="#">付款方式</a></li>
							<li><a href="#">我的收货地址</a></li>
							<li><a href="#">我的通知</a></li>
						</ul></li>
				</ul>
			</div>
		</div>
	</div>
</body>

<script>
	//长度必须在6~20位之间
	//开头不能为数字
	//只能包含小写字母和数字
	//数字：48~57
	//小写字母：97~122
	//innerHTML
	function fnLogin() {
		var oUname = document.getElementById("uphone");
		var oUpass = document.getElementById("upass");
		var oError = document.getElementById("error_box");
		var isNotError = true;
	/* 	if (oUname.value.length > 20 || oUname.value.length < 6) {
			oError.innerHTML = "用户名长度必须在6~20位之间";
			isNotError = false;
			return;

		} else */ 
		if (oUname.value.length != 11) {
			oError.innerHTML = "登录账号是11位手机号";
			isNotError = false;
			
		} else {
			for (var i = 0; i < oUname.value.length; i++) {
				if( (oUname.value.charCodeAt(i) > 57 || oUname.value.charCodeAt(i) < 48) ) {
					oError.innerHTML = "账号只能包含数字";
					isNotError = false;
					/* return ; */
				}
			}
		}
		for (var i = 0; i < oUname.value.length; i++) {
			if ((oUpass.value.charCodeAt(i) > 122 || oUpass.value.charCodeAt(i) < 97) && (oUpass.value.length > 20 || oUpass.value.length < 6)) {
				oError.innerHTML = "密码只能包含小写字母和数字";
				isNotError = false;
				/* return ; */
			}
		}
		/* oError.innerHTML = ""; */
		return isNotError;
	}
	
</script>

<script src="${pageContext.request.contextPath }/js/jquery.js"></script>
<script src="${pageContext.request.contextPath }/js/Particleground.js"></script>
<script>
	$(document).ready(function() {
		//粒子背景特效
		$('body').particleground({
			dotColor : '#ffffff',
			lineColor : '#ffcc00'
		});
	});
</script>
</html>