package com.jf.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import com.jf.model.Admin;
import com.jf.model.PageBean;
import com.jf.model.Reader;
import com.jf.myspringmvc.Controller;
import com.jf.myspringmvc.RequestMapping;
import com.jf.pagebean.BorrowInfoVO;
import com.jf.pagebean.BorrowShowInfo;
import com.jf.service.BorrowServiceI;
import com.jf.service.impl.BorrowServiceImpl;
import com.jf.utils.DataUtils;
import com.jf.utils.Md5Utils;

@Controller
public class borrowManageAction {
	private BorrowServiceI service = new BorrowServiceImpl();
	@RequestMapping("/admin/borrowManageAction_list")
	public void list(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		int pageCode = DataUtils.getPageCode(request.getParameter("pageCode"));
		int pageSize = 5;
		PageBean<BorrowInfoVO> pb = service.dataGrid(pageCode,pageSize);
		request.setAttribute("pb", pb);
		pb.getBeanList();
		request.getRequestDispatcher("/WEB-INF/admin/borrowManage.jsp").forward(request, response);
	}
	
	@RequestMapping("/admin/borrowManageAction_borrowBook")
	public void borrow(HttpServletRequest request,HttpServletResponse response) throws IOException{
		//在servlet里面不做逻辑判断，把逻辑判断放到service层
		String paperNO = request.getParameter("paperNO");
		String ISBN = request.getParameter("ISBN");
		String pwd = request.getParameter("pwd");
		//被忘了这里的密码都是被MD5加密过的，所以要把密码重新加密一次
		pwd = Md5Utils.md5(pwd);
		//封装属性
		Reader reader = new Reader();
		reader.setPwd(pwd);
		reader.setPaperNO(paperNO);
		Admin admin = (Admin) request.getSession().getAttribute("admin");
		if(admin != null){
			//取出管理员id
			Integer aid = admin.getId();
			//开始去service操作逻辑
			int code = service.addBorrow(reader,aid,ISBN);
			response.getWriter().print(code);
		}
	}
	
	@RequestMapping("/admin/borrowManageAction_getBorrowInfoById")
	public void getBorrowInfoById(HttpServletRequest request,HttpServletResponse response){
		String borrowId = request.getParameter("borrowId");
		int id = Integer.parseInt(borrowId);
		BorrowShowInfo bsi = new BorrowShowInfo();
		bsi = service.getBorrowShowInfo(id);
		JSONObject json1 = new JSONObject().fromObject(bsi);
		String json = json1.toString();
		System.out.println(json);
		try {
			response.getWriter().print(json);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	
	
	
	
	
}
