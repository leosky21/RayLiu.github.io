package com.jf.service;

import com.jf.model.BookType;
import com.jf.model.PageBean;

public interface BookTypeServiceI {

	public PageBean<BookType> queryBookType(BookType bt, int pageCode, int pageSize);

	public BookType getBookTypeByName(String typeName);

	public boolean addTypeName(String typeName);

	public int deleteById(int id);

	public BookType getBookTypeById(int id);

	public int updateById(int id, String typeName);

}
