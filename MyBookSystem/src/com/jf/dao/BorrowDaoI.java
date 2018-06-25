package com.jf.dao;

import java.sql.SQLException;
import java.util.List;

import com.jf.model.Book;
import com.jf.model.BorrowInfo;
import com.jf.model.ForfeitInfo;
import com.jf.model.PageBean;
import com.jf.model.Reader;
import com.jf.model.ReaderType;
import com.jf.pagebean.BorrowInfoVO;
import com.jf.pagebean.BorrowShowInfo;

public interface BorrowDaoI {

	public PageBean<BorrowInfoVO> getAllBorrowInfo(int pageCode, int pageSize) throws SQLException;

	public Book getBookByISBN(String iSBN) throws SQLException;

	public ReaderType getReaderByFK(int fk_readerType)  throws SQLException;

	public int getAlreadyBorrowNum(Reader r) throws SQLException;

	public List<ForfeitInfo> getForfeitInfoById(Reader r) throws SQLException;

	public int add(BorrowInfo borrowInfo) throws SQLException;

	public void update(Book book) throws SQLException;

	public void addBackInfo(int id) throws SQLException;

	public BorrowShowInfo getBorrowShowInfo(int id) throws SQLException;

	


}
