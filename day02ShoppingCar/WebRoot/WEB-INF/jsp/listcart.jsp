<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>购物车</title>
    
	<script type="text/javascript">
    function deleteitem(id) {
        var b = window.confirm("您确认删除吗？");
        if(b) {
            // window代表当前浏览器窗口，location代表当前浏览器窗口的地址栏
            window.location.href = "${pageContext.request.contextPath }/DeleteItemServlet?id="+id;
        }
    }

    function clearCart() {
        var b = window.confirm("您确认清空吗？");
        if(b) {
            window.location.href = "${pageContext.request.contextPath }/ClearCartServlet"
        }
    }

    function changeQuantity(input, id, oldValue) {
        var quantity = input.value; // 得到要修改的数量
        /*
        // 检查用户输入的数量是不是一个数字
        if(isNaN(quantity)) {
            alert("请输入数字！！！");
            // 得到输入项原来的值
            input.value = oldValue;
            return;
        }
        */

        // 检查用户输入的数量是不是一个正整数
        if(quantity<0 || quantity!=parseInt(quantity)) {    // 1.1 != 1     parseInt("abc")返回NaN
            alert("请输入一个正整数！！！");
            input.value = oldValue;
            return;
        }

        var b = window.confirm("您确定把书的数量修改为"+quantity+"吗？")
        if(b) {
            window.location.href = "${pageContext.request.contextPath }/ChangeQuantityServlet?id="+id+"&quantity="+quantity;
        }
    }
</script>

  </head>
  
  <body style="text-align: center;">
    <h1>购物车列表</h1>
    <c:if test="${empty(cart.map) }"> <!-- cart.map.empty：map为空，没东西       el表达式的函数：empty()——检测map是否为null或""，若是则返回true   -->
        您没有购买任何商品！！！
    </c:if>

    <c:if test="${!empty(cart.map) }">
    <table width="70%" border="1" align="center">
        <tr>
            <td>书名</td>
            <td>作者</td>
            <td>单价</td>
            <td>数量</td>
            <td>小计</td>
            <td>操作</td>
        </tr>

        <c:forEach var="entry" items="${cart.map }">   <!-- entry<id, CartItem> -->
            <tr>
                <td>${entry.value.book.name }</td>
                <td>${entry.value.book.author }</td>
                <td>${entry.value.book.price }</td>
                <td>                                                                                                <!-- this代表input输入项 -->              
                    <input type="text" name="quantity" value="${entry.value.quantity }" style="width: 35px" onchange="changeQuantity(this, ${entry.key }, ${entry.value.quantity })" />
                </td>
                <td>${entry.value.price }</td>
                <td>
                    <!-- javascript:void(0)：去掉超链接的默认行为 -->
                    <a href="javascript:void(0)" onclick="deleteitem(${entry.key })">删除</a>
                </td>
            </tr>
        </c:forEach>
        <tr>
            <td colspan="3">总价</td>
            <td colspan="2">${cart.price }元</td>
            <td colspan="1">
                <a href="javascript:void(0)" onclick="clearCart()">清空购物车</a>
            </td>
        </tr>
    </table>
    </c:if>
</body>
</html>
