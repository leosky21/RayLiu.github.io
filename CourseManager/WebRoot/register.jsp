<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  	<meta charset="utf-8" />
		<link rel="stylesheet" href="css/bootstrap.css" type="text/css">
		<script type="text/javascript" src="js/jquery-3.1.1.js"></script>
		<script type="text/javascript" src="js/bootstrap.js"></script>
		<title>数据库课程设计之高校智能排课系统</title>
		<style type="text/css">
			*{margin: 0px;padding: 0px;}
			.main{
				margin-top:0;
				text-align: center;
			}
			.main ul{
				display: inline-block;
			}
			.font_big{
				font-size: 30px;
			}
			.mid{
				text-align: center;				
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
    <title>no accout,register!</title>
	<link rel="stylesheet" type="text/css" href="CSS/loginCss.css">
  </head>
  <!-- ！！！加一个自动验证的js -->
  <body>
  	<div class="col-lg-6 col-lg-offset-3">
				<div class="panel panel-primary">
					<div class="panel-heading font_big">数据库课程设计之高校智能排课系统</div>
					<div class="panel-body">
						<div class="jumbotron col-lg-8 col-lg-offset-2 page-hearder" >
							<form id="ff" name="rr" class="bs-example bs-example-form" role="form" action="myregister!doRegister.action">
						        <div class="input-group">
						            <span class="input-group-addon">用户名:</span>
						            <input type="text" name="userName" id="userName" class="form-control">
						        </div>
						        <br>
						        <div class="input-group">
						            <span class="input-group-addon">编号:</span>
						            <input type="text" name="userID" id="userID" class="form-control">
						        </div>
						        <br>
						        <div class="input-group">
						            <span class="input-group-addon">密码:</span>
						            <input type="password" name="userPass" id="pass" class="form-control">
						        </div>
						        <br>
						        <div class="input-group">
						            <span class="input-group-addon">再次输入密码:</span>
						            <input type="password" name="Pass" id="pass"class="form-control">
						        </div>
						        <br>
						        <div class="input-group">
						            <span class="input-group-addon">电话:</span>
						            <input type="text" name="Tel" id="Tel" class="form-control">
						        </div><br>
						        <br>
						        <div class="col-lg-12 mid">
						        		<input type="submit" formmethod="post" class="btn btn-info" onclick="error()">
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<input type="reset" class="btn btn-danger"><br /><br />
									<a href="login.jsp">不注册了</a>
						       </div>								
						</form>
					</div>
				</div>
				<div class="panel-footer mid">数据库课程设计之高校智能排课系统 计算机工程学院版权所有</div>
			</div>
		</div>		
  </body>
</html>