<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<link rel="stylesheet" href="${pageContext.request.contextPath }/css/public.css" type="text/css"></link>  
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css"></link>  
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/detail.css" type="text/css"></link> 
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

<body>
<input type="hidden" id="shopValue" value="${pageContext.request.contextPath }"> 
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
						<a href="${pageContext.request.contextPath}/send/main/pages/user/index"> <img
							src="${pageContext.request.contextPath }/images/logo3.png" />
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
		<!-- 头部结束 -->

		<div class="section_container">
			<!-- 购物车 -->
			<div id="shopping_cart">
				<div class="message success">我的购物车</div>
				<table class="data-table cart-table" cellpadding="0" cellspacing="0">
					<tr>
						<th class="align_center" width="10%">商品编号</th>
						<th class="align_left" width="35%" colspan="2">商品名称</th>
						<th class="align_center" width="10%">销售价格</th>
						<th class="align_center" width="20%">数量</th>
						<th class="align_center" width="15%">小计</th>
						<th class="align_center" width="10%">删除</th>
					</tr>
					<c:forEach items="${sessionScope.forder.sorders }" var="sorder"
						varStatus="num">
						<tr lang="${sorder.product.id}">
							<td class="align_center"><a href="#" class="edit">${num.count }</a>
							</td>
							<td width="80px"><img
								src="data:image/jpeg;base64,${sorder.product.pic}" width="80"
								height="80" /> <%-- img
						src="${pageContext.request.contextPath}/files/${sorder.product.pic}" width="80" height="80" /> --%>
							</td>
							<td class="align_left"><a class="pr_name" href="${pageContext.request.contextPath}/product/get.action?id=${sorder.product.id}">${sorder.sname }</a>
							</td>
							<td class="align_center vline">
								<fmt:formatNumber type="number"  pattern="#.##">${sorder.sprice }</fmt:formatNumber>
							</td>
							
							<td class="align_center vline">
								<!-- 文本框 --> 
								<input class="text" style="height: 20px;"
										value="${sorder.snumber }"  lang="${sorder.snumber }">
							</td>
							<td class="align_center vline">
								<fmt:formatNumber type="number"  pattern="#.##">${sorder.sprice*sorder.snumber }
								</fmt:formatNumber>
							</td>
							<td class="align_center vline"><a href="#" class="remove"></a>
							</td>
						</tr>
					</c:forEach>

				</table>
				<!-- 结算 -->
				<div class="totals">
					<table id="totals-table">
						<tbody>
							<tr>
								<td width="60%" colspan="1" class="align_left"><strong>小计</strong>
								</td>
								<td class="align_right" style=""><strong>￥<span
										class="sprice" id="ftotal">
										<fmt:formatNumber type="number"  pattern="#.##">${sessionScope.forder.ftotal}</fmt:formatNumber>
										</span>
								</strong>
								</td>
							</tr>
							<tr>
								<td width="60%" colspan="1" class="align_left">运费</td>
								<td class="align_right" style="">￥<span class="sprice"
									id="yunfei">0.00</span>
								</td>
							</tr>
							<tr>
								<td width="60%" colspan="1" class="align_left total"><strong>总计</strong>
								</td>
								<td class="align_right" style="">￥<span class="ftotal"
									id="totalAll"><strong>${sessionScope.forder.ftotal}</strong>
								</span>
								</td>
							</tr>
						</tbody>
					</table>
					<div class="action_buttonbar">
						<font><a href="${pageContext.request.contextPath}/send/main/user/confirm">
								<button type="button" title="" class="checkout fr"
									style="background-color: #f38256;">订单确认</button>
						</a></font>
						<button type="button" title="" class=" fr">
							<font><font>清空购物车</font> </font>
						</button>
						<a href="${pageContext.request.contextPath}/send/main/pages/user/index">
							<button type="button" title="" class="continue fr">
								<font>继续购物</font>
							</button>
						</a>
						<div style="clear:both"></div>
					</div>
				</div>
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
		</div>
	</div>
	
</body>
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
<script type="text/javascript">
	 var shop = $('#shopValue').val(); 
	$(function() {
		//注册事件
		$(".text").change(function() {
			//验证数据的有效性
			var snumber = this.value; //也可以使用$(this).val();
			//isNaN(number)表示若number不是数字就返回真
			if (!isNaN(snumber) && parseInt(snumber) == snumber && snumber > 0) {
				//如果合法，同步更新的数
				$(this).attr("lang", snumber);
				//找到当前标签中第一个是tr的父节点，然后拿到属性为lang的值，也就是商品的id
				var pid = $(this).parents("tr:first").attr("lang");
				//发送Ajax请求，传输当前的数量与商品的id，返回修改数量后的总价格
				$.post(shop + "/user/check/sorder/updateSorder.action", {
					snumber : snumber,
					'product.id' : pid
				}, function(ftotal) {
					$("#ftotal").html(ftotal); //所有商品的小计
					var yunfei = $("#yunfei").html();
					$("#totalAll").html((ftotal * 1 + yunfei * 1).toFixed(2)); //所有商品小计和运费的和
				}, "text");
				//计算单个商品的小计，保留两位小数
				var sprice = ($(this).parent().prev().html() * snumber).toFixed(2);
				$(this).parent().next().html(sprice);
			} else {
				//如果非法，还原为刚刚合法的数
				this.value = $(this).attr("lang");
			}
		})

	})
</script>
</html>
