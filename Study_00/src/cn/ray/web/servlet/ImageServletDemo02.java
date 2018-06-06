package cn.ray.web.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ImageServletDemo02")
public class ImageServletDemo02 extends HttpServlet {

	
	/**
		 * The doGet method of the servlet. <br>
		 *
		 * This method is called when a form has its tag value method equals to get.
		 * 
		 * @param request the request send by the client to the server
		 * @param response the response send by the server to the client
		 * @throws ServletException if an error occurred
		 * @throws IOException if an error occurred
		 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 通过Content-Type头字段，控制浏览器以哪种方式处理数据
		response.setHeader("Content-Type","image/png");
		  //定时刷新,通过响应头,设置刷新
        response.setHeader("refresh", "2;url="+request.getContextPath()+"/1.html");
		// 读取位于项目根目录下的  HTTP响应状态码 这张图片，返回一个输入流
		InputStream in = this.getServletContext().getResourceAsStream("/images/HTTP响应状态码");
		int len=0;
		byte[] buffer = new byte[1024];
		
		OutputStream out = response.getOutputStream();
		while( (len=in.read(buffer) ) !=-1 ){
			out.write(buffer);
		}
		
	}

	/**
		 * The doPost method of the servlet. <br>
		 *
		 * This method is called when a form has its tag value method equals to post.
		 * 
		 * @param request the request send by the client to the server
		 * @param response the response send by the server to the client
		 * @throws ServletException if an error occurred
		 * @throws IOException if an error occurred
		 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request,response);
		
	}

	/**
		 * Initialization of the servlet. <br>
		 *
		 * @throws ServletException if an error occurs
		 */
	public void init() throws ServletException {
		// Put your code here
	}

}
