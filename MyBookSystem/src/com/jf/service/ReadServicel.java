package com.jf.service;

import java.sql.SQLException;

import com.jf.model.Reader;

public interface ReadServicel {

	public Reader getReaderByPaperNoAndPwd(String paperNo, String pwd) throws SQLException;

	public int UpdateReaderInfo(Reader newReader);

	public int UpdatePwd(String newPwd, Integer id);
	
}
