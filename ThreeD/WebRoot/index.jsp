<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>智能探识感知大数据平台</title>
<link href="css/style_index.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>
<script src="js/commonss.min.js"></script>
<style>
		*{
			padding: 0;
			margin: 0;
		}
		body{
			margin-top: -20px;
		}
	</style>
</head>
<body onLoad="sendRequest()">
<div class="videozz"></div>
	<video  autoplay muted loop poster="images/fallba1ck.jpg">
		<source src="images/movs.mp4">		
		你的游览器不支持video支持
	</video>
<div class="box">
	<div class="box-a">
    <div class="m-2">
          <div class="m-2-1">
            <form action="${PageContext.requestScope.context }/ThreeD/LoginServlet" method="post">
                <div class="m-2-2">
                   <input type="text" name="username" placeholder="请输入账号" />
                </div>
                <div class="m-2-2">
                   <input type="password" name="password"  placeholder="请输入密码"/>
                </div>
                <div class="m-2-2-1">
                   <input type="text" placeholder="请输入验证码"/>
                   <img src="images/1.png"/>
                </div>
                <div class="m-2-2">
                   <input type="submit" value="登录" />
                </div>     
            </form>
          </div>
    </div>
    
	
    <div class="m-5"> 
    <div id="m-5-id-1"> 
    <div id="m-5-2"> 
    <div id="m-5-id-2">  
    </div> 
    <div id="m-5-id-3"></div> 
    </div> 
    </div> 
    </div>   
    <div class="m-10"></div>
    <div class="m-xz7"></div>
    <div class="m-xz8 xzleft"></div>
    <div class="m-xz9"></div>
    <div class="m-xz9-1"></div>
    <div class="m-x17 xzleft"></div>
    <div class="m-x18"></div>
    <div class="m-x19 xzleft"></div>
    <div class="m-x20"></div>  
    <div class="m-8"></div>
    <div class="m-9"><div class="masked1" id="sx8">智能探识感知大数据平台</div></div> 
    <div class="m-11">
    	<div class="m-k-1"><div class="t1"></div></div>
        <div class="m-k-2"><div class="t2"></div></div>
        <div class="m-k-3"><div class="t3"></div></div>
        <div class="m-k-4"><div class="t4"></div></div>
        <div class="m-k-5"><div class="t5"></div></div>
        <div class="m-k-6"><div class="t6"></div></div>
        <div class="m-k-7"><div class="t7"></div></div>
    </div>   
    <div class="m-14"><div class="ss"></div></div>
    <div class="m-15-a">
    <div class="m-15-k">
    	<div class="m-15xz1">
            <div class="m-15-dd2"></div>
        </div>
    </div>
    </div>
    <div class="m-16"></div>
    <div class="m-17"></div>
    <div class="m-18 xzleft"></div>
    <div class="m-19"></div>
    <div class="m-20 xzleft"></div>
    <div class="m-21"></div>
    <div class="m-22"></div>
    <div class="m-23 xzleft"></div>
    <div class="m-24" id="localtime"></div>
    <div class="footer">
			<p>项目名称:<a target="_blank">教育部高教司-达内科技集团产学合作协同育人项目</a></p>
	</div>
    </div>
</div>
</body>
</html>