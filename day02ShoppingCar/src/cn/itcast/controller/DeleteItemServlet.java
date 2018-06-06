package cn.itcast.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.Service.BusinessService;
import cn.itcast.domain.Cart;

@WebServlet("/DeleteItemServlet")
public class DeleteItemServlet extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html");
		BusinessService bs = new BusinessService();
		bs.remove(
					request.getParameter("id"), 
				(Cart)request.getSession().getAttribute("cart"));
		
		String url = response.encodeRedirectURL(request.getContextPath()+"/listCarUIServlet");
		response.sendRedirect(url);
		
	}

}
