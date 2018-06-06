<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<c:set value="${pageContext.request.contextPath }"  var="shop" />
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<%@ include file="/public/head.jspf" %>
	<style type="text/css">
		#menu {
			width:60px;
			/*border:1px solid red;*/
		}
		#menu ul {
			list-style: none;
			padding: 0px;
			margin: 0px;
		}
		#menu ul li {
			border-bottom: 1px solid #fff;
			
		}
		#menu ul li a {
			/*先将a标签转换为块级元素，才能设置宽和内间距*/
			display: block;
			background-color: #00a6ac;
			color: #fff;
			padding: 5px;
			text-decoration: none;
		}
		#menu ul li a:hover {
			background-color: #008792;
		}
		
	</style>
	
	<script type="text/javascript">
		$(function(){
			$("a[title]").click(function(){
				var text = $(this).text();
				var href = $(this).attr("title");
				//判断当前右边是否已有相应的tab
				if($("#tt").tabs("exists", text)) {
					$("#tt").tabs("select", text);
				} else {
					//如果没有则创建一个新的tab，否则切换到当前tag
					$("#tt").tabs("add",{
						title:text,
						closable:true,
						content:'<iframe title=' + text + ' src=' + href + ' frameborder="0" width="100%" height="100%" />'
					});
				}
				
			});
		});
	</script>
</head>
	<body class="easyui-layout">
	    <div data-options="region:'north',title:'欢迎来到医药管家后台管理',split:true" style="height:100px;">
	    </div>   
	    <div data-options="region:'west',title:'系统操作',split:true" style="width:200px;">
	    	<!-- 此处显示的是系统菜单 -->
	    	<div id="menu" class="easyui-accordion" data-options="fit:true">   
    			<div title="基本操作">   
        			<ul>
						<li><a href="#" title="${Med }/category/query.action">药箱管理</a>
						<li><a href="#" title="${Med }/plan/query.action">计划管理</a>
						<li><a href="#" title="${Med }/user/query.action">用户管理</a> 
						<li><a href="#" title="${Med }/medicine/query.action">药品管理</a> 
					</ul>
    			</div>   
    			<div title="数据分析">
    				<ul>
						<li><a href="#" title="${Med }/send/main/analysis/region">地域分析</a>
						<li><a href="#" title="${Med }/send/main/analysis/medicine">药品分析</a>
					</ul>
    			</div>   
			</div>   
	    </div>   
	    <div data-options="region:'center',title:'后台操作页面'" style="padding:1px;background:#eee;">
	    	<div id="tt" class="easyui-tabs" data-options="fit:true">   
    			<div title="系统缺省页面" style="padding:10px;">
    				入门介绍信息入门介绍信息入门介绍信息入门介绍信息入门介绍信息入门介绍信息入门介绍信息入门介绍信息入门介绍信息入门介绍信息入门介绍信息入门介绍信息入门介绍信息入门介绍信息
    			</div>   	
    		</div>      		    	
	    </div> 
	    <div id="win" data-options="collapsible:false,minimizable:false,maximizable:false,modal:true"></div>  
	</body>  

</html>
