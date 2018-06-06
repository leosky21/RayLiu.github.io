package cn.ray.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.ray.beans.User;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet(
		urlPatterns = { "/LoginServlet" }, 
		initParams = { 
				@WebInitParam(name = "redirctURL", value = "/day06UserManage/index.jsp")
		})
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        
        request.getSession().setAttribute("user", user);
        response.sendRedirect(this.getInitParameter("redirctURL"));
       
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
