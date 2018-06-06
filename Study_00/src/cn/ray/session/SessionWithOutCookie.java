package cn.ray.session;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 浏览器禁用Cookie后的session处理
 * 		- 解决方案: URL重写
 * 		 
 * 禁用Cookie后，你访问服务器，都是通过点击超链接来访问，不以cookie的形式带session_id号过来，
 * 			通过点击超链接的时候再带过来。 
		每个用户先访问首页，不同的用户在访问首页的时候，
		我给你回送首页超链接的时候，在每一个超链接的后面都跟上你的session_id号，
		也就是说一访问首页，我就帮你创建session，
		然后在每一个超链接的后面都跟上session_id号再打给你，
		这时你一点超链接，超链接屁股后面带着各自的session_id号来了，
		那就不以cookie的形式带session_id号过来嘛，
		带过来之后服务器端得到你URL带过来的session_id号去获取对应的session。
		
		- response. encodeRedirectURL(java.lang.String url)
				——用于对sendRedirect方法后的url地址进行重写。
		- response. encodeURL(java.lang.String url)
				——用于对表单action和超链接的url地址进行重写。
				
		用户第一次访问服务器，服务器不知道用户有没有禁用cookie，
				所以它会把session的id号以cookie的形式回写，并且也会把url地址重写.
			二次访问服务器，若用户没有禁用cookie，
				就会带cookie过来，服务器发现你带cookie过来了，就不会重写url地址了。
 *
 *
 */

@WebServlet("/SessionWithOutCookie")
public class SessionWithOutCookie extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		
		PrintWriter out = response.getWriter();
		
		request.getSession();//帮助创建Session,没必要创建 session 对象的引用
		
		/*
         * 第一次访问服务器，服务器不知道用户有没有禁用cookie，它会把session的id号以cookie的形式
         * 回写，并且也会把url地址重写。第二次访问服务器，若用户没有禁用cookie，就会带cookie过来，
         * 服务器发现你带cookie过来了，就不重写url地址了。
         */
        String url1 = response.encodeURL("/Study_00/SessionDemo01");
        String url2 = response.encodeURL("/Study_00/SessionDemo02");

        out.print("<a href='"+url1+"'>购买</a><br/>");
        out.print("<a href='"+url2+"'>结账</a>");
	}

}
