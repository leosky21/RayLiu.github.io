package cn.ray.request;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 	请求转发：
 * 		指一个web资源收到客户端请求后，通知服务器去调用另外一个web资源进行处理。 
 * 
 * @author ray
 */

@WebServlet("/DispatcherForwardTest")
public class DispatcherForwardTest extends HttpServlet {

	 // mvc设计模式(m--model(javabean) v--view(jsp) c--controller(servlet))
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String data="aaaaaaaaaaaaa";
		// 每一个请求都有一个request，每个的数据都存在对应的request里面，跳到jsp，jsp显示各自的数据
		request.setAttribute("data", data);
		  // request实现转发
		request.getRequestDispatcher("/request/param01.jsp").forward(request, response);
		
		/**
		 * 	请求转发的特点：
				客户端只发一次请求，而服务器端有多个资源调用。
				客户端浏览器地址栏没有发生变化。
		 */
	}

}
