package cn.ray.session.Demo01;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/CarServlet")
public class CarServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html;charset=UTF-8");
		
		PrintWriter out = response.getWriter();
		
		HttpSession session = request.getSession(false);
        if(session == null) {
            out.print("您没有购买任何商品！！！");
            return;
        }
        out.print("您购买了如下商品：<br/>");
        List<Book> list = (List<Book>) session.getAttribute("list");
        for(Book book : list) {
            out.print(book.getName()+"<br/>");
        }
	}

}
