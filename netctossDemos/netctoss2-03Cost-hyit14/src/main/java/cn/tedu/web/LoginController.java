package cn.tedu.web;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
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
	 * @throws PasswordException 
	 * @throws AdminCodeException 
	 */
	@RequestMapping("/checkLogin.do")
	public String checkLogin(
			String adminCode, String password,
			HttpSession session,Model model) 
				throws AdminCodeException, PasswordException {
		
			Admin admin = loginService.validate(
							adminCode, password);

			//验证通过，将登录信息存入session
			session.setAttribute("admin", admin);
			
			//重定向到导航首页toIndex.do
			return "redirect:toIndex.do";
	}
	
	//当出现异常时，Spring会帮着将
	//	正在处理的请求和异常对象传入
	//通过请求对象，可以获取其中的账号和密码
	@ExceptionHandler
	public String doException(
			Exception e,
			HttpServletRequest request) throws Exception {
		if(e instanceof AdminCodeException
				|| e instanceof PasswordException) {
			//如果是业务异常，手工处理
			request.setAttribute(
					"error", e.getMessage());
			
			//取出请求中参数，重新绑定请求中的属性
			String code = request.getParameter("adminCode");
			String pwd = request.getParameter("password");
			request.setAttribute("adminCode", code);
			request.setAttribute("password", pwd);
			
			return "main/login";
		} else {
			//如果是其他异常，直接抛给SpringWebMVC框架处理
			throw e;
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
/*
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
 */







