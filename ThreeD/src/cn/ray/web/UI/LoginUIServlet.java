package cn.ray.web.UI;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/LoginUIServlet")
public class LoginUIServlet extends HttpServlet {
	 /**
	 * 
	 */
	private static final long serialVersionUID = -6371564306524421892L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
	        request.getRequestDispatcher("/login.jsp").forward(request, response);
	    }

	    protected void doPost(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
	        doGet(request, response);
	    }
}
