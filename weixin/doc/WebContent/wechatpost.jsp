<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>响应微信post方法模拟测试</title>
<script type="text/javascript">
	function wechatPost(){
		var form=document.forms[0];
		form.action="/weixin/selectController/getSelectJson";
		form.method="post";
		form.submit;
	}

</script>
</head>
<body>

<h3>将页面数据传递到后台</h3>
	<form action="/weixin/selectController/getSelectJson" method="post">
	    <input type="text" name="wechat">
		关键字:<input type="text" name="keyword" />
		<label for="startDate">开始时间:</label> 
		<input id="start" name="start"
					class="Wdate" type="text" value="2016-01-01" size="15"/>
					
		<label for="endDate">结束时间:</label> 
		<input type="text" id="end"
					name="end" size="15" class="Wdate" value="2016-01-02" />
		<input type="submit" value="添加" />
	</form>
</body>
</html>