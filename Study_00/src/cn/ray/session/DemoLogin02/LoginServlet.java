package cn.ray.session.DemoLogin02;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

	/**
		 * The doGet method of the servlet. <br>
		 *
		 * This method is called when a form has its tag value method equals to get.
		 * 
		 * @param request the request send by the client to the server
		 * @param response the response send by the server to the client
		 * @throws ServletException if an error occurred
		 * @throws IOException if an error occurred
		 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			
			String name = request.getParameter("username");
			String pass = request.getParameter("password");
			 List<User> list = DB.getAll();
			for(User user : list)
				if(name.equals(user.getUsername()) && pass.equals(user.getPassword())){
					request.getSession().setAttribute("user", user);
					response.sendRedirect("/Study_00/session/session02index.jsp");
					return ;
			}
		out.print("用户名或者密码不正确!!");
	}

}
