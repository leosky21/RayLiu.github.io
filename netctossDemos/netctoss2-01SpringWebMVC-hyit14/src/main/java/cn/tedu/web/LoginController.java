package cn.tedu.web;

import java.io.Serializable;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.tedu.entity.Admin;
import cn.tedu.service.AdminCodeException;
import cn.tedu.service.LoginService;
import cn.tedu.service.PasswordException;

@Controller
@RequestMapping("/login")
public class LoginController implements Serializable{

	/**
	 * 打开登录页面
	 */
	@RequestMapping("/toLogin.do")
	public String toLogin() {
		return "main/login";
	}
	
	/**
	 * 打开功能导航页 index.jsp
	 */
	@RequestMapping("/toIndex.do")
	public String toIndex() {
		return "main/index";
	}
	
	
	/**
	 * 登录的检查
	 * @param adminCode
	 * @param password
	 * 		这两个参数的值，是Spring从表单传入过来的
	 * @param session
	 * 		Spring帮着创建，
	 * 		将用户登录的状态，保存到session中
	 * @param model
	 * 		登录失败时，将失败的信息，存入Model
	 */
	@RequestMapping("/checkLogin.do")
	public String checkLogin(
			String adminCode, 
			String password,
			HttpSession session,
			Model model) {
		
		try {
			Admin admin = loginService.validate(
							adminCode, password);
			
			//验证通过，将登录信息存入session
			session.setAttribute("admin", admin);
			
			//重定向到导航首页toIndex.do
			return "redirect:toIndex.do";
			
		} catch (AdminCodeException e) {
			//验证失败，添加错误信息到model
			//再请求转发到/WEB-INF/main/login.jsp
			String message = e.getMessage();
			model.addAttribute("error", message);
			
			model.addAttribute("adminCode", adminCode);
			model.addAttribute("password", password);
			
			return "main/login";
		} catch (PasswordException e) {
			String message = e.getMessage();
			model.addAttribute("error", message);
			
			model.addAttribute("adminCode", adminCode);
			model.addAttribute("password", password);
			
			return "main/login";
		}
	}
	
	@Autowired
	private LoginService loginService;

	public LoginService getLoginService() {
		return loginService;
	}

	public void setLoginService(LoginService loginService) {
		this.loginService = loginService;
	}
	
}








