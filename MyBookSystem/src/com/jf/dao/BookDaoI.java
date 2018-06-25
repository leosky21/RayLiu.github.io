package com.jf.dao;

import java.sql.SQLException;
import java.util.List;

import com.jf.model.Admin;
import com.jf.model.Book;
import com.jf.model.BookType;
import com.jf.model.PageBean;
import com.jf.pagebean.BookQuery;
import com.jf.pagebean.BookVO;

public interface BookDaoI {

	public PageBean<BookVO> queryBook(BookQuery bq, int pageCode, int pageSize) throws SQLException ;

	public List<BookType> getAllBookType() throws SQLException;

	public int add(BookVO vo, String bookTypeId, int fk_admin) throws SQLException;

	public Book getBookById(int parseInt) throws SQLException;

	public int updateBook(Book book) throws SQLException;

	public int updateBookNum(Book book) throws SQLException;

	public Admin getAdmin(Integer fk_admin) throws SQLException;

}
