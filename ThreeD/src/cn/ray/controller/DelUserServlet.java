package cn.ray.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.ray.dao.BussinessService;
import cn.ray.dao.BussinessServiceImpl;


@WebServlet("/DelUserServlet")
public class DelUserServlet extends HttpServlet {

	/**
	 *  给用户以删除客户的界面
	 */
	private static final long serialVersionUID = 1L;
	BussinessService bs = new BussinessServiceImpl();
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try{
			String id = request.getParameter("id");
			
			
			
			bs.deleteUser(
					Integer.parseInt(id));
			request.setAttribute("message", "删除成功");
				
			
			
		}catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("message", "删除失败"); 
		}finally {	
			request.getRequestDispatcher("/ListUserServlet").forward(request, response);
			//response.sendRedirect("/ListCustomerServlet");
		}
		
		
	}

	
}
