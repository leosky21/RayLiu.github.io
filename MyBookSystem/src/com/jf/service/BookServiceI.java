package com.jf.service;

import java.util.List;

import com.jf.model.Book;
import com.jf.model.BookType;
import com.jf.model.PageBean;
import com.jf.pagebean.BookQuery;
import com.jf.pagebean.BookVO;

public interface BookServiceI {

	public PageBean<BookVO> queryBook(BookQuery bq, int pageCode, int pageSize);

	public List<BookType> getAllBookType();

	public int add(BookVO vo, String bookTypeId, int fk_admin);

	public Book getBookById(int parseInt);

	public int updateBook(Book book);

	public int updateBookNum(Book book);

	public BookVO getBookInfo(int parseInt);

}
