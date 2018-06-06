package cn.ray.controller;

import java.io.IOException;
import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.ray.domain.User;
import cn.ray.service.BusinessService;
import cn.ray.utils.MD5Util;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 传统方式的登录
		String username = request.getParameter("username");
		String password = request.getParameter("password");

		BusinessService service = new BusinessService();
		User user = service.login(username, password);
		if (user == null) {
			request.setAttribute("message", "用户名或密码错误！！！");
			request.getRequestDispatcher("/message.jsp").forward(request, response);
			return;
		}
		
		
		/**
		 * @TODO 自动登录
		 *  	1. 给客户机发送自动登录的cookie ,名字..就是autoCookie,值就是 usernmae::MD5(password)
		 *  	2. 设置有效期:
		 *  		用服务器的时间判断有效时间和失效时间
		 *  		失效时间(expirestime)
		 *  		MD5(username:expirestime:username)
		 *  	
		 */
		int expirestime = Integer.parseInt(request.getParameter("time"));
		
		//给客户及发送自动登录的cookie
		Cookie cookie = makeCookie(user,expirestime);
		response.addCookie(cookie);
		this.Login(request,response,user);
		
	}

	private static void Login(HttpServletRequest request,HttpServletResponse response,User user) throws IOException{
		
		request.getSession().setAttribute("user", user);
		request.getSession().setAttribute("message", "success!!!");
		response.sendRedirect(request.getContextPath()+"/message.jsp");
	}
	private Cookie makeCookie(User user, int expirestime) {
		long currenttime = System.currentTimeMillis();
		
		String cookieValue = user.getUsername()+":"+(currenttime+expirestime*1000)+":"
				+MD5Util.md5Login(user.getUsername(),user.getPassword(),(currenttime+expirestime*1000));
		Cookie cookie = new Cookie("autologin",cookieValue);
		cookie.setMaxAge(expirestime);
		cookie.setPath(this.getServletContext().getContextPath());
		return cookie;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		doGet(request, response);
	}

}
