<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>显示学生分页数据</title>

</head>
<body>
    <c:forEach var="s" items="${pagebean.list }">
        ${s.id }	${s.name }<br/>
    </c:forEach>
    <script type="text/javascript">

        function gotopage(currentpage) {
            if ( currentpage < 1 || currentpage != parseInt(currentpage) || currentpage > ${pagebean.totalpage } ) {
                alert("请输入有效值！！！");
                document.getElementById("pagenum").value='';
            } else {
                var pagesize = document.getElementById("pagesize").value;
                window.location.href = '${pageContext.request.contextPath }/ListStudentServlet?currentpage='+currentpage+'&pagesize='+pagesize;
            }
        }

        function changesize(pagesize, oldvalue) {
            if ( pagesize < 0 || pagesize != parseInt(pagesize) ) {
                alert("请输入合法值！！！");
                document.getElementById("pagesize").value = oldvalue;
            } else {
                window.location.href = '${pageContext.request.contextPath }/ListStudentServlet?pagesize='+pagesize; // 当前页currentpage就不传过去了，也即一改变页面大小，由于没带当前页过去，所以就从第1页开始显示
            }
        }
    </script>
    共[${pagebean.totalrecord }]条记录，
    每页显示<input type="text" id="pagesize" value="${pagebean.pagesize }" onchange="changesize(this.value, ${pagebean.pagesize })" style="width:30px" maxlength="2">条，
    共[${pagebean.totalpage }]页，
    当前第[${pagebean.currentpage }]页
    &nbsp;&nbsp;&nbsp;&nbsp;
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
</body>


</html>