package cn.ray.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.ray.beans.Customer;
import cn.ray.beans.PageBean;
import cn.ray.beans.QueryInfo;
import cn.ray.dao.BussinessService;
import cn.ray.dao.BussinessServiceImpl;
import cn.ray.utils.WebUtils;


@WebServlet("/DelCustomerServlet")
public class DelCustomerServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try{
			String id = request.getParameter("id");
			BussinessService bs =new BussinessServiceImpl();
			
			
			bs.deleteCustomer(id);
			request.setAttribute("message", "删除成功");
				
			
			
		}catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("message", "删除失败"); 
		}finally {	
			request.getRequestDispatcher("/ListCustomerServlet").forward(request, response);
			//response.sendRedirect("/ListCustomerServlet");
		}
		
		
	}

	
}
