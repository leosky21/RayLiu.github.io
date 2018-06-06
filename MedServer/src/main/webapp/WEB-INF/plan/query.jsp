<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<%@ include file="/public/head.jspf"%>
<meta content="refresh" http-equiv="1">
<style type="text/css">
body {
	margin: 1px;
}

.searchbox {
	margin: -3;
}
</style>
<script type="text/javascript">
	var Med = $('#MedValue').val();

	$(function() {
		$('#dg').datagrid({
			//url地址改为请求categoryAction
			url : Med + '/plan/queryMedicinePlan.action',
			method : "post",
			loadMsg : '您的计划正在加载中...莫急莫急...',
			queryParams : {
				queryString : '',
			}, //这里不需要传具体的name，因为我们要显示所有的
			//width:300,
			fitColumns : true, //水平自动展开，如果设置此属性，则不会有水平滚动条，演示冻结列时，该参数不要设置
			//显示斑马线
			striped : true,
			//当数据多的时候不换行
			nowrap : true,
			singleSelect : false, //如果为真，只允许单行显示，全显功能失效
			//设置分页
			pagination : true,
			//设置每页显示的记录数，默认是10个
			pageSize : 5,
			//设置可选的每页记录数，供用户选择，默认是10,20,30,40...
			pageList : [ 5, 10, 15, 20, 25, 30, 35 ],
			idField : 'licensenumber', //指定id为标识字段，在删除，更新的时候有用，如果配置此字段，在翻页时，换页不会影响选中的项

			//toolbar定义添加、删除、更新按钮以及搜索框
			toolbar : [ {
				iconCls : 'icon-add',
				text : '添加计划',
				handler : function() {
					parent.$("#win").window({
						title : "添加计划",
						width : 650,
						height : 600,
						content : '<iframe src="' + Med + '/send/main/medicine/save" frameborder="0" width="100%" height="100%"/>'
					});
				}
			}, '-', {
				iconCls : 'icon-edit',
				text : '更新计划',
				handler : function() {
					//判断是否有选中行记录，使用getSelections获取选中的所有行
					var rows = $("#dg").datagrid("getSelections");
					if (rows.length == 0) {
						//弹出提示信息
						$.messager.show({ //语法类似于java中的静态方法，直接对象调用
							title : '提示',
							msg : '至少要选择一条记录',
							timeout : 1500,
							showType : 'slide',
						});
					} else if (rows.length != 1) {
						//弹出提示信息
						$.messager.show({ //语法类似于java中的静态方法，直接对象调用
							title : '提示',
							msg : '每次只能更新一条记录',
							timeout : 1500,
							showType : 'slide',
						});
					} else {
						//1. 弹出更新的页面
						parent.$("#win").window({
							title : "更新计划",
							width : 650,
							height : 600,
							content : '<iframe src="' + Med + '/send/main/medicine/update" frameborder="0" width="100%" height="100%"/>'
						});

					}
				}
			}, '-', {
				iconCls : 'icon-remove',
				text : '删除计划',
				handler : function() {
					//判断是否有选中行记录，使用getSelections获取选中的所有行
					var rows = $("#dg").datagrid("getSelections");
					//返回被选中的行，如果没有任何行被选中，则返回空数组
					if (rows.length == 0) {
						//弹出提示信息
						$.messager.show({ //语法类似于java中的静态方法，直接对象调用
							title : '错误提示',
							msg : '至少要选择一条记录',
							timeout : 2000,
							showType : 'slide',
						});
					} else {
						//提示是否确认删除，如果确认则执行删除的逻辑
						$.messager.confirm('删除的确认对话框', '您确定要删除此项吗？', function(r) {
							if (r) {
								//1. 从获取的记录中获取相应的的id,拼接id的值，然后发送后台1,2,3,4
								var licensenumbers = "";
								for (var i = 0; i < rows.length; i++) {
									licensenumbers += rows[i].licensenumber + ",";
								}
								licensenumbers = licensenumbers.substr(0, licensenumbers.lastIndexOf(","));
								//2. 发送ajax请求
								$.post(Med + "/medicine/deleteByLicenseNumber.action", {
									licensenumbers : licensenumbers
								}, function(result) {
									if (result == "true") {
										//将刚刚选中的记录删除，要不然会影响后面更新的操作
										$("#dg").datagrid("uncheckAll");
										//刷新当前页，查询的时候我们用的是load，刷新第一页，reload是刷新当前页
										$("#dg").datagrid("reload"); //不带参数默认为上面的queryParams		
									} else {
										$.messager.show({
											title : '删除异常',
											msg : '删除失败，请检查操作',
											timeout : 1500,
											showType : 'slide',
										});
									}
								}, "text");
							}
						});
					}
				}
			}, '-', { //查询按钮不是LinkButton，它有语法，但是也支持解析HTML标签
				text : "<input id='ss' name='serach' />"
			} ],
			rowStyler : function(index, row) {
				console.info("index" + index + "," + row)
				if (index % 2 == 0) {
					return 'background-color:#fff;';
				} else {
					return 'background-color:#c4e1e1;';
				}

			},
			frozenColumns : [ [
				{
					field : 'checkbox',
					checkbox : true
				},
				{
					field : 'phone',
					title : '用户名',
					width : 50,
					hight : "auto"
				},

			] ],
			columns : [ [
				{
					field : 'license',
					title : '药准字号',
					width : 50
				},
				{
					field : 'times',
					title : '服药剩余次数',
					width : 50
				},
				{
					field : 'medicine_time',
					title : '服药时间',
					width : 100
				},
				{
					field : 'num',
					title : '每次剂量',
					width : 40
				}
			] ]
		});
		//把普通的文本框转化为查询搜索文本框
		$('#ss').searchbox({
			//触发查询事件
			searcher : function(value, name) { //value表示输入的值
				//alert(value + "," + name)
				//如果指定了参数，它将取代'queryParams'属性。通常可以通过传递一些参数执行一次查询，通过调用这个方法会向上面url指定的action去发送请求，从服务器加载新数据。
				$('#dg').datagrid('load', {
					queryString : value
				});
			},
			prompt : '例如：用户名,药准字号'
		});
	});
</script>
</head>

<body>
	<table id="dg"></table>

</body>
</html>