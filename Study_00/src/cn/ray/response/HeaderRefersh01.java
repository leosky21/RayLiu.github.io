package cn.ray.response;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/HeaderRefersh01")
public class HeaderRefersh01 extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 设置refresh响应头控制浏览器每隔3秒钟刷新一次
		response.setHeader("refersh", "3");
		
		String data = new Random().nextInt(1000000)+"";
		response.getWriter().write(data);
	}

}
