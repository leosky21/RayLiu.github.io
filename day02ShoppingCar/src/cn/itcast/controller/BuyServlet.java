package cn.itcast.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.Service.BusinessService;
import cn.itcast.domain.Book;
import cn.itcast.domain.Cart;

@WebServlet("/BuyServlet")
public class BuyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String id = request.getParameter("id");
        BusinessService service = new BusinessService();
        Book book = service.find(id);

        // 得到用户的购物车
        Cart cart = (Cart) request.getSession().getAttribute("cart");//Session 持久化对象???
        
        // 用户第一次购买，为用户创建购物车
        if(cart==null) {
            cart = new Cart();
            request.getSession().setAttribute("cart", cart);
        }

        // 把书加到用户的购物车中，完成购买
        cart.add(book);
       // request.getSession().setAttribute("cart", cart);//为什么这句话不加也没事?? :  JSSESSIONID 被加载到链接之后,
        
        /* 
         * 浏览器重新访问地址：/WEB-INF/jsp/listcart.jsp
         * 但这个地址被保护起来，外面是无法直接访问的，
         * 要实现的话，会比较麻烦，需要先跳到servlet，然后再转到jsp。
         */
        // response.sendRedirect("/WEB-INF/jsp/listcart.jsp");
        String url = response.encodeRedirectURL(request.getContextPath()+"/listCarUIServlet");//防止浏览器禁用cookie
        response.sendRedirect(url);
	}

}
