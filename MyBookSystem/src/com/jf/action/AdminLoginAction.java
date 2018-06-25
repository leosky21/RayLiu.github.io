package com.jf.action;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jf.model.Admin;
import com.jf.myspringmvc.Controller;
import com.jf.myspringmvc.RequestMapping;
import com.jf.service.AdminServiceI;
import com.jf.service.impl.AdminServiceImpl;
import com.jf.utils.Md5Utils;

@Controller
public class AdminLoginAction {
	
	private AdminServiceI adservice = new AdminServiceImpl();
	
	@RequestMapping("/AdminLoginAction_Login")
	public String adminLogin(HttpServletRequest request, HttpServletResponse response){
		String username = request.getParameter("username");
		String pwd = request.getParameter("pwd");
		pwd = Md5Utils.md5(pwd);
		Admin admin = new Admin();
		int status = 1;
		try {
			admin = adservice.getAdminByNameAndPwd(username,pwd);
			if(admin == null){
				status = -1;
			}else if(!pwd.equals(admin.getPassword())){
				status = -2;
			}else{
				request.getSession().setAttribute("admin", admin);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			response.getWriter().print(status);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping("/adminLoginAction_logout")
	public void logOut(HttpServletRequest request, HttpServletResponse response) throws IOException{
		request.getSession().invalidate();
		response.sendRedirect("adminLogin.jsp");
	}
}
