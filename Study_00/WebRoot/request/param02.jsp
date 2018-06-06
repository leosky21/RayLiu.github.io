<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>各种表单输入项数据的获取</title>
</head>
<body>
    <form action="/day06/RequestDemo3" method="post">
        用户名：<input type="text" name="username"><br/>
        密码：<input type="password" name="password"><br/>
        性别：
             <input type="radio" name="gender" value="male">男
             <input type="radio" name="gender" value="female">女<br/>
        所在地：
             <select name="city">
                <option value="beijing">北京</option> <!-- value代表提交给服务器的值，每一个数据必须要有一个名称，服务器根据名称获取对应的值-->
                <option value="shanghai">上海</option>
                <option value="wuhan">武汉</option>
             </select><br/>
        爱好：
             <input type="checkbox" name="likes" value="sing">唱歌
             <input type="checkbox" name="likes" value="dance">跳舞
             <input type="checkbox" name="likes" value="basketball">篮球
             <input type="checkbox" name="likes" value="football">足球<br/>
        备注： <textarea rows="6" cols="60" name="description"></textarea><br/>
        大头照： <input type="file" name="image"><br/>
        <input type="hidden" name="id" value="123456"> <!-- 隐藏输入项 -->
        <input type="submit" value="提交">
    </form>
</body>
</html>
