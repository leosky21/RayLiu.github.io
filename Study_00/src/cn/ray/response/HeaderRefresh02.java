package cn.ray.response;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



@WebServlet("/HeaderRefresh02")
public class HeaderRefresh02 extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setHeader("Content-Type", "text/html;charset=UTF-8");
		//response.setHeader("refresh", "3;url='HeaderRefresh01'");
		response.setHeader("refresh", "3;url='/Study_00/HeaderRefresh01'");
		response.getWriter().write("本浏览器将在3秒后跳转");
	}

}
