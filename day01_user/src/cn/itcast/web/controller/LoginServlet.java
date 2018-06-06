package cn.itcast.web.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.dao.impl.BusinessServiceImpl;
import cn.itcast.domain.User;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	 protected void doGet(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
	        String username = request.getParameter("username");
	        String password = request.getParameter("password");

	        BusinessServiceImpl service = new BusinessServiceImpl();
	        User user = service.login(username, password);
	        if(user!=null) {
	            request.getSession().setAttribute("user", user);
	            // 让用户登录成功后，跳转到首页

	            response.sendRedirect(request.getContextPath()+"/index.jsp");
	            return;
	        }

	        request.setAttribute("message", "对不起，用户名或密码错误！！请重新登录！2秒后为您自动跳到登录页面！！<meta http-equiv='refresh' content='2;url="+request.getContextPath()+"/LoginUIServlet'>");
	        request.getRequestDispatcher("/message.jsp").forward(request, response);
	    }

	    protected void doPost(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
	        doGet(request, response);
	    }

}
