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
@WebServlet("/ClearCartServlet")
public class ClearCartServlet extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		BusinessService bs = new BusinessService();
		Cart cart = (Cart) request.getSession().getAttribute("cart");
		bs.removeAll(cart);
	}

}
