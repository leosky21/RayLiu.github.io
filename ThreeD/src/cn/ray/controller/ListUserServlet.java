package cn.ray.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.ray.beans.PageBean;
import cn.ray.beans.QueryInfo;
import cn.ray.dao.BussinessService;
import cn.ray.dao.BussinessServiceImpl;
import cn.ray.utils.WebUtils;


@WebServlet("/ListUserServlet")
public class ListUserServlet extends HttpServlet {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try{
			 /*
             * 如果用户带查询条件过来，就将查询条件封装到QueryInfo里面去。
             * 如果用户没带带查询条件过来，也即第一次查询，也要生成一个QueryInfo，使用默认查询条件，即从第1页查看5条记录。
             */
            QueryInfo info = WebUtils.request2Bean(request, QueryInfo.class);
			System.out.println("ListUserServlet::"+info.getCurrentpage()+"::"+info.getStartindex()+"::"+info.getPagesize());
			BussinessService service = new BussinessServiceImpl();
		
			PageBean pagebean =  service.pageQuery(info);

            request.setAttribute("pagebean", pagebean);
		
		}catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("message", "查看客户失败!!");
		}finally {
			request.getRequestDispatcher("/listUser.jsp").forward(request, response);
		}
	}

}
