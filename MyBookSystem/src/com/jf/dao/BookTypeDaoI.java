package com.jf.dao;

import java.sql.SQLException;

import com.jf.model.BookType;
import com.jf.model.PageBean;

public interface BookTypeDaoI {

	public PageBean<BookType> pageSearch(BookType bt, int pageCode, int pageSize)  throws SQLException;

	public BookType getBookTypeByName(String typeName) throws SQLException;

	public int addTypeName(String typeName) throws SQLException;

	public int deleteById(int id) throws SQLException;

	public BookType getBookTypeById(int id) throws SQLException;

	public int updateById(int id, String typeName) throws SQLException;

}
