package com.jf.action;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jf.model.Reader;
import com.jf.myspringmvc.Controller;
import com.jf.myspringmvc.RequestMapping;
import com.jf.service.ReadServicel;
import com.jf.service.impl.ReaderServiceImpl;
import com.jf.utils.Md5Utils;

@Controller
public class ReaderLoginAction {
	
	private ReadServicel rservice = new ReaderServiceImpl();
	
	@RequestMapping("/ReaderLoginAction_login")
	public String login(HttpServletRequest request, HttpServletResponse response){
		
		String paperNo = request.getParameter("paperNO");
		String pwd = request.getParameter("pwd");
		pwd = Md5Utils.md5(pwd);
		
		Reader reader = new Reader();
		int status = 1;
		try {
			reader = rservice.getReaderByPaperNoAndPwd(paperNo,pwd);
			if(reader == null){
				status = -1;
			}else if(!pwd.equals(reader.getPwd())){
				status = -2;
			}else{
				request.getSession().setAttribute("reader", reader);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			response.getWriter().print(status);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
		
	}
	
	@RequestMapping("/readerLoginAction_logout")
	public void logOut(HttpServletRequest request, HttpServletResponse response) throws IOException{
		request.getSession().invalidate();
		//重定向到读者的页面
		response.sendRedirect("login.jsp");
	}
}
