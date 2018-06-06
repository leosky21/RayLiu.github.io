package cn.ray.cookie;

import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * - Cookie : 是客户端技术
 * 		- 程序把每个用户的数据以cookie的形式写给用户各自的浏览器。
 * 			当用户使用浏览器再去访问服务器中的web资源时，就会带着各自的数据去。
 * 			这样，web资源处理的就是用户各自的数据了。 
 * - Session : 服务器端技术
 * 		- 服务器在运行时可以为每一个用户的浏览器创建一个其独享的session对象
 * 			由于session为用户浏览器独享，所以用户在访问服务器的web资源时，可以把各自的数据放在各自的session中，
 * 
 * 
 * 
 * 		- response接口中定义了一个addCookie方法，它用于在其响应头中增加一个相应的Set-Cookie头字段。
 * 		- 同样，request接口中也定义了一个getCookies方法，它用于获取客户端提交的Cookie。
 * 
 * @author ray
 *
 */

import javax.servlet.http.Cookie;

@WebServlet("/APICookieTest")
public class APICookieTest extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		// - 访问html不产生cookie 
		// - 访问jsp , servlet产生cookie 
		
		//TODO 获取浏览器访问服务器传递来的cookie数组
		Cookie[] cookies = request.getCookies();//Cookie(String name, String value)  构造方法.
		
		if(cookies!=null)
			for(Cookie c : cookies){
				if(c.getName().equalsIgnoreCase("lastAccessTime")){
					long cookieValue = Long.parseLong(c.getValue());// TODO 得到了用户的上次访问时间
					Date d = new Date(cookieValue);
					response.getWriter().write("上次访问的时间:\n"+d.toLocaleString());
				}
			}
		
		/**
         * TODO 给用户回送最新的访问时间
         * 用户访问过之后重新设置用户的访问时间，存储到cookie中，然后发送到客户端浏览器
         */
		Cookie c = new Cookie("lastAccessTime",System.currentTimeMillis()+"");
		 // 将cookie对象添加到response对象中，这样服务器在输出response对象中的内容时就会把cookie也输出到客户端浏览器
		c.setMaxAge(1*24*3600);//设置cookie的有效期。以秒为单位的时间值。创建cookie时，不调用setMaxAge()，cookie的有效期是浏览器进程，意味着用户访问服务器，服务器回写一个cookie，这时cookie是没有设有效期的，当浏览器关闭，进程结束，cookie就没有了。下次打开浏览器，就不会带cookie过去了。如果调用setMaxAge()设置为1天，那么cookie的有效期是1天，意味用户使用浏览器访问服务器，服务器回写一个cookie，这时浏览器会把cookie写到本地硬盘，即使关闭浏览器再打开一个新的浏览器，还会带着cookie过去，只有过了1天了，cookie才失效。
		// TODO 将cookie的有效期设置为0，命令浏览器删除该cookie
		
		
		c.setPath("/Study_00");//TODO 设置cookie的有效目录。意味着你等一会儿去访问服务器下的Study_00下的所有web资源时，都会带着cookie过去
		response.addCookie(c);
		
		// TODO - 中文: 转码,解码
		Cookie zn = new Cookie("username",URLEncoder.encode("中文", "utf-8"));
		URLDecoder.decode(zn.getValue(), "utf-8");
	}

}
