package cn.ray.response;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



//缓存  一个小时，所以以后访问 HeaderExpires02，
		//都不会向服务器发送任何请求，而是向缓存拿数据
@WebServlet("/HeaderExpires02")
public class HeaderExpires02 extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		response.setDateHeader("expires", System.currentTimeMillis()+1000*3600);
		
		String data = "aaaaaaa";
		
		response.getWriter().write(data);
	}

}
