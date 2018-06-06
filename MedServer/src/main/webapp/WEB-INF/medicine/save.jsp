<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
	<%@ include file="/public/head.jspf" %>
	<style type="text/css">
		form div {
			margin:10px;
		}
	</style>
	<script type="text/javascript">
		var Med = $('#MedValue').val();
	
		$(function(){
			//自定义验证方法向validatebox.defaults.rules中注册新函数
			$.extend($.fn.validatebox.defaults.rules,{
				//函数的名称：{函数的实现体(又是一个json对象，里面包括函数的实现，和错误消息的设置)} 
				format:{
					//函数实现，如果返回为false，则验证失败
					validator: function(value,param){
						//获取当前文件的后缀名
						var ext = value.substring(value.lastIndexOf('.') + 1);
						//获取支持的文件后缀名，然后比较即可
						var arr = param[0].split(",");
						for(var i = 0; i < arr.length; i++) {
							if(ext == arr[i])
								return true;
						}
						return false;
					},
					//错误消息
					message: '文件后缀必须为:{0}'
				}
			});
				
			$("input[name=licensenumber]").validatebox({
				required:true,
				missingMessage:'请输入药准字号'
			});
			$("input[name=periodvalidity]").validatebox({
				required:true,
				missingMessage:'请输入有效期',
				suffix:'个月'
			});
			$("input[name=medicinename]").validatebox({
				required:true,
				missingMessage:'药通用名',
			});
			$("textarea[name=medcharacter]").validatebox({
				required:true,
				missingMessage:'请输入性状'
			});
			$("input[name=dose]").validatebox({
				required:true,
				missingMessage:'请输入规格/剂量',
			});
			$("textarea[name=dosage]").validatebox({
				required:true,
				missingMessage:'用法用量',
			});		
			$("textarea[name=dosagefromdoc]").validatebox({
				required:true,
				missingMessage:'医生建议用法用量'
			});
			$("input[name=untowardeffect]").validatebox({
				required:true,
				missingMessage:'不良反应',
			});
			$("input[name=druginteraction]").validatebox({
				required:true,
				missingMessage:'药物相互作用',
			});
			$("input[name=manufacturer]").validatebox({
				required:true,
				missingMessage:'请输入生产厂家'
			});
			$("input[name=storageconditions]").validatebox({
				required:true,
				missingMessage:'存储条件',
			});
			
			$("textarea[name=activeingredient]").validatebox({
				required:true,
				missingMessage:'请输入有效成分'
			});
			$("textarea[name=contraindication]").validatebox({
				required:true,
				missingMessage:'禁忌'
			});
			$("textarea[name=indication]").validatebox({
				required:true,
				missingMessage:'适应症/功能主治'
			});
			//窗体弹出默认时禁用验证
			$("#ff").form("disableValidation");
			
			//注册button的事件
			$("#submit").click(function(){
				//开启验证
				$("#ff").form("enableValidation");
				//如果验证成功，则提交数据
				if($("#ff").form("validate")) {
					//调用submit方法提交数据
					$("#ff").form('submit', {
						url: Med+'/medicine/save.action',
						success: function(){
							//如果成功了，关闭当前窗口
							parent.$("#win").window("close");
							parent.$("iframe[title='药品管理']").get(0).contentWindow.$("#dg").datagrid("reload");
						}
					});
				}
			});
			
			//注册button的事件
			$("#reset").click(function(){
				$("#ff").form("disableValidation");//重置不需要表单验证
				//重置当前表单数据
				$("#ff").form("reset");
			});
		});
	</script>
  </head>
  
  <body>
	  <form title="添加药品" id="ff" method="post" enctype="application/x-www-form-urlencoded"><!-- 不需要传文件 -->
		<div>
			<label>药准字号:</label> <input type="text" name="licensenumber" />
		</div>

		<div>
			<label>药通用名:</label> <input type="text" name="medicinename" />
		</div>
		<div>
			<label>有效成分:</label> 
			<textarea name="activeingredient" cols="40" rows="4"></textarea>
		</div>

		<div>
			<label>性状：</label> 
			<textarea name="medcharacter" cols="40" rows="2"></textarea>
		</div>
		
		<div>
			<label>规格/剂量:</label> 
			<input type="text" name="dose"/>  
		</div>
		<div>
			<label>用法用量:</label>
			<textarea name="dosage" cols="40" rows="2"></textarea>	
		</div>
		 
		<div>
			<label>禁忌:</label>
			<textarea name="contraindication" cols="40" rows="2"></textarea>
		</div>
		<div>
			<label>适应症/功能主治:</label>
			<textarea name="indication" cols="40" rows="2"></textarea>
		</div>
		
		<div>
			<label>医生建议用法用量：</label> 
			<textarea name="dosagefromDoc" cols="40" rows="2"></textarea>
		</div>
		
		<div>
			<label>不良反应:</label> 
			<input type="text" name="untowardeffect"/>  
		</div>
		<div>
			<label>药物相互作用:</label>
			<input type="text" name="druginteraction" />		
		</div>
		 
		<div>
			<label>有效期：</label> 
			<input type="text" name="periodvalidity"/>
		</div>
		
		<div>
			<label>生产厂家:</label> 
			<input type="text" name="manufacturer"/>  
		</div>
		<div>
			<label>存储条件:</label>
			<input type="text" name="storageconditions" />		
		</div>
		<div>
			<a id="submit" href="#" class="easyui-linkbutton">添 加</a> 
			<a id="reset" href="#" class="easyui-linkbutton">重 置</a>
		</div>
	  </form>	
  </body>
</html>