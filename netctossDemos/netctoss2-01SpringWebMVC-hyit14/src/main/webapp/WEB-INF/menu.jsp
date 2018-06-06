<%@ page contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <!-- 
		当前链接中模块的路径，使用绝对路径！
		因为在后期模块的访问路径，有可能出现多个层次结果，
			如：/netctoss/account/find.do
			当前这个account/find.do路径是两层，
		若用相对路径：findCost.do，会生成以下错误的路径：
			/netctoss/account/findCost.do
			
		所以，对于复杂项目中的通用模块，建议使用绝对路径！
    -->
<ul id="menu">
    <li><a href="../index.html" class="index_off"></a></li>
    <li><a href="../role/role_list.html" class="role_off"></a></li>
    <li><a href="../admin/admin_list.html" class="admin_off"></a></li>
    <li><a href="${pageContext.request.contextPath}/findCost.do" class="fee_on"></a></li>
    <li><a href="../account/account_list.html" class="account_off"></a></li>
    <li><a href="../service/service_list.html" class="service_off"></a></li>
    <li><a href="../bill/bill_list.html" class="bill_off"></a></li>
    <li><a href="../report/report_list.html" class="report_off"></a></li>
    <li><a href="../user/user_info.html" class="information_off"></a></li>
    <li><a href="../user/user_modi_pwd.html" class="password_off"></a></li>
</ul>            
