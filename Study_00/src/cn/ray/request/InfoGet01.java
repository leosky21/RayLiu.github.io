package cn.ray.request;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class InfoGet01 extends HttpServlet {

	/**
	 * 获取客户机信息
	 
		getRequestURL方法返回客户端发出请求时的完整URL。
		getRequestURI方法返回请求行中的资源名部分。 
			URI——用于标识(任意)一个资源，如/news/1.html、c:\abc\a.txt。 
			URL——用于标识互联网上的一个资源，如http://www.sina.com/news/1.html。 
			所以URI包含URL。
		getQueryString方法返回请求行中的参数部分。
		getPathInfo方法返回请求URL中的额外路径信息。额外路径信息是请求URL中的位于Servlet的路径之后和查询参数之前的内容，它以“/”开头。
		getRemoteAddr方法返回发出请求的客户机的IP地址。
		getRemoteHost方法返回发出请求的客户机的完整主机名。
		getRemotePort方法返回客户机所使用的网络端口号。
		getLocalAddr方法返回WEB服务器的IP地址。
		getLocalName方法返回WEB服务器的主机名。

	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");// 只对客户机的post方式提交有效
		 String username = request.getParameter("username");
		 username = new String(username.getBytes("ISO8859-1"), "UTF-8");//Get方法通过手动方式
		// <a href="day06/RequestDemo4?username=<%=URLEncoder.encode('中国', 'UTF-8')%>">点点</a>
		 
		System.out.println(request.getRequestURI());// 打印/Study_00/servlet/HeadersGet01
		System.out.println(request.getRequestURL());// 打印http://localhost:8080/Study_00/servlet/HeadersGet01
		System.out.println(request.getQueryString());// 方法返回请求行中的参数部分。
		System.out.println(request.getMethod()); // Get
	}

}
