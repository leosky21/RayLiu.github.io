<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
	<%@ include file="/public/head.jspf" %>
	<style type="text/css">
		form div {
			margin:5px;
		}
	</style>
	<script type="text/javascript">
		var Med = $('#MedValue').val();
		
		$(function(){
			$("input[name=nick]").validatebox({
				required:true,
				missingMessage:'请输入用户登录名'
			});	
			$("input[name=username]").validatebox({
				required:true,
				missingMessage:'请输入用户姓名',
				validType:'length[2,4]'
			});
			$("input[name=手机号]").validatebox({
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
				missingMessage:'请输入用户体重',
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
			$("#btn").click(function(){
				//开启验证
				$("#ff").form("enableValidation");
				//如果验证成功，则提交数据
				if($("#ff").form("validate")) {
					//调用submit方法提交数据
					$("#ff").form('submit', {
						url: Med+'/user/register.action',
						success: function(){
							//如果成功了，关闭当前窗口
							parent.$("#win").window("close");
						
							parent.$("iframe[title='用户管理']").get(0).contentWindow.$("#dg").datagrid("reload");
							//alert(dg);
						}
					});
				}
			});
		});
	</script>
  </head>
  
  <body>
  	<form id="ff" method="post" enctype="application/x-www-form-urlencoded">   
	    <div>   
	        <label for="nick">登录名称(昵称):</label> <input type="text" name="nick" />   
	    </div>   
	    <div>   
	        <label for="username">用户姓名:</label> <input type="text" name="username" />   
	    </div> 
	    <div>   
	        <label for="sex">性别:</label>
	        男:<input type="radio" name="sex" checked="checked"value="true" />
			女:<input type="radio" name="sex" value="false" /> 
	    </div> 
	    <div>   
	        <label for="phone">手机号:</label>
	        <input type="text" name="phone" />   
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
	    	<a id="btn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add'">添加</a>  
	    </div>  
	</form>  	
  </body>
</html>