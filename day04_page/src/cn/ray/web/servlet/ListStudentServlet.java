package cn.ray.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.ray.beans.PageBean;
import cn.ray.beans.QueryInfo;
import cn.ray.service.BuinessService;
import cn.ray.utils.WebUtil;

@WebServlet("/ListStudentServlet")
public class ListStudentServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html");
		QueryInfo info = WebUtil.request2Bean(request, QueryInfo.class);
		BuinessService service = new BuinessService();
		
		PageBean bean = service.pageBean(info);
		request.setAttribute("pagebean", bean);
        request.getRequestDispatcher("/index.jsp").forward(request, response);
	}

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req, resp);
	}
	

}
