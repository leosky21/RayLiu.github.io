<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
			//iframe中的datagrid对象
			var dg = parent.$("iframe[title='药品管理']").get(0).contentWindow.$("#dg"); 
			
			// 完成数据的回显，更新时，用户肯定先选择了要更新的那一行，首先我们得拿到那一行
			var rows = dg.datagrid("getSelections");
			//将拿到的那一行对应的数据字段加载到表单里，实现回显
			$("#ff").form('load',{
				licensenumber:rows[0].licensenumber,
				periodvalidity:rows[0].periodvalidity,
				medicinename:rows[0].medicinename,
				medcharacter:rows[0].medcharacter,
				dose:rows[0].dose,
				dosage:rows[0].dosage,
				dosagefromdoc:rows[0].dosagefromdoc,
				untowardeffect:rows[0].untowardeffect,
				druginteraction:rows[0].druginteraction,
				manufacturer:rows[0].manufacturer,
				storageconditions:rows[0].storageconditions,
				activeingredient:rows[0].activeingredient,
				contraindication:rows[0].contraindication,
				indication:rows[0].indication,
			});
			//回显完了数据后，设置一下验证功能
			$("input[name=licensenumber]").validatebox({
				required:true,
				missingMessage:'请输入药准字号'
			}); 
			$("input[name=periodvalidity]").numberbox({
				required:true,
				missingMessage:'请输入有效期',
				min:1,
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
				suffix:'个月'
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
			$("#btn").click(function(){
				//开启验证
				$("#ff").form("enableValidation");
				//如果验证成功，则提交数据
				if($("#ff").form("validate")) {
					//调用submit方法提交数据
					$("#ff").form('submit', {
						url: Med+'/medicine/update.action', //提交时将请求传给productAction的update方法执行
						success: function(){
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

  	<form title="更新药品信息" id="ff" method="post" enctype="application/x-www-form-urlencoded">
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
			<textarea name="dosagefromdoc" cols="40" rows="2"></textarea>	
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
	    	<a id="btn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-edit'">更新</a>  
	    	<!-- <input type="hidden" name="id" /> -->
	    </div>  
	</form>  	
  </body>
</html>