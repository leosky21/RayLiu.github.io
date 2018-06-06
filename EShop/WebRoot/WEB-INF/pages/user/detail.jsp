<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"
	isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/detail.css" />

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
					<div class="welcom fl">欢迎光临E+线上商城!</div>
					<ul class="top_links fr">
						<li><a href="${pageContext.request.contextPath}/send/main/pages/user/index">首页</a></li>
						<li><a href="#">我的账户</a></li>
						<li><a href="${pageContext.request.contextPath}/send/main/user/shoppingCart">购物车</a></li>
						<li class="highlight"><a href="#">商品详情</a></li>
						<li class="highlight"><a href="#">Welcome : <c:if
									test="${empty user}">未登录!</c:if> <c:if test="${!empty user}">${user.unick }</c:if>
						</a></li>
					</ul>
					<!--头部小导航结束-->
					<!-- logo -->
					<h1 class="logo clear fl">
						<a href="index.jsp"> <img src="${pageContext.request.contextPath }/images/logo3.png" />
						</a>
					</h1>
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

		<div class="navigation_container">
			<!-- 面包屑 -->
			<ul id="first-carousel" class="breadcrumb">
				<li class="active"><a href="#"
					style="font-size: 20px;background-color: ">${product.category.type}
				</a></li>
			</ul>
			<div class="nav"></div>
			<!-- 产品详情 -->
			<div id="product_detail">
				<!--详情左侧-->
				<div class="product_leftcol fl">
					<div id="jingdong">
						<div class="datu">
							<img id="pic" src="data:image/jpeg;base64,${product.pic}"
								width="200px" height="240px" />
							<div id="fangdajing"></div>
						</div>
						<div class="xiaotu">
							<div class="leftbut"></div>
							<div class="tu">
								<ul>
									<li><img src="/images/0.png" /></li>
									<li><img src="images/1.png" /></li>
									<li><img src="images/2.png" /></li>
									<li><img src="images/3.png" /></li>
									<li><img src="images/4.png" /></li>
									<li><img src="images/5.png" /></li>
									<li><img src="images/6.png" /></li>
									<li><img src="images/7.png" /></li>
									<li><img src="images/8.png" /></li>
								</ul>
							</div>
							<div class="rightbut"></div>
						</div>
					</div>
				</div>
				<!--详情左侧结束-->
				<!--详情右侧-->
				<div class="product_rightcol fr">
					<div id="name">
						<h1>${product.name}</h1>
						<strong>支持货到付款，新店开张，全场低至三折包邮，已购买运费险，七天无理由退换，让您购物无忧！</strong>
					</div>
					<ul id="summary">
						<li id="summary-market">
							<div class="dt">参&nbsp;考&nbsp;价：</div>

							<div class="dd">
								<del id="page_maprice">
									¥
									<fmt:formatNumber type="number" pattern="#.99">
                            ${product.price * 1.2}
                            </fmt:formatNumber>
								</del>
							</div>
						</li>
						<li id="summary-price">
							<div class="dt">折&nbsp;后&nbsp;价：</div>
							<div class="dd">
								<strong class="p-price" id="jd-price">￥${product.price}</strong>
							</div>
						</li>
					</ul>
					<ul id="choose">
						<!--  <li id="choose-color" class="choose-color-shouji">
                            <div class="dt">选择颜色：</div>
                            <div class="dd">
                                <div class="item">
                                    <b></b>
                                    <a href="#none" title="白色">
                                        <img data-img="1" src="images/11.jpg" width="25" height="25" alt="白色 "> <i>白色</i>
                                    </a>
                                </div>
                                <div class="item  selected">
                                    <b></b>
                                    <a href="#none" title="黑色">
                                        <img data-img="1" src="images/22.jpg" width="25" height="25" alt="黑色 "> <i>黑色</i>
                                    </a>
                                </div>
                            </div>
                        </li>-->
						<li id="choose-version">
							<div class="dt">简单描述：</div>
							<br />
							<div class="dd">
								<div class="item" class="item  selected">
									<b></b> <a href="#none" style="cursor: pointer;">${product.remark }</a>
								</div>
							</div>
						</li>

						<li id="choose-amount">
							<div class="dt">具体描述：</div>
							<br />
							<div class="dd">
								<div class="item" class="item  selected">
									<b></b>
									<textarea style="cursor:text ;" readonly="readonly" cols="80"
										rows="6">${product.xremark }</textarea>
								</div>
							</div>
						</li>
					</ul>
					<div class="add_to_buttons">

						<a
							href="${pageContext.request.contextPath}/user/check/sorder/addSorder.action?id=${product.id}">
							<button class="add_cart">加入购物车</button>
						</a>

					</div>
				</div>
				<!--详情右侧结束-->
			</div>
			<!--产品详情结束-->
			<!-- 产品列表 -->
			<!-- 产品列表 -->
			<%-- 	<c:forEach items="${requestScope.ExpandProduct}" var="list"> --%>
			<div class="products_list products_slider clear">
				<!-- 显示类别名称 -->
				<h2 class="sub_title">同类别更多惊喜!</h2>
				<ul id="first-carousel"
					class="first-and-second-carousel jcarousel-skin-tango">

					<c:forEach items="${requestScope.ExpandProduct }" var="product">
						<li><a href="${pageContext.request.contextPath}/product/get.action?id=${product.id}"
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
			<%-- </c:forEach> --%>
			<!-- 导航栏结束 -->
			<div class="footer_container">
			<div class="footer">
				<ul class="footer_links">
					<li><span><a href="${pageContext.request.contextPath }/send/main/aindex">后台管理</a></span>
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
