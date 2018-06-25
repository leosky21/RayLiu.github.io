package com.jf.action;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.beanutils.BeanUtils;

import com.jf.model.Admin;
import com.jf.model.Book;
import com.jf.model.BookType;
import com.jf.model.PageBean;
import com.jf.myspringmvc.Controller;
import com.jf.myspringmvc.RequestMapping;
import com.jf.pagebean.BookQuery;
import com.jf.pagebean.BookVO;
import com.jf.service.BookServiceI;
import com.jf.service.impl.BookServiceImpl;
import com.jf.utils.DataUtils;

@Controller
public class BookAction {
	private BookServiceI service = new BookServiceImpl();
	
	@RequestMapping("/admin/bookManageAction_list")
	public void dataGrid(HttpServletRequest request,HttpServletResponse response) throws IllegalAccessException, InvocationTargetException, ServletException, IOException{
		
		//获取分页信息
		int pageCode = DataUtils.getPageCode(request.getParameter("pageCode"));
		//手动设置每页条数
		int pageSize = 5;
		//分装搜索条件
		Map<String, String[]> parame = request.getParameterMap();
		
		
		//创建书籍的查询类
		BookQuery bq = new BookQuery();
		
		//封装
		BeanUtils.copyProperties(bq, parame);
		
		
		//调用service层方法，获取所有图书的VO类
		
		PageBean<BookVO> pb = service.queryBook(bq,pageCode,pageSize);
		
		
		
		request.setAttribute("pb", pb);
		request.setAttribute("query", bq);
		System.out.println(bq.getPress());
		request.getRequestDispatcher("/WEB-INF/admin/bookManage.jsp").forward(request, response);
	}
	
	@RequestMapping("/admin/bookManageAction_getAllBookTypes")
	public void getAllBookTypes(HttpServletRequest request,HttpServletResponse response) throws UnsupportedEncodingException{
		
		List<BookType> list = service.getAllBookType();
		String json = JSONArray.fromObject(list).toString();
		try {
			response.getWriter().print(json);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
				
	}
	
	@RequestMapping("/admin/bookManageAction_addBook")
	public void addBook(HttpServletRequest request,HttpServletResponse response) throws IllegalAccessException, InvocationTargetException, IOException{
		
		Map<String, String[]> parame = request.getParameterMap();
		BookVO vo = new BookVO();
		vo.setName(request.getParameter("bookName"));
		BeanUtils.copyProperties(vo, parame);
		String bookTypeId = request.getParameter("bookTypeId");
		
		Admin admin = (Admin) request.getSession().getAttribute("admin");
		int fk_admin = admin.getId();
		System.out.println("开始执行add方法");
		int num = service.add(vo,bookTypeId,fk_admin);
		
		int status = -1;
		if(num>0){
//			System.out.println("添加成功");
			status = 1;
			
		}
		
		response.getWriter().print(status);
			

	}
	
	@RequestMapping("/bookManageAction_getBook")
	public void getBookById(HttpServletRequest request,HttpServletResponse response) throws IOException{
		String bookId = request.getParameter("bookId");
		Book book = service.getBookById(Integer.parseInt(bookId));
		
		String json = JSONArray.fromObject(book).toString();
		json = json.substring(1, json.length()-1);
//		System.out.println(json);
		response.getWriter().print(json);
	}
	
	@RequestMapping("/bookManageAction_getBookInfo")
	public void getBookInfo(HttpServletRequest request,HttpServletResponse response) throws IOException{
		String bookId = request.getParameter("bookId");
		BookVO book = service.getBookInfo(Integer.parseInt(bookId));
		
		String json = JSONArray.fromObject(book).toString();
		json = json.substring(1, json.length()-1);
		System.out.println(json);
		response.getWriter().print(json);
	}
	
	@RequestMapping("/admin/bookManageAction_updateBook")
	public void UpdateBoook(HttpServletRequest request,HttpServletResponse response){
		int bookId = Integer.parseInt(request.getParameter("bookId"));
		String bookName = request.getParameter("bookName");
		int bookTypeId = Integer.parseInt(request.getParameter("bookTypeId"));
		String autho = request.getParameter("autho");
		String press = request.getParameter("press");
		double price = Double.parseDouble(request.getParameter("price"));
		String description = request.getParameter("description");
		String ISBN = request.getParameter("ISBN");
		Book book = new Book();
		book.setId(bookId);
		book.setISBN(ISBN);
		book.setName(bookName);
		book.setFk_booktype(bookTypeId);
		book.setAutho(autho);
		book.setPress(press);
		book.setPrice(price);
		book.setDescription(description);
		int num = service.updateBook(book);
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
	
	@RequestMapping("/admin/bookManageAction_addBookNum")
    public String addBookNum(HttpServletRequest request, HttpServletResponse response) {
        String bookId = request.getParameter("bookId");
        String num = request.getParameter("num");
        Book book = new Book();
        book.setNum(Integer.parseInt(num));
        book.setId(Integer.parseInt(bookId));
        int newBook = service.updateBookNum(book);
        int success = 0;
        if (newBook > 0) {
            success = 1;
            //由于是转发并且js页面刷新,所以无需重查
        }
        try {
            response.getWriter().print(success);
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
        return null;
    }
}
