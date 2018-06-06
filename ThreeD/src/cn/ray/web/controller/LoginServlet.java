package cn.ray.web.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.ray.beans.Customer;
import cn.ray.dao.BusinessServiceImpl;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	BusinessServiceImpl service = new BusinessServiceImpl();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String referer = request.getHeader("Referer");
		System.out.println("referer::"+referer);
		
		Customer user = service.login(username, password);
		System.out.println(username+"::"+password);
		
		if (user != null) {
			request.getSession().setAttribute("user", user);
			// 让用户登录成功后，跳转到首页
//			if("http://localhost:8080/ThreeD/".equals(referer))
				response.sendRedirect(request.getContextPath() + "/show_datas.jsp");
//			else
//				response.sendRedirect("http://localhost:8080/ThreeD/ListUserServlet");
//			request.getRequestDispatcher("/show_datas.jsp").forward(request, response);
			return;
		}
//		if("http://localhost:8080/ThreeD/".equals(referer)){
			request.setAttribute("message", "对不起，用户名或密码错误！！请重新登录！2秒后为您自动跳到登录页面！！<meta http-equiv='refresh' content='2;url="
				+ request.getContextPath() + "/'>");
//		}else{
//			request.setAttribute("message", "对不起，用户名或密码错误！！请重新登录！2秒后为您自动跳到登录页面！！<meta http-equiv='refresh' content='2;url="
//					+ request.getContextPath() + "/LoginUIServlet'>");
//		}
		request.getRequestDispatcher("/message.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
