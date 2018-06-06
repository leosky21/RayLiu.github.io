package cn.ray.controller;

import java.io.IOException;

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

@WebServlet("/AddCustomerServlet")
public class AddCustomerServlet extends HttpServlet{
    // 给用户提供一个添加客户界面
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		 req.setCharacterEncoding("UTF-8");
		 req.setAttribute("genders", GlobalValue.genders);
	     req.setAttribute("preferences", GlobalValue.preferences);
	     req.setAttribute("types", GlobalValue.types);
	     
	     req.getRequestDispatcher("/WEB-INF/jsp/addcustomer.jsp").forward(req, resp);
	}
	
	// 接收表单的添加客户请求(post)，并处理用户的添加请求
	
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
		 try {
	            request.setCharacterEncoding("UTF-8");
	            
	            // 在实际开发中，一定要对表单进行校验

	            // 把表单数据封装到customer对象中
	            Customer c = WebUtils.request2Bean(request, Customer.class);
	            c.setId(WebUtils.generateID());

	            BussinessService service = new BussinessServiceImpl();
	            service.addCustomer(c);
	            System.out.println(c.getName()+"::"+c.getCellPhone()+"::"+c.getGender()+"::"+c.getDescription()+"::"+c.getType());
	            request.setAttribute("message", "添加成功！！！");
	        } catch (Exception e) {
	            e.printStackTrace();
	            request.setAttribute("message", "添加失败！！！");
	        }
	        request.getRequestDispatcher("/WEB-INF/jsp/addcustomer.jsp").forward(request, response);

	    }
}
