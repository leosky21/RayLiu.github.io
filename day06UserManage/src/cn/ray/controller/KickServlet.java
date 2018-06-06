package cn.ray.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class KickServlet
 */
@WebServlet("/KickServlet")
public class KickServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String username  = request.getParameter("username");
		// 提交过来的中文名字是使用UTF-8字符集编码的，所以无须再编码和解码
        // username = new String(username.getBytes("ISO8859-1"), "UTF-8");
		
		Map map = (Map)this.getServletContext().getAttribute("map");
		HttpSession session = (HttpSession)map.get(username);
		
		if(session != null){
			session.invalidate();
			map.remove(username);
		}
		response.sendRedirect("/day06UserManage/kickUser.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
