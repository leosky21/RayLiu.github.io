package com.jf.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.jf.dao.BookTypeDaoI;
import com.jf.model.BookType;
import com.jf.model.PageBean;
import com.jf.utils.DataUtils;
import com.jf.utils.JDBCTools;

public class BookTypeDaoImpl implements BookTypeDaoI {

	@Override
	public PageBean<BookType> pageSearch(BookType bookType, int pageCode, int pageSize) throws SQLException {
		 PageBean<BookType> pb = new PageBean<BookType>();    //pageBean对象，用于分页
	        //根据传入的pageCode当前页码和pageSize页面记录数来设置pb对象
	        pb.setPageCode(pageCode);//设置当前页码
	        pb.setPageSize(pageSize);//设置页面记录数

	        StringBuilder and = new StringBuilder();

	        String sql_count = "SELECT count(*) FROM BookType b where 1=1";
	        String sql_data = "SELECT * FROM BookType b where 1=1";

	        if (bookType != null) {
	            if (DataUtils.isValid(bookType.getName())) {
	                and.append(" and b.name like '%" + bookType.getName() + "%'");
	            }
	        }
	        sql_count += and.toString();
	        sql_data += and.toString() + " limit ?,?";

	        System.out.println(sql_count);
	        System.out.println(sql_data);


	        QueryRunner qr = new QueryRunner(JDBCTools.getDataSource());

	        Object o = qr.query(sql_count, new ScalarHandler());
	        String ss = o.toString();
	        int record = Integer.parseInt(ss);
	        pb.setTotalRecord(record);    //设置总记录数

	        int start = (pageCode - 1) * pageSize;
//	        参数类型
//	        new Object[]{start, pageSize}
	        List<BookType> bookTypes = (List<BookType>) qr.query(sql_data, new BeanListHandler(BookType.class), start,pageSize);
	        if (bookTypes != null && bookTypes.size() > 0) {
	            pb.setBeanList(bookTypes);
	            return pb;
	        }
	        
	        return null;
	}
	
	@Override
	public BookType getBookTypeByName(String typeName) throws SQLException {
		
		QueryRunner qr = new QueryRunner(JDBCTools.getDataSource());
		String sql = "select * from booktype where name=?";
		return qr.query(sql, new BeanHandler<>(BookType.class),typeName);
		
	}
	
	@Override
	public int addTypeName(String typeName) throws SQLException {
		QueryRunner qr = new QueryRunner(JDBCTools.getDataSource());
		String sql = "insert into booktype values(null,?)";
		int num = qr.update(sql,typeName);
		return num;
	}
	
	@Override
	public int deleteById(int id) throws SQLException {
		QueryRunner qr = new QueryRunner(JDBCTools.getDataSource());
		String sql = "delete from booktype where id=?";
		int num = qr.update(sql,id);
		return num;
	}
	
	@Override
	public BookType getBookTypeById(int id) throws SQLException {
		QueryRunner qr = new QueryRunner(JDBCTools.getDataSource());
		String sql = "select * from booktype where id=?";
		return qr.query(sql, new BeanHandler<>(BookType.class),id);
	}
	
	@Override
	public int updateById(int id, String typeName) throws SQLException {
		QueryRunner qr = new QueryRunner();
		String sql = "update booktype set name=? where id=?";
		int num = qr.update(JDBCTools.getConnection(),sql,typeName,id);
		return num;
	}

}
