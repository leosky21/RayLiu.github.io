package cn.ray.web.servlet;

import java.io.IOException;
import java.io.InputStream;


import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/DownloadServlet")
public class DownloadServlet extends HttpServlet {	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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
		/**
		 * 设置content-disposition响应头，让浏览器下载文件
		 */
		response.setHeader("Content-Disposition","attachment;filename=HTTP响应状态码.png");
		InputStream in = this.getServletContext().getResourceAsStream("/images/HTTP响应状态码");
		
		int len=0;
		byte[] buffer = new byte[1024];
		ServletOutputStream out = response.getOutputStream();
		while((len=in.read(buffer))!=-1){
			out.write(buffer, 0, len);
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

}
