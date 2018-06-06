<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"
	isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8" />
<title>智能探识感知大数据平台管理系统</title>
<meta name="author" content="DeathGhost" />
<link rel="stylesheet" type="text/css" href="css/style.css">
<!--[if lt IE 9]>
<script src="js/html5.js"></script>
<![endif]-->
<script src="js/jquery.js"></script>
<script src="js/jquery.mCustomScrollbar.concat.min.js"></script>
<script>

	(function($) {
		$(window).load(function() {

			$("a[rel='load-content']").click(function(e) {
				e.preventDefault();
				var url = $(this).attr("href");
				$.get(url, function(data) {
					$(".content .mCSB_container").append(data); //load new content inside .mCSB_container
					//scroll-to appended content 
					$(".content").mCustomScrollbar("scrollTo", "h2:last");
				});
			});

			$(".content").delegate("a[href='top']", "click", function(e) {
				e.preventDefault();
				$(".content").mCustomScrollbar("scrollTo", $(this).attr("href"));
			});

		});
	})(jQuery);
</script>
</head>
<body>
	<header>
	<h1>
		<img src="images/admin_logo.png" />
	</h1>
	<ul class="rt_nav">
		<li><a href="index.jsp" target="_blank" class="website_icon">系统首页</a></li>
		<li><a href="consult.html" class="clear_icon">查询用户</a></li>
		<li><a href="addUser.jsp" class="admin_icon">添加用户</a></li>
		<li><a href="/LogoutServlet" class="quit_icon">安全退出</a></li>
	</ul>
	</header>

<section class="rt_wrap content mCustomScrollbar">
	<div class="rt_content">
		<div class="page_title">
			<h2 class="fl">用户信息</h2>
			<a href="addUser.jsp" class="fr top_rt_btn add_icon">添加用户</a>
		</div>
		<table class="table">
			<tr>
				<th>账号</th>
				<th>公司</th>
				<th>联系方式</th>
				<th>密码</th>
				<th>操作</th>
			</tr>

			<c:forEach var="c" items="${requestScope.pagebean.list }"
				varStatus="status">
				<tr>
					<!-- class="${status.count%2==0?'even':'odd' }" -->
					<td>${c.username }</td>
					
					<td>${c.company }</td>
					<td>${c.tel }</td>
					<td>${c.password }</td>
					<td class="center"><a
						href="${pageContext.request.contextPath }/EditUserServlet?id=${c.id }"
						title="编辑" class="link_icon">修改</a> <a href="javascript:void(0)"
						onclick="del('${c.id}')" title="删除" class="link_icon">删除</a></td>
				</tr>
			</c:forEach>
		</table>
	</div>
	<%--  <span>${message }</span> --%> <br />
</body>
<script type="text/javascript">
	function del(id) {
  	  if (window.confirm("您确定删除吗？？？")) {
        location.href = '${pageContext.request.contextPath }/DelUserServlet?id='+id;
    	}
   	}
    function gotopage(currentpage) {
      if ( currentpage < 1 || currentpage != parseInt(currentpage) || currentpage > ${pagebean.totalpage } ) {
            alert("请输入有效值！！！");
            document.getElementById("pagenum").value='';
          } else {
             var pagesize = document.getElementById("pagesize").value;
             window.location.href = '${pageContext.request.contextPath }/ListUserServlet?currentpage='+currentpage+'&pagesize='+pagesize;
            }
        }

     function changesize(pagesize, oldvalue) {
        if ( pagesize < 0 || pagesize != parseInt(pagesize) ) {
                alert("请输入合法值！！！");
                document.getElementById("pagesize").value = oldvalue;
         } else {
             window.location.href = '${pageContext.request.contextPath }/ListUserServlet?pagesize='+pagesize;
            }
        } 
</script>
<aside class="paging" style="text-align: center;">
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
	</aside>
</html>
