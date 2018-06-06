<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>E+商城</title>
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
	<%-- <div align="right">
		<h2>E+商城热门首页</h2>
		Hello <b>${user.uname}</b>,
		<div align="right">
			<a href="${pageContext.request.contextPath }/user/logout.action">注销</a>
		</div>
	</div> --%>
	<div class="wrapper">
		<div class="header">
			<div class="header_container">

				<!--头部开
				始-->
				<div class="top_bar clear">
					<!-- logo -->
					<h1 class="logo clear fl">
						<a
							href="${pageContext.request.contextPath}/send/main/pages/user/index"><img
							src="${pageContext.request.contextPath }/images/logo.png" /></a>
					</h1>
					<!--头部小导航-->

					<ul class="top_links fr">
						<li class="highlight"><a
							href="${pageContext.request.contextPath}/send/main/pages/user/index">首页</a></li>
						<li><a href="#">我的账户</a></li>
						<li><a
							href="${pageContext.request.contextPath}/send/main/user/shoppingCart">购物车</a></li>
						<li><a href="#">商品详情</a></li>
						<li class="highlight"><c:if test="${empty user}">
								<a href="${pageContext.request.contextPath}/send/main/login">未登录!</a>
							</c:if> <c:if test="${!empty user}">
								<a href="${pageContext.request.contextPath}/user/logout.action">Welcome
									:${user.unick } </a>
							</c:if></li>
					</ul>

					<div class="welcom fl">欢迎光临E+线上商城!</div>
					<!-- 搜索框 -->
					<div class="header_search">
						<div class="form-search ">
							<input value="请输入商品名称" class="input-text" type="text" />
							<button type="submit" title="Search"></button>
						</div>
					</div>

				</div>

			</div>
		</div>
		<!-- 产品列表 -->
		<c:forEach items="${applicationScope.HotProductList}" var="list">
			<div class="products_list products_slider clear">
				<!-- 显示类别名称 -->
				<h2 class="sub_title">${list[0].category.type}</h2>
				<ul id="first-carousel"
					class="first-and-second-carousel jcarousel-skin-tango">

					<c:forEach items="${list }" var="product">
						<li><a
							href="${pageContext.request.contextPath}/product/get.action?id=${product.id}"
							class="product_image"> <%-- <img src="${product.pic}" /> --%>
								<img id="pic" src="data:image/jpeg;base64,${product.pic}"
								width="200px" height="240px" />
						</a>
							<div class="product_info" style="height: 120px">
								<h3>
									<a href="#">商品名称：${product.name }</a>
								</h3>
								<small>简单描述：${product.remark}</small>
							</div>
							<div class="price_info">
								<a
									href="${pageContext.request.contextPath}/user/check/sorder/addSorder.action?id=${product.id}"><button>
										<span class="pr_add">添加购物车</span>
									</button></a>
								<button class="price_add" title="" type="button">
									<span class="pr_price">￥${product.price}</span>
								</button>
							</div></li>
					</c:forEach>
				</ul>
			</div>
		</c:forEach>
		<!--产品列表结束  -->
		<!-- 导航栏结束 -->
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