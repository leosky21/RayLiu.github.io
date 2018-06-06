package cn.ray.cookie.Demo01;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/DetailsUpdate")
public class DetailsUpdate extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html;Charset=utf-8");
		PrintWriter out = response.getWriter();
		
		
		//1. 通过用户带过来的id,获取商品的详细信息
		String id = request.getParameter("id");
		Book book = Db.getAll().get(id);
		out.print("Name::"+book.getName()+"<br/>"
				+ "Author::"+book.getAuthor()+"<br/>"
				+ "Description::"+book.getDescription()+"<br/>"
				+ "ID::"+book.getId()+"<br/>"
				);
		
		//2. 构建cookie ,回写给浏览器
		String cookieValue = buildCookie(id,request);
		Cookie cookie = new Cookie("bookHistory",cookieValue);
		cookie.setMaxAge(1*3600*24);
		cookie.setPath("/Study_00");
		
		response.addCookie(cookie);
	}

	private String buildCookie(String id, HttpServletRequest request) {
		//① bookHistory=null	1 	1(返回值)
		
		String bookHistroy = null;
		Cookie[] cs = request.getCookies();
		for(int i=0;cs!=null&&i<cs.length;i++){
			if(cs[i].getName().equals("bookHistory")){
				
				if(cs[i].getValue() != null)
					bookHistroy = cs[i].getValue();
			}
		}
		if(bookHistroy==null){
			return id;
		}
		
		//最多只能显示3条浏览记录
		LinkedList<String> list = new LinkedList<String>(Arrays.asList(bookHistroy.split("#")));
		
		
		if(list.contains(id)){
			list.remove(id);
		}else 
			if(list.size()>=3){
			list.removeLast();
		}
		
		list.addFirst(id);
		
		StringBuffer sb = new StringBuffer();//线程安全----99.999%不会用它
		for (String string : list) {
			sb.append(string+"#");
		}
		return sb.deleteCharAt(sb.length()-1).toString();
		
	}

}
