package cn.ray.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.ray.beans.User;
import cn.ray.dao.BussinessService;
import cn.ray.dao.BussinessServiceImpl;
import cn.ray.utils.WebUtils;

@WebServlet("/EditUserServlet")
public class EditUserServlet extends HttpServlet {
	/**
	 * 给用户一个更新客户的功能
	 */
	private static final long serialVersionUID = 1L;

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		 request.setCharacterEncoding("UTF-8");
		 response.setCharacterEncoding("UTF-8");
	        try {
	            // 把填写后的表单的修改信息封装到customer对象中
	            User c = WebUtils.request2Bean(request, User.class);
	            BussinessService service = new BussinessServiceImpl();
	            service.updateUser(c);

	            request.setAttribute("message", "更新成功！！！");
	        } catch (Exception e) {
	            e.printStackTrace();
	            request.setAttribute("message", "更新失败！！！");
	        }
	        request.getRequestDispatcher("/editUser.jsp").forward(request, response);
	    }
	
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 request.setCharacterEncoding("UTF-8");
		 response.setCharacterEncoding("UTF-8");
		String id = request.getParameter("id");
        BussinessService service = new BussinessServiceImpl();
        User c = service.findUser(Integer.parseInt(id));
       
        request.setAttribute("c", c);
        request.getRequestDispatcher("/editUser.jsp").forward(request, response);
	}

	
}
