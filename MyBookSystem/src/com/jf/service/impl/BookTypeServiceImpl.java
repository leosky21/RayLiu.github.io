package com.jf.service.impl;

import java.sql.SQLException;

import com.jf.dao.BookTypeDaoI;
import com.jf.dao.impl.BookTypeDaoImpl;
import com.jf.model.BookType;
import com.jf.model.PageBean;
import com.jf.service.BookTypeServiceI;
import com.jf.utils.JDBCTools;

public class BookTypeServiceImpl implements BookTypeServiceI {
	
	private BookTypeDaoI bookTypeDao = new BookTypeDaoImpl();
	@Override
	public PageBean<BookType> queryBookType(BookType bt, int pageCode,
			int pageSize) {
		try {
			return bookTypeDao.pageSearch(bt, pageCode, pageSize);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public BookType getBookTypeByName(String typeName) {
		BookType book;
		try {
			book = bookTypeDao.getBookTypeByName(typeName);
			return book;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	@Override
	public boolean addTypeName(String typeName) {
		try {
			int num = bookTypeDao.addTypeName(typeName);
			if(num > 0){
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	@Override
	public int deleteById(int id) {
		int num;
		try {
			num = bookTypeDao.deleteById(id);
			return num;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	@Override
	public BookType getBookTypeById(int id) {
		BookType book;
		try {
			book = bookTypeDao.getBookTypeById(id);
			return book;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public int updateById(int id, String typeName) {
		int num;
		//避免重名
		try {
			BookType book1 = bookTypeDao.getBookTypeByName(typeName);
			if(book1 != null){
				num = -1;//表示重名
			}else{
				JDBCTools.beginTransaction();
				num = bookTypeDao.updateById(id,typeName);
				JDBCTools.commitTransaction();
			}
			return num;
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		return 0;
	}
}
