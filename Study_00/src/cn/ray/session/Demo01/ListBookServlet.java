package cn.ray.session.Demo01;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ListBookServlet")
public class ListBookServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        out.print("本网站有如下商品：<br/>");
        Map<String, Book> map = Db.getAll();
        for (Map.Entry<String, Book> entry : map.entrySet()) {
            Book book = entry.getValue();
            String url = "/Study_00/BuyServlet?id="+book.getId();
            out.print(book.getName() + "<a href='"+url+"' target='_blank'>购买</a><br/>");
        }

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

}
