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
@WebServlet("/ChangeQuantityServlet")
public class ChangeQuantityServlet extends HttpServlet {

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
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		 String id = request.getParameter("id");
	        String quantity = request.getParameter("quantity");

	        Cart cart = (Cart) request.getSession().getAttribute("cart");

	        BusinessService service = new BusinessService();
	        service.changeItemQuantity(id, quantity, cart);

	        String url = response.encodeRedirectURL(request.getContextPath()+"/listCarUIServlet");
	        response.sendRedirect(url);
	}

}
