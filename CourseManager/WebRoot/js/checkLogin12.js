function checkForm(){
	var forms = document.forms;
	var sUser = forms[0].user.userName.value;
	var sID = document.ff.user.userID.value;
	var sPass = document.ff.user.Pass.value;
	
	if(sUser==""){
		alert("用户名不能为空");
		return false;
	}
	if(sID=""||sPass.length<4||sPass.length>15){
		alert("ID长度不能为空，长度应该位4～15位");
		return false;
	}
	if(sPass=""||sPass.length<4||sPass.length>15){
		alert("密码长度不能为空，长度应该位4～15位，两次输入密码应该一致");
		return false;
	}
	return ;
}
