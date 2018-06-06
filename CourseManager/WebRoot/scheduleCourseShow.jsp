<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
	<head>
		<link rel="stylesheet" href="css/bootstrap.css" type="text/css">
		<script type="text/javascript" src="js/jquery-3.1.1.js"></script>
		<script type="text/javascript" src="js/bootstrap.js"></script>
		<title>数据库课程设计之高校智能排课系统</title>
		<style type="text/css">
			.main{
				text-align: center;
			}
			.main ul{
				display: inline-block;
			}
			.mid{
				text-align: center;
			}
			.font_big{
				font-size: 30px;
			}
			body{
				margin-top: 75px;
				background: #000 url(img/background.jpg) center center fixed no-repeat;
				-moz-background-size:cover;
				-webkit-background-size:cover;
				-ms-background-size:cover;
				-o-background-size:cover;
				background-size:cover;
				}
				/*1024x768*/
				@media only all and (max-width:1024px) (max-heigth: 768px){
					body{
						margin-top: 75px;
						-moz-background-size:1024px 768px;
						-webkit-background-size:1024px 768px;
						-ms-background-size:1024px 768px;
						-o-background-size:1024px 768px;
						background-size:1024px 768px;
					}
				}
				/*640x480*/
				@media only all and (max-width:1024px) (max-heigth: 768px){
					body{
						margin-top: 75px;
						-moz-background-size:640px 480px;
						-webkit-background-size:640px 480px;
						-ms-background-size:640px 480px;
						-o-background-size:640px 480px;
						background-size:640px 480px;
					}
				}
		</style>
	</head>
	<body style="width:100%; height:100%;">
		<div class="main">
	  		 <ul class="nav nav-pills">
				<li><a href="teacherInfoShow.jsp">教师信息</a><span></span></li>
				<li><a href="classInfoShow.jsp" >班级信息</a><span></span></li>
				<li><a href="courseInfoShow.jsp">课程信息</a><span></span></li>
				<li><a href="classCourseInfoShow.jsp">班级课程信息</a><span></span></li>
				<li><a href="manaCourse.jsp">教务信息</a><span></span></li>
				<li><a href="test/user.jsp">个人信息</a><span></span></li>
			</ul>
	  	</div>
				<div class="col-lg-8 col-lg-offset-2">
						<div class="panel panel-primary">
							<div class="panel-heading font_big">数据库课程设计之高校智能排课系统</div>
							<div class="panel-body">
								<div class="jumbotron col-lg-12 page-hearder" >
									<table class="table table-bordered table-hover">
										<caption>课程表</caption>
									   		<tr>
									   			<th>节号</th>
									   			<!-- <th>时间段</th> -->
									   			<th>周一</th>
									   			<th>周二</th>
									   			<th>周三</th>
									   			<th>周四</th>
									   			<th>周五</th>
									   		</tr>
									   		<c:forEach var="ci" items="${courseList}" varStatus="ct" >
									   			<tr>
									   				 <td>${ct.count}</td>
									   			<%-- <td>${ci.TimeOf}</td>  --%>
									   				<td>${ci.SMonday }</td>
									   				<td>${ci.STuesday}</td>
									   				<td>${ci.SWensday }</td>
									   				<td>${ci.SThursday}</td>
									   				<td>${ci.SFriday }</td>
									   			</tr>
									   		</c:forEach>
									  </table>
									</div>
								</div>
							<div class="panel-footer mid">数据库课程设计之高校智能排课系统 计算机工程学院版权所有</div>
					</div>
		</div>
	</body>
</html>

