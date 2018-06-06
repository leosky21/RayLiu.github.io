<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<%
	response.setDateHeader("expires", -1);
	response.setHeader("Cache-Control", "no-cache");
	response.setHeader("Pragma", "no-cache");
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
	<c:if test="${empty sessionScope.forder.sorders }">
		<!-- 如果购物车中的购物项为空，则跳转到首页 -->
		<c:redirect url="/index.jsp" />
	</c:if>
	<div class="wrapper">
		<div class="header">
			<div class="header_container">
				<!--头部开始-->
				<div class="top_bar clear">
					<h1 class="logo clear fl">
						<a href="index.html"> <img
							src="${pageContext.request.contextPath }/images/logo3.png" />
						</a>
					</h1>
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
		<!-- 头部结束 -->

		<!-- 导航栏 -->
		<div class="navigation_container">
			<!---->
			<div class="nav">
				<ul class="primary_nav">
					<li><a
						href="${pageContext.request.contextPath}/send/main/user/shoppingCart">购物车</a></li>
					<li class="active"><a href="#">确认订单信息</a></li>
					<li><a href="#">完成订单</a></li>
				</ul>
			</div>
		</div>
		<!--导航栏结束-->

		<div class="section_container">
			<!-- 购物车 -->
			<!--  <ul class="breadcrumb">
                <li>
                    <a href="#">加入购物车</a>
                </li>
                <li class="active">
                    <a href="#">确认订单信息</a>
                </li>
                <li >
                    <a href="#">完成订单</a>
                </li>
            </ul> -->
			<!-- 确认订单信息 -->
			<div class="check-stup">
				<!-- 商品确认 -->
				<div class="pro-check check ">
					<h1>确认订单信息</h1>
					<table class="data-table cart-table" cellpadding="0"
						cellspacing="0">
						<tr>
							<th class="align_center" width="10%">商品编号</th>

							<th class="align_center" width="35%" colspan="2">商品名称</th>
							<th class="align_center" width="15%">销售价格</th>
							<th class="align_center" width="10%">数量</th>
							<th class="align_center" width="20%">小计</th>
						</tr>
						<c:forEach items="${sessionScope.forder.sorders}" var="sorder"
							varStatus="num">
							<tr lang="${sorder.product.id}">
								<td class="align_center"><a href="#" class="edit">${num.count}</a>
								</td>
								<td class="align_center" width="80px"><img
									src="data:image/jpeg;base64,${sorder.product.pic}" width="80"
									height="80" /></td>
								<td class="align_left"><a class="pr_name" href="#">${sorder.sname}</a>
								</td>
								<td class="align_center vline">￥ ${sorder.sprice}</td>
								<td class="align_center vline">${sorder.snumber}</td>
								<td class="align_center vline">￥${sorder.sprice*sorder.snumber}</td>
							</tr>
						</c:forEach>
					</table>

					<div class="sum">
						<div class="fr">
							<span>总计：</span><b>￥${forder.ftotal}</b>
						</div>
					</div>

				</div>
				<!-- 订购人确认 -->
				<form
					action="${pageContext.request.contextPath}/user/forder/check/save.action"
					method="post">
					<div class="person-check check">
						<h1>订购人信息</h1>
						<div class="person-checkinner">
							<div>
								<label>配送姓名:</label> <input type="text" name="fname"
									value="${sessionScope.user.uname }" />
							</div>
							<div>
								<label>联系方式:</label> <input type="text" name="fphone"
									value="${sessionScope.user.uphone }" />
							</div>
							<div>
								<label>区域邮编:</label> <input type="text" name="fpost" />
							</div>
							<div>
								<label>配送地址:</label> <input type="text" name="faddress" />
							</div>
						</div>
					</div>
					<!-- 卖家留言 -->
					<div class="person-check check">
						<h1>卖家留言</h1>
						<textarea style="margin: 5px;" name="fremark" cols="120" rows="2">输入留言信息</textarea>
						<div class="submit">
							<input type="submit" class="sub-logo fr"
								style="margin: 0px;padding: 0px; border: 0px;" value="确认无误,购买" />
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
	<!-- 确认订单信息结束 -->
</body>
<script src="${pageContext.request.contextPath }/js/jquery.js"></script>
<%-- <script src="${shop }/js/verificationNumbers.js"></script> --%>
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
