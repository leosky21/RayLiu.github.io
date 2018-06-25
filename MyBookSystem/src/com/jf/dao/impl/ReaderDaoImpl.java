package com.jf.dao.impl;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import com.jf.dao.ReaderDaol;
import com.jf.model.Reader;
import com.jf.utils.JDBCTools;

public class ReaderDaoImpl implements ReaderDaol {

	@Override
	public Reader getReader(String paperNo, String pwd) throws SQLException {
		
		QueryRunner qr = new QueryRunner(JDBCTools.getDataSource());
		String sql = "select * from reader where paperNO=? and pwd=?";
		return qr.query(sql, new BeanHandler<Reader>(Reader.class),paperNo,pwd);
	}
	
	@Override
	public int UpdateReaderInfo(Reader newReader) throws SQLException {
		QueryRunner qr = new QueryRunner();
		String sql = "update reader set name=? ,phone=? ,email=? where id=?";
		return qr.update(JDBCTools.getConnection(),sql,newReader.getName(),newReader.getPhone(),newReader.getEmail(),newReader.getId());
	}
	
	@Override
	public int UpdatePwd(String newPwd, Integer id) throws SQLException {
		QueryRunner qr = new QueryRunner();
		String sql = "update reader set pwd=? where id=?";
		return qr.update(JDBCTools.getConnection(),sql,newPwd,id);
	}
}
