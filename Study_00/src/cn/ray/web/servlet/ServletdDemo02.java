package cn.ray.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ServletdDemo02")
public class ServletdDemo02 extends HttpServlet {

	/**
	 * - 转发 
			你请求我这个Servlet，我这个Servlet把你的请求转给他处理。用大白话来说就是你找我借钱，我说我没有，我帮你去找他。客户机发了一次请求！
	   - 重定向 
			用大白话来说就是你找我借钱，我说我没有，我要你自己去找他。客户机发了二次请求
	 */
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String data = "aaaaaaaaaaaaaaaa";
		// 把数据带给1.jsp
        /**
         * - ServletContext对象会被所有的web资源所共享，
	         * 通过他来带数据，会出现数据紊乱的现象。
	         * 在实际开发里面，不能通过context域，要通过request域
         */
        this.getServletContext().setAttribute("data", data);
        RequestDispatcher rd = this.getServletContext().getRequestDispatcher("/1.jsp"); // 获取请求转发对象(RequestDispatcher)
        rd.forward(request, response); // 调用forward方法实现请求转发
		/**
		 *  - ServletContext对象会被所有的web资源所共享，通过它来带数据，会出现数据紊乱的现象。在实际开发里面，不能通过context域，要通过request域。 
		 */
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}


}
