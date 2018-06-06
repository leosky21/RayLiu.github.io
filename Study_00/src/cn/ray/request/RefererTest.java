package cn.ray.request;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * 			<http://blog.csdn.net/yerenyuan_pku/article/details/51923376> --  web工程中URL地址的推荐写法
 * @author liujun
 *
 */

@WebServlet("/RefererTest")
public class RefererTest extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 得到来访者从哪个资源来访问服务器
		String referer = request.getHeader("Referer");
		
		 /*
         * 若来访者直接在浏览器地址栏输入http://localhost:8080/Study_00/RefererTest，
         * 即来访者没从任何页面点过来，则referer == null
         */
		  if(referer == null || !referer.startsWith("http://localhost")) {
	            // 必须得用重定向，要明确告诉来访者跳到首页上面去了
	            response.sendRedirect("/Study_00/index.jsp");
	            return; // 重定向完了之后后面的代码不需要执行，所以要return
	        }
		  
		  String data = "防盗链测试";
		  response.setContentType("text/html;charset=UTF-8");
		  response.getWriter().write(data);
		  
		  /**
		   * 
		   * 请求重定向和请求转发的区别
				- 一个web资源收到客户端请求后，通知服务器去调用另外一个web资源进行处理，称之为请求转发。
				- 一个web资源收到客户端请求后，通知浏览器去访问另外一个web资源，称之为请求重定向。
				- RequestDispatcher.forward
					方法只能将请求转发给同一个WEB应用中的组件；
					而HttpServletResponse.sendRedirect方法
							可以重定向到同一个站点上的其他应用程序中的资源，甚至是使用绝对URL重定向到其他站点的资源。 
		   */
		  /**
		   * - 如果传递给HttpServletResponse.sendRedirect方法的相对URL以“/”开头，
		   * 		它是相对于整个WEB站点的根目录；
		   * - 如果创建RequestDispatcher对象时指定的相对URL以“/”开头，
		   * 		它是相对于当前WEB应用程序的根目录。 
				
				
				调用HttpServletResponse.sendRedirect方法重定向的访问过程结束后，
						浏览器地址栏中显示的URL会发生改变，由初始的URL地址变成重定向的目标URL；
				调用RequestDispatcher.forward方法的请求转发过程结束后，
						浏览器地址栏保持初始的URL地址不变。 
			* - HttpServletResponse.sendRedirect方法对浏览器的请求直接作出响应，(2 resq /  1 resp)
			* 		响应的结果就是告诉浏览器去重新发出对另外一个URL的访问请求；
			* - RequestDispatcher.forward方法在服务器端内部将请求转发给另外一个资源，
			* 		浏览器只知道发出了请求并得到了响应结果，并不知道在服务器程序内部发生了转发行为。 <----- 浏览器  只知道   开始 和  结果------>
			  - RequestDispatcher.forward方法的
			  			调用者与被调用者之间共享相同的request对象和response对象，它们属于同一个访问请求和响应过程；
			  - 而HttpServletResponse.sendRedirect方法
			 			调用者与被调用者使用各自的request对象和response对象，它们属于两个独立的访问请求和响应过程。
		   */
		  
	}

}
