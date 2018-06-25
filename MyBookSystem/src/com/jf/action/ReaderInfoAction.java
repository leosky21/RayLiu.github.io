package com.jf.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jf.model.Admin;
import com.jf.model.Reader;
import com.jf.myspringmvc.Controller;
import com.jf.myspringmvc.RequestMapping;
import com.jf.service.ReadServicel;
import com.jf.service.impl.ReaderServiceImpl;
import com.jf.utils.Md5Utils;

@Controller
public class ReaderInfoAction {
	
	private ReadServicel reService = new ReaderServiceImpl();
	
	@RequestMapping("/reader/readerInfoAction_readerInfo")
	public void updateReaderInfo(HttpServletRequest request,HttpServletResponse response){
		String name = request.getParameter("name");
		String phone = request.getParameter("phone");
		String email = request.getParameter("email");
		
		Reader reader = new Reader();
		reader = (Reader) request.getSession().getAttribute("reader");
		Integer id = reader.getId();
		Reader newReader = new Reader();
		newReader.setId(id);
		newReader.setName(name);
		newReader.setPhone(phone);
		newReader.setEmail(email);
		int num = reService.UpdateReaderInfo(newReader);
		int status = -1;
		if(num > 0){
			status = 1;
			reader.setName(name);
			reader.setPhone(phone);
			reader.setEmail(email);
			request.getSession().setAttribute("reader", reader);
		}
		try {
			response.getWriter().print(status);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping("/reader/readerInfoAction_readerPwd")
	public void ReaderUpdatePwd(HttpServletRequest request,HttpServletResponse response){
		String oldPwd = request.getParameter("oldPwd");
		String newPwd = request.getParameter("newPwd");
		String confirmPwd = request.getParameter("confirmPwd");
		oldPwd = Md5Utils.md5(oldPwd);
		newPwd = Md5Utils.md5(newPwd);
		confirmPwd = Md5Utils.md5(confirmPwd);
		Reader reader = (Reader) request.getSession().getAttribute("reader");
		int status = -2;
		if(!oldPwd.equals(reader.getPwd())){
			status = -1;
		}else if(!newPwd.equals(confirmPwd)){
			status = 0;
		}else{
			Integer id = reader.getId();
			int num = reService.UpdatePwd(newPwd,id);
			if(num > 0){
				status = 1;
				reader.setPwd(newPwd);
				request.getSession().setAttribute("reader", reader);
			}
		}
		try {
			response.getWriter().print(status);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
