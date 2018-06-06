package cn.ray.response;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * 客户机请求服务器，访问Servlet，Servlet产生数据，
 * 		数据不适合在Servlet输出！
 * 	这时Servlet通常会把请求转发给jsp，由jsp负责输出数据。
 * @author ray
 *
 */
@WebServlet("/RefreshDirect03")
public class RefreshDirect03 extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html");
		String message = "<meta http-equiv='refresh' content='3;url=/Study_00/index.jsp'>恭喜你登陆成功,本浏览器将在3秒后跳转";
		request.setAttribute("messgae", message);
		this.getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
	}

}
