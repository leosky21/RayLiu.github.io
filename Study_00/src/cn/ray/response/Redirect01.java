package cn.ray.response;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Redirect01 extends HttpServlet {

	/**
	 * 
	 * 
	 * 	一个web资源收到客户端请求后，
	 * 		通知客户端去访问另外一个web资源，这称之为请求重定向。 
	 * 应用场景
	 * 		:
	 * 		- 用户登录，用户首先访问登录页面，登录成功后，就会跳转到某个页面，这个过程就是一个请求重定向的过程。
			- 显示购物车，通常会用到重定向技术。
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setStatus(302);
		response.setHeader("location", "index.jsp");
		
		//相当于
	//response.sendRedirect("/day06/index.jsp");
		
	}

}
