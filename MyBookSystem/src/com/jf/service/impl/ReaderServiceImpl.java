package com.jf.service.impl;

import java.sql.SQLException;

import com.jf.dao.ReaderDaol;
import com.jf.dao.impl.ReaderDaoImpl;
import com.jf.model.Reader;
import com.jf.service.ReadServicel;
import com.jf.utils.JDBCTools;

public class ReaderServiceImpl implements ReadServicel {
	
	private ReaderDaol rdao = new ReaderDaoImpl();

	@Override
	public Reader getReaderByPaperNoAndPwd(String paperNo, String pwd) throws SQLException {
		Reader reader = new Reader();
		reader = rdao.getReader(paperNo,pwd);
		return reader;
	}
	
	@Override
	public int UpdateReaderInfo(Reader newReader) {
		try {
			JDBCTools.beginTransaction();
			int num = rdao.UpdateReaderInfo(newReader);
			JDBCTools.commitTransaction();
			return num;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	@Override
	public int UpdatePwd(String newPwd, Integer id) {
		try {
			JDBCTools.beginTransaction();
			int num = rdao.UpdatePwd(newPwd,id);
			JDBCTools.commitTransaction();
			return num;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

}
