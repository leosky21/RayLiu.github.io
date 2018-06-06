package cn.ray.session.Demo01;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/BuyServlet")
public class BuyServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String id = request.getParameter("id");
		Book book = Db.getAll().get(id);
		
		//如果要关掉浏览器之后,重新打开购物车记录还在,可以存储在 cookie里
		HttpSession session = request.getSession();
		
		// 从session中得到用户用于保存所有书的集合（即得到用户的购物车）
        List<Book> list = (List<Book>) session.getAttribute("list");
        if(list==null) {
            list = new ArrayList<Book>();
            session.setAttribute("list", list);
        }
        list.add(book);
		
        // 记住千万不要用请求转发
        // request.getRequestDispatcher("/ListCarServlet").forward(request, response);

        /**
         * 显示购物车，通常会用到重定向技术。
         * request.getContextPath()返回"/Study_00"
         */
        /**
         * TODO  若浏览器禁用Cookie后，又该怎么解决呢？ 
			- 解决方案：URL重写。此时代表网站首页的Servlet——ListBookServlet应修改为：
         */
        String url = response.encodeRedirectURL(request.getContextPath()+"/CarServlet");
        response.sendRedirect(url);
	}

}
