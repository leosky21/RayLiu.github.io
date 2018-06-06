<%@ page language="java" contentType="text/html;charset=UTF-8"
     isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>


<!-- 注意：ShowCalendar.js所在文件夹应放在WebRoot里，WEB-INF外！！！！！！！！ -->
<script type="text/javascript" src="${pageContext.request.contextPath }/js/ShowCalendar.js"></script>

<title>添加用户的视图</title>
<script type="text/javascript">
    function makepre() {
        var pres = document.getElementsByName("pre");
        var preference = "";
        for(var i=0;i<pres.length;i++) {
            var input = pres[i];
            if(input.checked==true) {
                preference = preference + input.value + ","; 
            }
        }
        preference = preference.substring(0, preference.length-1); // preference="唱歌,跳舞,夜生活";

        // 在form表单创建一个input隐藏输入项，如：<input type="hidden" name="preference" value="唱歌,跳舞,夜生活,...">
        var form =  document.getElementById("form");
        var input = document.createElement("input");
        input.type = "hidden";
        input.name = "preference";
        input.value = preference;

        form.appendChild(input);
        return true;
    }
</script>
</head>
<body style="text-align: center;">
 <%@include file="/index.jsp" %>
    <br/>
    <form id="form" action="${pageContext.request.contextPath }/AddCustomerServlet" method="post">
    <table border="1" width="50%" align="center">
    	
        <tr>
            <td>客户姓名</td>
            <td>
                <input type="text" name="name" />
            </td>
        </tr>
        <tr>
            <td>客户性别</td>
            <td>
                <!-- <input type="radio" name="gender" value="男">男
                <input type="radio" name="gender" value="女">女
                <input type="radio" name="gender" value="人妖">人妖 -->
                <!-- 页面到底输出几个性别，不是在页面中显示的，而是由一个程序维护的，这个程序有几个性别，页面中就输出几个性别  -->
                <c:forEach var="gender" items="${genders }"> 
                	 <input type="radio" name="gender" value="${gender }">${gender }
                </c:forEach>
            </td>
        </tr>
        <tr>
            <td>生日</td>
            <td>
              <!--   <input type="text" name="birthday" id="birthday" onclick="showCalendar(this.id)"> -->
                <input type="text" name="birthday" id="birthday"   title="点击选择" onclick="showCalendar(this.id)"> 
            </td>
        </tr>

        <tr>
            <td>手机</td>
            <td>
                <input type="text" name="cellPhone" />
            </td>
        </tr>
        <tr>
            <td>邮箱</td>
            <td>
                <input type="text" name="email" />
            </td>
        </tr>
        <tr>
            <td>爱好</td>
            <td>
                <!-- <input type="checkbox" name="pre" value="唱歌">唱歌
                <input type="checkbox" name="pre" value="跳舞">跳舞
                <input type="checkbox" name="pre" value="逛夜店">逛夜店 -->
                <c:forEach var="p" items="${preferences }">
                    <input type="checkbox" name="preference" value ="${p }">${p }
                </c:forEach>
            </td>
        </tr>
        <tr>
            <td>客户类型</td>
            <td>
                <!-- <input type="radio" name="type" value="VIP客户">VIP客户
                <input type="radio" name="type" value="重点客户">重点客户
                <input type="radio" name="type" value="一般客户">一般客户 -->
                 <c:forEach var="type" items="${types }">
                    <input type="radio" name="type" value="${type }">${type }
                </c:forEach>
            </td>
        </tr>
        <tr>
            <td>客户备注</td>
            <td>
                <textarea rows="5" cols="60" name="description"></textarea>
            </td>
        </tr>
        <tr>
            <td>
                <input type="reset" value="重置">
            </td>
            <td>
                <input type="submit" value="添加客户">
            </td>
        </tr>
        
    </table>
    <span>${message }</span>
    </form>
</body>
</html>