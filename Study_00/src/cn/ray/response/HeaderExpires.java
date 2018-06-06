package cn.ray.response;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/HeaderExpires01")
public class HeaderExpires extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/**
		 * 	- 设置http响应头控制浏览器禁止缓存当前文档内容
		 * 			- 第一种:
		 * 				 DateHeader : expires -1
		 * 			- 第二种:
		 * 				 Cache-Control
		 * 			- 第三种:
		 * 				 Pragma
		 */
		response.setDateHeader("expires", -1);
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Pragma", "no-cache");
		
		
		
	}

}
