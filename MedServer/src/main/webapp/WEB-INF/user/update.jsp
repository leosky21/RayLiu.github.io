<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<%@ include file="/public/head.jspf"%>
<style type="text/css">
form div {
	margin: 5px;
}
</style>
<script type="text/javascript">
	var Med = $('#MedValue').val();

	$(function() {
		//iframe中的datagrid对象
		var dg = parent.$("iframe[title='用户管理']").get(0).contentWindow.$("#dg");

		// 完成数据的回显，更新时，用户肯定先选择了要更新的那一行，首先我们得拿到那一行
		var rows = dg.datagrid("getSelections");
		//将拿到的那一行对应的数据字段加载到表单里，实现回显
		$("#ff").form('load', {
			id : rows[0].id,
			nick : rows[0].nick,
			username : rows[0].username,
			phone : rows[0].phone,
			height : rows[0].height,
			weight : rows[0].weight,
			medicalhistory : rows[0].medicalhistory,
			drugallergyhistory : rows[0].drugallergyhistory,
		});
		$("input[name=nick]").validatebox({
				required:true,
				missingMessage:'请输入用户登录名'
			});	
			$("input[name=username]").validatebox({
				required:true,
				missingMessage:'请输入用户姓名',
				validType:'length[2,4]'
			});
			$("input[name=phone]").validatebox({
				required:true,
				missingMessage:'请输入手机号',
				validType:'length[11,11]'
			});
			
			$("input[name=height]").numberbox({
				required:true,
				missingMessage:'请输入身高',
				min:0,
				suffix:'cm'
			});	
			$("input[name=weight]").numberbox({
				required:true,
				missingMessage:'请输入体重',
				min:0,
				precision:2, //保留两位小数
				suffix :'kg'
			});
			$("textarea[name=medicalhistory]").validatebox({
				required:true,
				missingMessage:'请输入病史,若无则输入: 无'
			});$("textarea[name=drugallergyhistory]").validatebox({
				required:true,
				missingMessage:'请输入药物过敏史,若无则输入: 无'
			});
		//窗体弹出默认时禁用验证
		$("#ff").form("disableValidation");
		//注册button的事件
		$("#btn").click(function() {
			//开启验证
			$("#ff").form("enableValidation");
			//如果验证成功，则提交数据
			if ($("#ff").form("validate")) {
				//调用submit方法提交数据
				$("#ff").form('submit', {
					url : Med + '/user/update.action', 
					success : function() {
						//如果成功了，关闭当前窗口，并刷新页面
						parent.$("#win").window("close");
						dg.datagrid("reload");
					}
				});
			}
		});
	});
</script>
</head>

<body>
	<form id="ff" method="post">
		<div>
			<label for="nick">登录名称(昵称):</label> <input type="text" name="nick" />
		</div>
		<div>
			<label for="username">用户姓名:</label> <input type="text"
				name="username" />
		</div>
		<div>
			<label for="sex">性别:</label> 男:<input type="radio" name="sex"
				checked="checked" value="男" /> 女:<input type="radio" name="sex"
				value="女" />
		</div>
		<div>
			<label for="phone">手机号:</label> <input type="text" name="phone" />
		</div>
		<div>
			<label for="height">身高:</label> <input type="text" name="height" />
		</div>
		<div>
			<label for="weight">体重:</label> <input type="text" name="weight" />
		</div>
		<div>
			<label for="medicalhistory">病史:</label>
			<textarea name="medicalhistory" cols="50" rows="5"></textarea>
		</div>
		<div>
			<label for="drugallergyhistory">药物过敏史:</label>
			<textarea name="drugallergyhistory" cols="50" rows="5"></textarea>
		</div>
		<div>
			<a id="btn" href="#" class="easyui-linkbutton"
				data-options="iconCls:'icon-edit'">更新</a> 
				
			<input type="hidden" name="id" />
		</div>
	</form>
</body>
</html>
