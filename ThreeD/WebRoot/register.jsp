<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8"/>
<title>智能探识感知大数据平台管理系统登录</title>
<meta name="author" content="DeathGhost" />
<link rel="stylesheet" type="text/css" href="css/style.css" />
<style>
body{height:100%;background:#16a085;overflow:hidden;}
canvas{z-index:-1;position:absolute;}
</style>
<script src="js/jquery.js"></script>
<script src="js/verificationNumbers.js"></script>
<script src="js/Particleground.js"></script>
<script>
$(document).ready(function() {
  //粒子背景特效
  $('body').particleground({
    dotColor: '#ffffff',
    lineColor: '#ffcc00'
  });
  //验证码
  createCode();
  //测试提交，对接程序删除即可
  $(".submit_btn").click(function(){
	  location.href="index.html";
	  });
});
</script>
</head>
<body>
	<div class="wrap">

		<dl>
			<form name="form2"
				action="${pageContext.request.contextPath }/RegisterServlet"
				method="post">
				<dd>
					登陆帐号： <input type="hidden" name="fmdo" value="user"><input
						type="hidden" name="dopost" value="regok"><input
						name="username" value="${form.username }" type="text"
						class="log_input2" onchange="TestUserOk()" /> <span
						id='testCanReg'>${form.errors.username }</span>
				</dd>

				<dd>
					登陆密码： <input name="password" value="${form.password }"
						type="password" class="log_input2" /> <span id='testCanReg'>${form.errors.password }</span>
				</dd>

				<dd>
					重复密码： <input name="password2" value="${form.password2 }"
						type="password" class="log_input2" /> <span id='testCanReg'>${form.errors.password2 }</span>
				</dd>


				<dd>
					验证码： <input name="vdcode" type="text" class="log_input2" /> <img
						src="" name='1' id="1" alt="换一张" />
				</dd>

				<dd>
					<br /> <input type="submit" name="Submit2" value="确定注册"
						class="Btn" /> &nbsp;&nbsp; <input type="reset" name="Submit22"
						value="重 置" class="Btn"> <br /> <br />
				</dd>
			</form>
		</dl>
	</div>
</body>
</html>