<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta charset="utf-8">
<title>数据库课程设计之高校智能排课系统</title>
<style>
*{ margin:0; padding:0; list-style:none;}
img{ border:0;}

.header{ width:100%; background:#F5F5F5;}
.nav{ width:1000px; margin:0 auto; overflow:hidden;}
.nav ul li{ height:40px; line-height:40px; float:left; padding:10px 5px; margin:0px 5px;position:relative;}
.nav ul li a{ color:#666; font-family:'Microsoft Yahei'; font-size:14px; text-decoration:none;}
.nav ul li a:hover{ color:#000; text-decoration:none;}
.nav ul li span{ display:block; position:absolute; width:0px; height:0px; background:#1FAEFF; top:58px; left:50%;}
</style>

</head>
<body>

<div class="header">
	<div class="nav">
		<ul>
			<li><a href="test/teacher.jsp">教师信息</a><span></span></li>
			<li><a href="test/class.jsp" >班级信息</a><span></span></li>
			<li><a href="test/course.jsp">课程信息</a><span></span></li>
			<li><a href="test/classcourse.jsp">班级课程信息</a><span></span></li>
			<li><a href="manaCourse.jsp">教务信息</a><span></span></li>
			<li><a href="test/user.jsp">个人信息</a><span></span></li>
		</ul>
	</div>
</div>
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript">
$(function(){
	$('.nav li').hover(function(){
		$('span',this).stop().css('height','2px');
		$('span',this).animate({
			left:'0',
			width:'100%',
			right:'0'
		},200);
	},function(){
		$('span',this).stop().animate({
			left:'50%',
			width:'0'
		},200);
	});
});
</script>


<script src="http://www.mycodes.net/js/tongji.js"></script>
<center><script src="http://www.mycodes.net/js/youxia.js" type="text/javascript"></script></center>
</body>
</html>

