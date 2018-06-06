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

@WebServlet("/AddUserServlet")
public class AddUserServlet extends HttpServlet {
	// 给用户提供一个添加客户界面

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");

		req.getRequestDispatcher("/addUser.jsp").forward(req, resp);
	}

	// 接收表单的添加客户请求(post)，并处理用户的添加请求

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			request.setCharacterEncoding("UTF-8");

			User c = WebUtils.request2Bean(request, User.class);
			// c.setId(WebUtils.generateID());

			BussinessService service = new BussinessServiceImpl();
			service.addUser(c);
			request.setAttribute("message", "添加成功！！！");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("message", "添加失败！！！");
		}
		request.getRequestDispatcher("/addUser.jsp").forward(request, response);

	}
}
