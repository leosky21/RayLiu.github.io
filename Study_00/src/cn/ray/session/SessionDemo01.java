package cn.ray.session;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
/**
 * TODO Tomcat 的session 持久，默认是支持的
 * 
 * 
	- 三个域对象
 * 		ServletContext 
			这个程序产生数据之后，(数据)除了显示给用户外，不仅等一会儿还要用，还要给别人用，这时就选用ServletContext。(应用：聊天室)
		request 
			这个程序产生数据之后，(数据)如果显示完了就没用了，这时就选用request。
		session 
			这个程序产生数据之后，(数据)除了显示给用户外等一会儿还要用，这时就选用session。
 * @author liujun
 *
 */
@WebServlet("/SessionDemo01")
public class SessionDemo01 extends HttpServlet {
//session是基于cookie的
	//3C2254924A7470C5247D699E8EAB9C96
	//3C2254924A7470C5247D699E8EAB9C96
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession(false);//不创建session,只获取
		
		session.setAttribute("name", "Session购买");
		String name = (String) request.getSession().getAttribute("name");
		name = "Session改过了???";
		
		
		/**
		 *  TODO 如何实现多个IE浏览器共享同一session？
		 *  		
		 *  		即关浏览器再开浏览器能拿到同一个session或者两个浏览器共享同一个session。 
		 *  应用：关掉IE浏览器后，再开IE浏览器，上次购买的商品还在。 
		 */
		
		Cookie cookie = new Cookie("JSESSIONID",session.getId());
		cookie.setMaxAge(30*60);
		cookie.setPath("/Study_00");
		response.addCookie(cookie);
		//session.invalidate();//手动摧毁session
	}
/**
 * 问题：TODO 用户开一个浏览器访问一个网站，服务器是不是针对这次会话创建一个session？ 
		答：不是的。session的创建时机是在程序中第一次去执行request.getSession();
			这个代码，服务器才会为你创建session。 
	
	问题：TODO 关闭浏览器，会话结束，session是不是就销毁了呢？ 
		答：不是的。session是30分钟没人用了才会死，服务器会自动摧毁session。
		
		可以在web.xml文件中设置<Session-config> 例如Session 的访问时间
 */
}
