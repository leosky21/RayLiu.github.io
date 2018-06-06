<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
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

	<div class="wrapper">
		<div class="header">
			<div class="header_container">
				<!--头部开始-->
				<div class="top_bar clear">
					<!--头部小导航-->
					<!--头部小导航-->
					<div class="welcom fl">欢迎光临E+线上商城!</div>
					<ul class="top_links fr">
						<li><a
							href="${pageContext.request.contextPath}/send/main/pages/user/index">首页</a></li>
						<li><a href="#">我的账户</a></li>
						<li class="highlight"><a
							href="${pageContext.request.contextPath}/send/main/user/shoppingCart">购物车</a></li>
						<li><a href="#">商品详情</a></li>
						<li class="highlight"><a href="#">Welcome : <c:if
									test="${empty user}">未登录!</c:if> <c:if test="${!empty user}">${user.unick }</c:if>
						</a></li>
					</ul>
					<!--头部小导航结束-->
					<!-- logo -->
					<h1 class="logo clear fl">
						<a
							href="${pageContext.request.contextPath}/send/main/pages/user/index">
							<img src="${pageContext.request.contextPath }/images/logo3.png" />
						</a>
					</h1>
					<div class="header_search">
						<div class="ss">
							<input value="请输入商品名称" class="ss" type="text" />
							<button type="submit" title="Search"></button>
						</div>
					</div>
				</div>

			</div>
		</div>
	</div>

	<!-- 头部结束 -->

	<!-- 导航栏 -->
	<div class="navigation_container">
		<!---->
		<div class="nav">
			<ul class="primary_nav">
				<li><a
					href="${pageContext.request.contextPath}/send/main/user/shoppingCart">购物车</a></li>
				<li><a href="#">确认订单信息</a></li>
				<li class="active"><a href="#">完成订单</a></li>
			</ul>
		</div>
		<!-- 确认订单信息 -->
		<div class="pay-step">
			<!-- 订购人确认 -->
			<div class="person-check check">
				<h1>您的订单已经生成</h1>
				<div class="person-checkinner">
					<div>
						<span>订单号</span>：<span>${sessionScope.oldForder.fid}</span>
					</div>
					<div>
						<span>收货人</span>：<span>${sessionScope.oldForder.fname}</span>
					</div>
					<div>
						<span>送货地址</span>：<span>${sessionScope.oldForder.faddress}</span>
					</div>
					<div>
						<span>邮政编码</span>：<span>${sessionScope.oldForder.fpost}</span>
					</div>
					<div>
						<span>支付金额</span>：<span>${sessionScope.oldForder.ftotal}</span>
					</div>
				</div>
			</div>
			<div class="pay">
				<div class="pay-inner">
					<div class="fl">支付方式:</div>
					<div class="fl yibao-logo">
						<a href=""><img
							src="${pageContext.request.contextPath}/files/yibao.jpg"
							width="110" height="35" alt="" /></a>
					</div>
					<div class="fr blue aa">
						世界一流的电子支付应用和服务提供商 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a class="red"
							href="">如何使用?</a>
					</div>

				</div>
			</div>
			<div class="select-bank">
				<form action="${pageContext.request.contextPath}/pay_goBank.action"
					method="post">
					<h1>请选择支付银行</h1>
					<!--  {fn:indexOf(name,'.'))}  {fn:substring(zip, 6, -1)} -->
					<div>
						<ul>
							<c:forEach items="${applicationScope.bankImageList}"
								var="bankImage">
								<li><input type="radio" name="pd_FrpId"
									value="${fn:substring(bankImage, 0, fn:indexOf(bankImage, '.'))}" />&nbsp;
									<img
									src="${pageContext.request.contextPath }/files/bankImages/${bankImage}" /></li>
							</c:forEach>
						</ul>
					</div>
					<div class="clear"></div>
					<div class="reminder">
						<span>请确保您的银行卡已开通网银支付功能，否则没有办法完成支付&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
						<a href="">如何开通?</a>
					</div>
					<div class="pay-submit">
						<div class="pay-inner">
							<input type="submit" style="width: 80px; height: 40px;"
								value="确认支付" />
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
	<div class="footer_container">
			<div class="footer">
				<ul class="footer_links">
					<li><span><a href="${pageContext.request.contextPath }/user/admin/login.action">后台管理</a></span>
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
</body>
<script src="${pageContext.request.contextPath }/js/jquery.js"></script>
<%-- <script src="${pageContext.request.contextPath }/js/verificationNumbers.js"></script> --%>
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
