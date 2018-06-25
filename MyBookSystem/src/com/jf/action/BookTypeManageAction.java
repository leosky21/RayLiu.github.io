package com.jf.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import com.jf.model.BookType;
import com.jf.model.PageBean;
import com.jf.myspringmvc.Controller;
import com.jf.myspringmvc.RequestMapping;
import com.jf.service.BookTypeServiceI;
import com.jf.service.impl.BookTypeServiceImpl;
import com.jf.utils.DataUtils;

@Controller
public class BookTypeManageAction {
	
	private BookTypeServiceI btService = new BookTypeServiceImpl();
	
	@RequestMapping("/admin/bookTypeManageAction_list")
	public void BookList(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		
		String typeName = request.getParameter("typeName");
		//获取页面传递过来的当前页码数
        int pageCode = DataUtils.getPageCode(request.getParameter("pageCode"));
        //给pageSize,每页的记录数赋值
        int pageSize = 5;
        BookType bt = new BookType();
        bt.setName(typeName);
        PageBean<BookType> pb = btService.queryBookType(bt,pageCode,pageSize);
        
      //存入request域中
        request.setAttribute("pb", pb);
        request.getRequestDispatcher("/WEB-INF/admin/bookTypeManage.jsp").forward(request,response);
	}
	
	@RequestMapping("/admin/bookTypeManageAction_addBookType")
	public void add(HttpServletRequest request,HttpServletResponse response){
		String typeName = request.getParameter("typeName");
		System.out.println(typeName);
		BookType booktype = btService.getBookTypeByName(typeName);
		int status = -2;
		//如果没有booktype,说明没有重复的类型,可以添加
		if(booktype != null){
			status = -1;
		}else{
			boolean bool = btService.addTypeName(typeName);
			if(bool){
				status = 1;
			}else{
				status = 0;
			}
		}
		
		try {
			response.getWriter().print(status);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping("/admin/bookTypeManageAction_deleteBookType")
	public void delete(HttpServletRequest request,HttpServletResponse response){
		String id_ = request.getParameter("id");
		int id = Integer.parseInt(id_);
		int num = btService.deleteById(id);
		int status = -1;
		if(num > 0){
			status = 1;
		}
		
		try {
			response.getWriter().print(status);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	@RequestMapping("/admin/bookTypeManageAction_getBookType")
	public void getBookType(HttpServletRequest request,HttpServletResponse response) throws IOException{
		String id_ = request.getParameter("id");
		int id = Integer.parseInt(id_);
		BookType book = btService.getBookTypeById(id);
		
		//把它转换成json
		JSONObject jsonObject = JSONObject.fromObject(book);
		response.getWriter().print(jsonObject);
	}
	
	@RequestMapping("/admin/bookTypeManageAction_updateBookType")
	public void Update(HttpServletRequest request,HttpServletResponse response){
		String id_ = request.getParameter("id");
		int id = Integer.parseInt(id_);
		String typeName = request.getParameter("typeName");
		int num = btService.updateById(id,typeName);
		int status = -3;
		if(num == -1){
			status = -1;
		}else if(num > 0){
			status = 1;
		}
		
		try {
			response.getWriter().print(status);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
