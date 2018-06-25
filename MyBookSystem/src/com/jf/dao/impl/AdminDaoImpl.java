package com.jf.dao.impl;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import com.jf.dao.AdminDaoI;
import com.jf.model.Admin;
import com.jf.model.Authorization;
import com.jf.utils.JDBCTools;

public class AdminDaoImpl implements AdminDaoI {

	@Override
	public Admin getAdmin(String username, String pwd) throws SQLException {
		QueryRunner qr = new QueryRunner(JDBCTools.getDataSource());
		String sql = "select * from admin where username=? and password=?";
		return qr.query(sql, new BeanHandler<>(Admin.class),username,pwd);
	}
	
	@Override
	public Authorization getAuthorization(Integer aid) throws SQLException {
		QueryRunner qr = new QueryRunner(JDBCTools.getDataSource());
		String sql = "select * from authorization where aid=?";
		return qr.query(sql, new BeanHandler<>(Authorization.class),aid);
		
	}

	@Override
	public int updateAdminInfo(Integer id, String name, String phone)
			throws SQLException {
		QueryRunner qr = new QueryRunner();
		String sql = "update admin set name =? , phone =? where id =?";
		return qr.update(JDBCTools.getConnection(),sql,name,phone,id);
	}
	
	@Override
	public int UpdatePwd(String newPwd, Integer id) throws SQLException {
		QueryRunner qr = new QueryRunner();
		String sql = "update admin set password =? where id =?";
		return qr.update(JDBCTools.getConnection(),sql,newPwd,id);
	}

}
