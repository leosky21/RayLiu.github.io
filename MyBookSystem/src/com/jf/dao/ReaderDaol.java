package com.jf.dao;

import java.sql.SQLException;

import com.jf.model.Reader;

public interface ReaderDaol {

	public Reader getReader(String paperNo, String pwd) throws SQLException;

	public int UpdateReaderInfo(Reader newReader) throws SQLException;

	public int UpdatePwd(String newPwd, Integer id) throws SQLException;

}
