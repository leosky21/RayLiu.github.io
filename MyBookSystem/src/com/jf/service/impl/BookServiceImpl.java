package com.jf.service.impl;

import java.sql.SQLException;
import java.util.List;

import com.jf.dao.BookDaoI;
import com.jf.dao.impl.BookDaoImpl;
import com.jf.model.Admin;
import com.jf.model.Book;
import com.jf.model.BookType;
import com.jf.model.PageBean;
import com.jf.pagebean.BookQuery;
import com.jf.pagebean.BookVO;
import com.jf.service.BookServiceI;
import com.jf.utils.JDBCTools;

public class BookServiceImpl implements BookServiceI {
	private BookDaoI dao = new BookDaoImpl();
	@Override
	public PageBean<BookVO> queryBook(BookQuery bq, int pageCode, int pageSize) {
		PageBean<BookVO> pb;
		try {
			pb = dao.queryBook(bq,pageCode,pageSize);
			return pb;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public List<BookType> getAllBookType() {
		List<BookType> list;
		try {
			list = dao.getAllBookType();
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public int add(BookVO vo, String bookTypeId, int fk_admin) {
		int num=0;
		try {
			num  = dao.add(vo,bookTypeId,fk_admin);
			return num;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return 0;
	}
	
	@Override
	public Book getBookById(int parseInt) {
		Book book;
		try {
			book = dao.getBookById(parseInt);
			return book;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public int updateBook(Book book) {
		try {
			JDBCTools.beginTransaction();
			int num =dao.updateBook(book);
			JDBCTools.commitTransaction();
			return num;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	@Override
	public int updateBookNum(Book book) {
		Book oldBook = getBookById(book.getId());
		int num = oldBook.getNum();
		int currentNum = oldBook.getCurrentNum();
		int addNum = book.getNum();
		book.setNum(num+addNum);
		book.setCurrentNum(currentNum+addNum);
		
		try {
			JDBCTools.beginTransaction();
			int count = dao.updateBookNum(book);
			JDBCTools.commitTransaction();
			return count;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	@Override
	public BookVO getBookInfo(int parseInt) {
		Book book;
		try {
			book = dao.getBookById(parseInt);
			int typeid = book.getFk_booktype();
			List<BookType> list = getAllBookType();
			
			String bookType="";
			for (BookType bt : list) {
				if(bt.getId() == typeid){
					bookType = bt.getName();
				}
			}
			Admin admin = dao.getAdmin(book.getFk_admin());
			BookVO bookVo = new BookVO();
			bookVo.setAutho(book.getAutho());
			bookVo.setCurrentNum(book.getCurrentNum());
			bookVo.setDescription(book.getDescription());
			bookVo.setISBN(book.getISBN());
			bookVo.setName(book.getName());
			bookVo.setPress(book.getPress());
			bookVo.setPrice(book.getPrice().toString());
			bookVo.setPutdate(book.getPutdate().toString());
			bookVo.setId(book.getId());
			bookVo.setBookType(bookType);
			bookVo.setAdmin(admin.getName());
			bookVo.setNum(book.getNum());
			
			return bookVo;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
