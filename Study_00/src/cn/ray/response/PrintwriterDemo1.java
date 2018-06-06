package cn.ray.response;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/PrintwriterDemo1")
public class PrintwriterDemo1 extends HttpServlet {

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
		 // 设置response使用的码表，以控制response以什么码表向浏览器写出数据
        response.setCharacterEncoding("UTF-8");

        // 指定浏览器以什么码表打开服务器发送的数据
        response.setHeader("content-type", "text/html;charset=UTF-8");
        response.setContentType("text/html");
        
        // 也可以 3 换 1  ???? 
        response.setContentType("text/html;charset=UTF-8");
        
        
       
		PrintWriter out = response.getWriter();
		 String data = "中国";
	        
	        out.write(data);
		
	}

}
