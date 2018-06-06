package cn.itcast.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.Service.BusinessService;
import cn.itcast.domain.Book;


@WebServlet("/ListBookServlet")
public class ListBookServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		BusinessService se = new BusinessService();
		Map<String, Book> bookAll = se.getAll();
		this.getServletContext().setAttribute("AllBook", bookAll);
		
		req.getRequestDispatcher("/WEB-INF/jsp/listbook.jsp").forward(req, resp);;
		
	}
	
}
