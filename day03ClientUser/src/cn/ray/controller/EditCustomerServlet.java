package cn.ray.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.ray.beans.Customer;
import cn.ray.dao.BussinessService;
import cn.ray.dao.BussinessServiceImpl;
import cn.ray.utils.GlobalValue;
import cn.ray.utils.WebUtils;

@WebServlet("/EditCustomerServlet")
public class EditCustomerServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		 request.setCharacterEncoding("UTF-8");
		 response.setCharacterEncoding("UTF-8");
	        try {
	            // 把填写后的表单的修改信息封装到customer对象中
	            Customer c = WebUtils.request2Bean(request, Customer.class);
	            BussinessService service = new BussinessServiceImpl();
	            service.updateCustomer(c);

	            request.setAttribute("message", "更新成功！！！");
	        } catch (Exception e) {
	            e.printStackTrace();
	            request.setAttribute("message", "更新失败！！！");
	        }
	        request.getRequestDispatcher("/WEB-INF/jsp/editcustomer.jsp").forward(request, response);
	    }
	
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 request.setCharacterEncoding("UTF-8");
		 response.setCharacterEncoding("UTF-8");
		String id = request.getParameter("id");
        BussinessService service = new BussinessServiceImpl();
        Customer c = service.findCustomer(id);

        request.setAttribute("genders", GlobalValue.genders);
        request.setAttribute("preferences", GlobalValue.preferences);
        request.setAttribute("types", GlobalValue.types);

        request.setAttribute("c", c);
        request.getRequestDispatcher("/WEB-INF/jsp/editcustomer.jsp").forward(request, response);
	}

	
}
