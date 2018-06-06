package cn.ray.session.DemoLogin02;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/LogoutServlet")
public class LogoutServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
		if(session==null){
			response.sendRedirect("/Study_00/session/session02index.jsp");
			return ;
		}
		
		session.removeAttribute("user"); // 移除登录标记
		response.sendRedirect("/Study_00/session/session02index.jsp");
	}

}
