package cn.ray.response;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * 使用OutputStream流输出中文注意问题：
 * 		在服务器端，数据是以哪个码表输出的，
 * 		那么就要控制客户端浏览器以相应的码表打开，
 * 		比如：outputStream.write("中国".getBytes("UTF-8"));
 * 	
 * 使用OutputStream流向客户端浏览器输出中文，
 * 		以UTF-8的编码进行输出，此时就要控制客户端浏览器以UTF-8的编码打开，
 * 		否则显示的时候就会出现中文乱码，
 * 		那么在服务器端如何控制客户端浏览器以UTF-8的编码显示数据呢？
 * 		可以通过设置响应头控制浏览器的行为，
 * 		例如：
 * 		response.setHeader("content-type", "text/html;charset=UTF-8");
 * 		通过设置响应头控制浏览器以UTF-8的编码显示数据
 * @author liujun
 *
 */
@WebServlet("/OutputSreamDemo2")
public class OutputStreamDemo2 extends HttpServlet {

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

		response.setContentType("text/html");
		OutputStream out = response.getOutputStream();
		out.write(1);//输出的是空白
		
	}

}
