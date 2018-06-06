<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head> 
    	<title>排课</title>
		<meta charset="utf-8">
		<link rel="stylesheet" href="css/bootstrap.css" type="text/css">
		<script type="text/javascript" src="js/jquery-3.1.1.js"></script>
		<script type="text/javascript" src="js/bootstrap.js"></script>
		<script type="text/javascript" src="js/jquery-3.1.1.js"></script>
		<style type="text/css">
				.font_big{
					font-size: 30px;
				}
				.mid{
					text-align: center;
				}
				body{
					margin-top: 150px;
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
							margin-top: 150px;
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
							margin-top: 150px;
							-moz-background-size:640px 480px;
							-webkit-background-size:640px 480px;
							-ms-background-size:640px 480px;
							-o-background-size:640px 480px;
							background-size:640px 480px;
						}
					}
					select{
						height: 40px;
						font-size: 20px;
						margin-right: 40px;
						border-radius: 5px;
					}
					.main{
						text-align: center;
						margin-left: 340px;
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
			<li class="active"><a href="manaCourse.jsp">教务信息</a><span></span></li>
			<li><a href="test/user.jsp">个人信息</a><span></span></li>
		</ul>
  	</div>
	<div class="col-lg-6 col-lg-offset-3">
 		 <div class="panel panel-primary">
			<div class="panel-heading font_big">数据库课程设计之高校智能排课系统</div>
				<div class="panel-body">
					<div class="jumbotron col-lg-12 page-hearder" >
						<form id="ff" class="bs-example bs-example-form mid" name="ff" method="post" action="mymana!doArrange.action">
							<select name="select"  class="selectpicker" data-style="btn-info" >
								<option value="add">排课</option>
								<option value="resetAll">清除排课</option>
							</select>
							<input type="submit" class="btn-lg btn-info" name="btn" id="btn" value="开始！">
							<p> 
								<label for="button"></label>
							</p>	
					</form>
					<form id="pp" class="bs-example bs-example-form mid" name="ff" method="post" action="mymana!doShow.action">
						<select name="showClass" data-style="btn-primary" class="selectpicker">
							<option value="class1">class1</option>
							<option value="class2">class2</option>
							<option value="class3">class3</option>
							<option value="class4">class4</option>
						</select> 
						<input type="submit" name="btn" class="btn-lg btn-default" id="btn" value="查看班级课程！">
						<p>
							<label for="button"></label>	
						</p>
					</form>
				</div>
			</div>
			<div class="panel-footer mid">淮海工学院职业介绍管理系统 计算机工程学院版权所有</div>
		</div>
		</div>
		</body>
</html>

