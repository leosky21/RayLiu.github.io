package com.jf.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jf.model.Admin;
import com.jf.myspringmvc.Controller;
import com.jf.myspringmvc.RequestMapping;
import com.jf.service.AdminServiceI;
import com.jf.service.impl.AdminServiceImpl;
import com.jf.utils.Md5Utils;

@Controller
public class AdminInfoAction {
	private AdminServiceI adService = new AdminServiceImpl();
	
	/**
	 * 更新管理员个人资料
	 * 只能更新昵称和手机号
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	@RequestMapping("/admin/adminInfoAction_adminInfo")
	public void updateAdminInfo(HttpServletRequest request,HttpServletResponse response) throws IOException{
		//从session中拿到管理员对象
		Admin admin = (Admin) request.getSession().getAttribute("admin");
		String name = request.getParameter("name");
		String phone = request.getParameter("phone");
		//拿到当前用户的id
		Integer id = admin.getId();
		int status = -1;//一般在开发中 -1 大多代表失败
		//判断是否更新成功
		int num = adService.updateAdminInfo(id,name,phone);
		if(num > 0){
			admin.setName(name);
			admin.setPhone(phone);
			//重新设置session
			request.getSession().setAttribute("admin", admin);
			status = 1;
		}
		//回写到浏览器
		response.getWriter().print(status);
	}
	
	@RequestMapping("/admin/adminInfoAction_adminPwd")
	public void AdminUpdatePwd(HttpServletRequest request,HttpServletResponse response){
		String oldPwd = request.getParameter("oldPwd");
		String newPwd = request.getParameter("newPwd");
		String confirmPwd = request.getParameter("confirmPwd");
		oldPwd = Md5Utils.md5(oldPwd);
		newPwd = Md5Utils.md5(newPwd);
		confirmPwd = Md5Utils.md5(confirmPwd);
		Admin admin = (Admin) request.getSession().getAttribute("admin");
		int status = -2;
		if(!oldPwd.equals(admin.getPassword())){
			status = -1;
		}else if(!newPwd.equals(confirmPwd)){
			status = 0;
		}else{
			Integer id = admin.getId();
			int num = adService.UpdatePwd(newPwd,id);
			if(num > 0){
				status = 1;
				admin.setPassword(newPwd);
				request.getSession().setAttribute("admin", admin);
			}
		}
		try {
			response.getWriter().print(status);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
