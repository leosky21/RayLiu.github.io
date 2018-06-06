package cn.ray.web.servlet;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.GZIPOutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * - 一道面试题：请说出servlet的生命周期。 
	答：servlet对象是用户第一次访问时创建，对象创建之后就驻留在内存里面了，
	响应后续的请求。servlet对象一旦被创建，
		- init()就会被执行，
		- 客户端的每次请求导致service()方法被执行，
		- servlet对象被摧毁时(web服务器停止后或者web应用从服务器里删除时)，
		- destory()方法就会被执行。
 */
/**
 * Servlet implementation class ServletDemo01
 */
@WebServlet("/ServletDemo01")
public class ServletDemo01 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		/**
		 *  - 1. 用location和302实现请求重定向
		 */
		
		response.setStatus(302);
		response.setHeader("location", "/Study_00/index.jsp");
		String data = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"+
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"+
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"+
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"+
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
		this.getServletContext().setAttribute("before", data.getBytes().length);
		ByteArrayOutputStream bout = new ByteArrayOutputStream();
		GZIPOutputStream gout = new GZIPOutputStream(bout);
		gout.write(data.getBytes());
		/*
         * GZIPOutputStream：包装流，一般都会有一个缓冲，
         * 如果调用其write()方法在写数据时，
         * 如果数据量没有把包装流的缓冲写满，它是不会往底层流写的。
         */
		gout.close();
		// 得到压缩后的数据
		byte[] gzip = bout.toByteArray();
		this.getServletContext().setAttribute("after", gzip.length);
		// 通知浏览器数据采用压缩格式
		response.setHeader("Content-Encoding", "gzip");
		// 告诉浏览器回送的压缩数据的长度
        response.setHeader("Content-Length", gzip.length+"");
        response.getOutputStream().write(gzip);
      
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
