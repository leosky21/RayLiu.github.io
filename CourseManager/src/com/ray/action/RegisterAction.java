package com.ray.action;



import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ModelDriven;
import com.ray.enitity.SysUser;
import com.ray.service.SysUserService;
import com.ray.service.SysUserServiceImp;

public class RegisterAction implements ModelDriven<SysUser> {
	
	 SysUserService rs = new SysUserServiceImp();
	 SysUser user;
 
	 public void doRegister() throws Exception{
			//TODO：！！读取用户输入值，存入数据库
//			registered
			if( (rs.findUserByName( user.getUserName(), user.getPass())) 
					||  rs.findUserById( user.getUserID(), user.getPass())){
				ServletActionContext.getRequest().getRequestDispatcher("/login.jsp").forward(
						ServletActionContext.getRequest(), ServletActionContext.getResponse());;
			}else{
				if(!rs.saveUser(user)){
//					success
					ServletActionContext.getRequest().getRequestDispatcher("/login.jsp").forward(
							ServletActionContext.getRequest(), ServletActionContext.getResponse());;
					}
				else{
//					failed
					ServletActionContext.getRequest().setAttribute("result", "falied，please try again!");
					ServletActionContext.getRequest().getRequestDispatcher("/register.jsp").forward(
							ServletActionContext.getRequest(), ServletActionContext.getResponse());;
//					return "reRegister";
					}
				}
//			return ;
		}
 
	public SysUser getRUser() {
		return user;
	}

	public void setrUser(SysUser user) {
		this.user = user;
	}

	@Override
	public SysUser getModel() {
		if(null == user){
			return user = new SysUser();
		}
		return user;
	}
	
}
