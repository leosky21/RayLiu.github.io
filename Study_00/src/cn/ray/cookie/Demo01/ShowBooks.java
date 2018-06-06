package cn.ray.cookie.Demo01;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 
 * 	1. 一个Cookie只能标识一种信息，它至少含有一个标识该信息的名称（NAME）和设置值（VALUE）。
 * 	2. 一个WEB站点可以给一个WEB浏览器发送多个Cookie，一个WEB浏览器也可以存储多个WEB站点提供的Cookie。
 * 	3. 浏览器一般只允许存放300个Cookie，每个站点最多存放20个Cookie，每个Cookie的大小限制为4KB。
 * 	4. 如果创建了一个cookie，并将他发送到浏览器，默认情况下它是一个会话级别的cookie（即存储在浏览器的内存中），用户退出浏览器之后即被删除。
 * 		若希望浏览器将该cookie存储在磁盘上，则需要使用maxAge，并给出一个以秒为单位的时间。将最大时效设为0则是命令浏览器删除该cookie。 
		
		注意，删除cookie时，path必须一致，否则不会删除。
 * 
 * @author 
 *
 */
@WebServlet("/ShowBooks")
public class ShowBooks extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charSet=utf-8");
		
		PrintWriter out = response.getWriter();
		
		Map<String,Book> map = Db.getAll();
		
		for (Map.Entry<String, Book> entry : map.entrySet() ) {
			Book b = entry.getValue();
			out.print("<a href='/Study_00/DetailsUpdate?id="+b.getId()+"' target='_blank'>"+b.getName()+"</a><br/>");
		}
		
		//显示用户曾经看过的商品
		out.print("<br/>您曾经看过的如下的商品:<br/>");
		Cookie[] cookies = request.getCookies();
		for(int i=0;cookies!=null && i<cookies.length;i++){
			if(cookies[i].getName().equals("bookHistory")){
				String[] ids = cookies[i].getValue().split("#");
				for(String id : ids){
					Book book = Db.getAll().get(id);
					out.print(ids.length+"::"+book.getName()+"<br/>");
				}
			}
		}
	}

}
