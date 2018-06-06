<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    	<title>数据库课程设计之高校智能排课系统</title>
    	<meta charset="utf-8">
			<meta http-equiv="pragma" content="no-cache">
			<meta http-equiv="cache-control" content="no-cache">
			<meta http-equiv="expires" content="0">    
			<meta http-equiv="keywords" content="中学,排课,哈哈哈哈">
			<meta http-equiv="description" content="某中学的排课系统">
			<link rel="stylesheet" href="css/bootstrap.css" type="text/css">
			<script type="text/javascript" src="js/jquery-3.1.1.js"></script>
			<script type="text/javascript" src="js/bootstrap.js"></script>
			<style type="text/css">
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
				<script type="text/javascript" src="js/checkLogin12.js"></script>
					<%String userName="";
					if(!session.isNew())
						{userName=(String)session.getAttribute("userName");
					if (userName==null)
						userName="";}
					%>
		  	</head>
  <!-- 还有一个问题是如何让登陆校验行之有效，请教龙，关于js获取前台element，并读取值 -->
  <body >
  	<div class="col-lg-6 col-lg-offset-3">
				<div class="panel panel-primary">
					<div class="panel-heading font_big">数据库课程设计之高校智能排课系统</div>
					<div class="panel-body">
						<div class="jumbotron col-lg-10 col-lg-offset-1 page-hearder" >
					
					  	<form id="ff" class="bs-example bs-example-form" name="ff"  method="post" action="mylogin!doLogin.action"  onsubmit="return checkForm()" >
								<div class="input-group col-lg-8 col-lg-offset-2">
						            <span class="input-group-addon">用户名:</span>
						            <input type="text" class="form-control" placeholder="用户名" name="userName" id="user" >
						        </div>
						        <br>
						       <div class="input-group col-lg-8 col-lg-offset-2">
						            <span class="input-group-addon">编号:</span>
						            <input type="text" class="form-control" placeholder="编号" name="userID" id="userid" >
						        </div>
						        <br>
						       <div class="input-group col-lg-8 col-lg-offset-2">
						            <span class="input-group-addon">密码:</span>
						            <input type="password" class="form-control" placeholder="密码" name="Pass" id="pwd" >
						        </div>
						        <br>
						        <br>
						      <br>
						        <div class="input-group col-lg-8 col-lg-offset-2">
						        	<input type="submit" name="btn"   class="btn btn-info col-lg-offset-4" value="提交">
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<input type="reset" name="btn2" class="btn btn-danger" value="重置">
						      </div>
						      <br>
						      <br>
								<div class="input-group col-lg-4 col-lg-offset-4">
									<label for="register"></label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<a href="register.jsp" class="col-lg-offset-4">没法登陆？</a>
							  </div>
							</form>
						</div>
					</div>
					<div class="panel-footer mid">数据库课程设计之高校智能排课系统 计算机工程学院版权所有</div>
				</div>	
			</div>
		</div>
  </body>
</html>
