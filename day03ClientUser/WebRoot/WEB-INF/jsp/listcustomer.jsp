<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/sub" prefix="sub" %>  <!-- 导入自定义函数。 -->

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>列出所有客户</title>
<style type="text/css">
    .even {background-color: #FFF000}
    .odd {background-color: #FFCCFF}
    tr:HOVER {background-color: #FF99FF}    /* 注意需要更高版本的文档声明  */
</style>

</head>
<body style="text-align: center;">
	<%@include file="/index.jsp" %>
    <table frame="border" width="85%">
        <tr>
            <td>客户姓名</td>
            <td>性别</td>
            <td>生日</td>
            <td>手机</td>
            <td>邮箱</td>
            <td>爱好</td>
            <td>类型</td>
            <td>备注</td>
            <td>操作</td>
        </tr>
        <c:forEach var="c" items="${requestScope.pagebean.list }" varStatus="status">
            <tr class="${status.count%2==0?'even':'odd' }">
                <td>${c.name }</td>
                <td>${c.gender }</td>
                <td>${c.birthday }</td>    <!-- birthday是Date类型的对象，虽然取出来的是Date类型的对象，但el表达式非常聪明，它会自动调用此对象的toString()方法，将其变成一个字符串，并且它内部会根据你到底是哪个国家和地区而去选用相对应的toString()方法 -->
                <td>${c.cellPhone }</td>
                <td>${c.email }</td>
              	 <td>${sub:sub({c.preference }) }</td>   <!-- 使用自定义函数。 -->
                <td>${c.type }</td>
                <td>${sub:sub({c.description }) }</td>
                <td>
                    <a href="${pageContext.request.contextPath }/EditCustomerServlet?id=${c.id }">修改</a>
                    <a href="javascript:void(0)" onclick="del('${c.id}')">删除</a>   <!-- 注意：有时候javascript函数获取不到传递过来的参数，可将传递过来的参数加上单引号('') -->
                </td>
            </tr>
        </c:forEach>
    </table>
   	 <span>${message }</span>
    <br/>
</body>
<script type="text/javascript">
	function del(id) {
  	  if (window.confirm("您确定删除吗？？？")) {
        location.href = '${pageContext.request.contextPath }/DelCustomerServlet?id='+id;
    	}
   	}
    function gotopage(currentpage) {
      if ( currentpage < 1 || currentpage != parseInt(currentpage) || currentpage > ${pagebean.totalpage } ) {
            alert("请输入有效值！！！");
            document.getElementById("pagenum").value='';
          } else {
             var pagesize = document.getElementById("pagesize").value;
             window.location.href = '${pageContext.request.contextPath }/ListCustomerServlet?currentpage='+currentpage+'&pagesize='+pagesize;
            }
        }

     function changesize(pagesize, oldvalue) {
        if ( pagesize < 0 || pagesize != parseInt(pagesize) ) {
                alert("请输入合法值！！！");
                document.getElementById("pagesize").value = oldvalue;
         } else {
             window.location.href = '${pageContext.request.contextPath }/ListCustomerServlet?pagesize='+pagesize; // 当前页currentpage就不传过去了，也即一改变页面大小，由于没带当前页过去，所以就从第1页开始显示
            }
        } 
</script>

 	共[${pagebean.totalrecord }]条记录，
    <%-- 每页显示<input type="text" id="pagesize" value="${pagebean.pagesize }" onchange="gotopage(${pagebean.currentpage })" style="width:30px" maxlength="2">条， --%>
    每页显示<input type="text" id="pagesize" value="${pagebean.pagesize }" onchange="changesize(this.value,${pagebean.pagesize })" style="width:30px" maxlength="2">条，
    共[${pagebean.totalpage }]页，
    当前第[${pagebean.currentpage }]页
    &nbsp;&nbsp;&nbsp;&nbsp;
    <!--  
    <a href="${pageContext.request.contextPath }/ListCustomerServlet?currentpage=${pagebean.previouspage }">上一页</a>
    <c:forEach var="pagenum" items="${pagebean.pagebar }">
        <a href="${pageContext.request.contextPath }/ListCustomerServlet?currentpage=${pagenum }">${pagenum }</a>
    </c:forEach>
    <a href="${pageContext.request.contextPath }/ListCustomerServlet?currentpage=${pagebean.nextpage }">下一页</a>
    -->

    <c:if test="${pagebean.currentpage!=1 }">
        <a href="javascript:void(0)" onclick="gotopage(${pagebean.previouspage })">上一页</a>
    </c:if>
    <c:forEach var="pagenum" items="${pagebean.pagebar }">
        <c:if test="${pagenum==pagebean.currentpage }">
            <font color="red">${pagenum }</font>
        </c:if>
        <c:if test="${pagenum!=pagebean.currentpage }">
            <a href="javascript:void(0)" onclick="gotopage(${pagenum })">${pagenum }</a>
        </c:if>
    </c:forEach>
    <c:if test="${pagebean.currentpage!=pagebean.totalpage }">
        <a href="javascript:void(0)" onclick="gotopage(${pagebean.nextpage })">下一页</a>
    </c:if>

    <input type="text" id="pagenum" style="width: 30px">
    <input type="button" value="GO" onclick="gotopage(document.getElementById('pagenum').value)">
</html>
