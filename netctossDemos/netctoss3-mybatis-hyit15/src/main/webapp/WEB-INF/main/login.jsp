<%@ page contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
        <title>达内－NetCTOSS</title>
        <link type="text/css" rel="stylesheet" media="all" href="../styles/global.css" />
        <link type="text/css" rel="stylesheet" media="all" href="../styles/global_color.css" />
    </head>
    <body class="index">
        <div class="login_box">
        		<!-- 当前页面：netctoss2/login/toLogin.do
        			目标路径： netctoss2/login/checkLogin.do -->
        		<form action="checkLogin.do" method="post">
            <table>
                <tr>
                    <td class="login_info">账号：</td>
                    <td colspan="2"><input name="adminCode" value="${adminCode}" type="text" class="width150" /></td>
                    <td class="login_error_info"><span class="required">30长度的字母、数字和下划线</span></td>
                </tr>
                <tr>
                    <td class="login_info">密码：</td>
                    <td colspan="2"><input name="password" value="${password}" type="password" class="width150" /></td>
                    <td><span class="required">30长度的字母、数字和下划线</span></td>
                </tr>
                <tr>
                    <td class="login_info">验证码：</td>
                    <td class="width70"><input name="imgCode" type="text" class="width70" /></td>
                    <td><img src="genimage" onclick="this.src='genimage?x='+Math.random();" alt="验证码" title="点击更换" /></td>  
                    <td><span class="required">验证码错误</span></td>              
                </tr>            
                <tr>
                    <td></td>
                    <td class="login_button" colspan="2">
                    		<!-- type="submit"提交按钮，\本质上就是触发表单的
                    			onsubmit事件，可直接使用表单的submit方法触发 -->
                        <a href="javascript:document.forms[0].submit();"><img src="../images/login_btn.png" /></a>
                    </td>    
                    <td><span class="required">${error}</span></td>                
                </tr>
            </table>
            </form>
        </div>
    </body>
</html>