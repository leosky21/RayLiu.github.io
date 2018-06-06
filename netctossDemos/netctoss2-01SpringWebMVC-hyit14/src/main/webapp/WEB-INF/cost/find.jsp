<%@ page contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
        <title>达内－NetCTOSS</title>
        <!-- 
        当前路径：/netctoss/findCost.do
        样式路径：/netctoss/styles/xxx.css
         -->
        <link type="text/css" rel="stylesheet" media="all" href="styles/global.css" />
        <link type="text/css" rel="stylesheet" media="all" href="styles/global_color.css" />
        <script language="javascript" type="text/javascript">
            //排序按钮的点击事件
            function sort(btnObj) {
                if (btnObj.className == "sort_desc")
                    btnObj.className = "sort_asc";
                else
                    btnObj.className = "sort_desc";
            }

            //启用
            function startFee() {
                var r = window.confirm("确定要启用此资费吗？资费启用后将不能修改和删除。");
                document.getElementById("operate_result_info").style.display = "block";
            }
            //删除
            function deleteFee() {
                var r = window.confirm("确定要删除此资费吗？");
                document.getElementById("operate_result_info").style.display = "block";
            }
        </script>        
    </head>
    <body>
        <!--Logo区域开始-->
        <div id="header">
        		<!-- 当服务器翻译find.jsp时，会连带导入并翻译
        			所引入的logo.jsp页面；
        			logo.jsp页面，在当前页面的上一层中，相对路径；
        			分析该路径时，所站角度是：在服务器中！ -->
        		<c:import url="../logo.jsp"></c:import>     
        </div>
        <!--Logo区域结束-->
        <!--导航区域开始-->
        <div id="navi">                        
			<c:import url="../menu.jsp"></c:import>
        </div>
        <!--导航区域结束-->
        <!--主要区域开始-->
        <div id="main">
            <form action="" method="">
                <!--排序-->
                <div class="search_add">
                    <div>
                        <!--<input type="button" value="月租" class="sort_asc" onclick="sort(this);" />-->
                        <input type="button" value="基费" class="sort_asc" onclick="sort(this);" />
                        <input type="button" value="时长" class="sort_asc" onclick="sort(this);" />
                    </div>
                    <input type="button" value="增加" class="btn_add" onclick="location.href='toAddCost.do';" />
                </div> 
                <!--启用操作的操作提示-->
                <div id="operate_result_info" class="operate_success">
                    <img src="../images/close.png" onclick="this.parentNode.style.display='none';" />
                    删除成功！
                </div>    
                <!--数据区域：用表格展示数据-->     
                <div id="data">            
                    <table id="datalist">
                        <tr>
                            <th>资费ID</th>
                            <th class="width100">资费名称</th>
                            <th>基本时长</th>
                            <th>基本费用</th>
                            <th>单位费用</th>
                            <th>创建时间</th>
                            <th>开通时间</th>
                            <th class="width50">状态</th>
                            <th class="width200"></th>
                        </tr>
                        <c:forEach items="${costs}" var="c">
                        <tr>
                            <td>${c.costId}</td>
                            <td><a href="fee_detail.html">${c.name }</a></td>
                            <td>${c.baseDuration}</td>
                            <td>${c.baseCost}</td>
                            <td>${c.unitCost}</td>
                            <td>${c.creatime}</td>
                            <td>${c.startime }</td>
                            <td>
                            		<c:if test="${c.status==0}">开通</c:if>
                            		<c:if test="${c.status==1}">暂停</c:if>
                            </td>
                            <td>                                
                                <input type="button" value="启用" class="btn_start" onclick="startFee();" />
                                <input type="button" value="修改" class="btn_modify" onclick="location.href='toUpdateCost.do?id=${c.costId}';" />
                                <input type="button" value="删除" class="btn_delete" onclick="deleteFee();" />
                            </td>
                        </tr>
                        </c:forEach>                      
                    </table>
                    <p>业务说明：<br />
                    1、创建资费时，状态为暂停，记载创建时间；<br />
                    2、暂停状态下，可修改，可删除；<br />
                    3、开通后，记载开通时间，且开通后不能修改、不能再停用、也不能删除；<br />
                    4、业务账号修改资费时，在下月底统一触发，修改其关联的资费ID（此触发动作由程序处理）
                    </p>
                </div>
                <!--分页-->
                <div id="pages">
                		<!-- 当前如果是第1页，则点上一页无效 -->
                		<c:if test="${page==1}">
                			<a href="">上一页</a>
                		</c:if>
                		<c:if test="${page!=1}">
	        	        		<a href="findCost.do?page=${page-1}">上一页</a>
                		</c:if>
                    
                    <!-- 显示页码 -->
                    <c:forEach begin="1" end="${total}" var="i">
                    		<!-- 如果循环到了当前页，高亮显示链接 -->
                    		<c:if test="${i==page}">
		                    <a href="findCost.do?page=${i}" class="current_page">${i}</a>
                    		</c:if>
	                    
	                    <!-- 如果不是当前页，去掉高亮，正常显示 -->
	                    <c:if test="${i!=page}">
		                    <a href="findCost.do?page=${i}">${i}</a>	                    
	                    </c:if>
                    </c:forEach>
                    
                    <!-- 当前如果是最后一页，则点下一页无效 -->
                    <c:if test="${page==total}">
                    		<a href="">下一页</a>
                    </c:if>
                    <c:if test="${page!=total}">
	                    <a href="findCost.do?page=${page+1}">下一页</a>
                    </c:if>
                </div>
            </form>
        </div>
        <!--主要区域结束-->
        <div id="footer">
            <p>[源自北美的技术，最优秀的师资，最真实的企业环境，最适用的实战项目]</p>
            <p>版权所有(C)加拿大达内IT培训集团公司 </p>
        </div>
    </body>
</html>
