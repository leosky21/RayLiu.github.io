package cn.ray.request;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HeaderGet01 extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		  System.out.println("------------------获取请求头方式1------------------");
	        String headValue = request.getHeader("Accept-Encoding"); // 根据请求头的名字获取对应的请求头的值
	        System.out.println(headValue);
	        
	        System.out.println("------------------获取请求头方式2------------------");
	        Enumeration<String> e = request.getHeaders("Accept-Encoding"); // 根据请求头的名字获取所有对应请求头的值
	        while(e.hasMoreElements()) {
	            String value = e.nextElement();
	            System.out.println(value);
	        }
	        System.out.println("------------------获取请求头方式3------------------");     
	        e = request.getHeaderNames(); // 获取所有的请求头
	        while(e.hasMoreElements()) {
	            String name = e.nextElement();
	            String value = request.getHeader(name);
	            System.out.println(name+"="+value);
	        }
	}

}
