<%@ page language="java" contentType="text/html;charset=UTF-8"
	isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<meta charset="utf-8" />
<title>后台管理系统</title>
<meta name="author" content="DeathGhost" />
<link rel="stylesheet" type="text/css" href="css/style.css">
<!--[if lt IE 9]>
<script src="js/html5.js"></script>
<![endif]-->
<script src="js/jquery.js"></script>
<script src="js/jquery.mCustomScrollbar.concat.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/ShowCalendar.js"></script>

<title>添加用户的视图</title>
<script type="text/javascript">

	(function($) {
		$(window).load(function() {

			$("a[rel='load-content']").click(function(e) {
				e.preventDefault();
				var url = $(this).attr("href");
				$.get(url, function(data) {
					$(".content .mCSB_container").append(data); //load new content inside .mCSB_container
					//scroll-to appended content 
					$(".content").mCustomScrollbar("scrollTo", "h2:last");
				});
			});

			$(".content").delegate("a[href='top']", "click", function(e) {
				e.preventDefault();
				$(".content").mCustomScrollbar("scrollTo", $(this).attr("href"));
			});

		});
	})(jQuery);
</script>
</head>
<body>
	<header>
	<h1>
		<img src="images/admin_logo.png" />
	</h1>
	<ul class="rt_nav">
		<li><a href="index.jsp" target="_blank" class="website_icon">系统首页</a></li>
		<li><a href="consult.html" class="clear_icon">查询用户</a></li>
		<li><a href="addUser.jsp " class="admin_icon">添加用户</a></li>
		<li><a href="/LogoutServlet" class="quit_icon">安全退出</a></li>
	</ul>
	</header>

	<section class="rt_wrap content mCustomScrollbar">
	<div class="rt_content">
		<div class="page_titles">
			<h2 class="fl">添加用户</h2>
			<a class="fr top_rt_btn" href="index.jsp">返回首页</a>
		</div>
		<section>
		<form style="text-align: center;"
			action="${pageContext.request.contextPath }/AddUserServlet"
			method="post">
			<ul class="ulColumn2">
				<li><span class="item_name" style="width:120px;">账号：</span> <input
					name="username" type="text" class="textbox textbox_295"
					placeholder="账号" /> <!--<span class="errorTips" >错误提示信息...</span>-->
				</li>
				<li><span class="item_name" style="width:120px;">公司名：</span> <input
					name="company" type="text" class="textbox textbox_295"
					placeholder="公司名" /> <!--<span class="errorTips">错误提示信息...</span>-->
				</li>
				<li><span class="item_name" style="width:120px;">联系方式：</span> <input
					name="tel" type="text" class="textbox textbox_295"
					placeholder="联系方式" /> <!--<span class="errorTips">错误提示信息...</span>-->
				</li>
				<li><span class="item_name" style="width:120px;">密码：</span> <input
					name="password" type="text" class="textbox textbox_295"
					placeholder="密码" /> <!--<span class="errorTips">错误提示信息...</span>-->
				</li>
				<li><span class="item_name" style="width:120px;"></span> <input
					type="submit" class="link_btn" />&nbsp;&nbsp;&nbsp;&nbsp; <input
					type="reset" class="link_btn" /></li>
			</ul>
		</form>

		<form id="form" style="text-align: center;"
			action="${pageContext.request.contextPath }/EditUserServlet"
			method="post">
			<!-- onsubmit="return makepre()" -->
			<input type="hidden" name="id" value="${c.id }" />
			<li><span class="item_name" style="width:120px;">客户姓名：</span> <input
				type="text" name="username" class="textbox textbox_295"
				value="${c.username }">
			<li>
			<li><span class="item_name" style="width:120px;">公司名：</span> <input
				name="company" type="text" class="textbox textbox_295"
				placeholder="公司名" value="${c.company }" />
			<li><span class="item_name" style="width:120px;">联系方式：</span> <input
				name="tel" type="text" class="textbox textbox_295"
				placeholder="联系方式" value="${c.tel }" /></li>
			<li><span class="item_name" style="width:120px;">密码：</span> <input
				name="password" type="text" class="textbox textbox_295"
				placeholder="密码" /></li>
			<li><span class="item_name" style="width:120px;"></span> <input
				type="submit" class="link_btn" value="重置" />&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="reset" class="link_btn" value="修改客户" /></li> <span>${message }</span>
		</form>
		</section>
	</div>
	</section>
	<script src="js/ueditor.config.js"></script>
	<script src="js/ueditor.all.min.js"> </script>
</body>
</html>




