package com.jf.dao.impl;

import java.math.BigInteger;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.jf.dao.BorrowDaoI;
import com.jf.model.Book;
import com.jf.model.BorrowInfo;
import com.jf.model.ForfeitInfo;
import com.jf.model.PageBean;
import com.jf.model.Reader;
import com.jf.model.ReaderType;
import com.jf.pagebean.BorrowInfoVO;
import com.jf.pagebean.BorrowShowInfo;
import com.jf.utils.JDBCTools;

public class BorrowDaoImpl implements BorrowDaoI {

	@Override
	public PageBean<BorrowInfoVO> getAllBorrowInfo(int pageCode, int pageSize)
			throws SQLException {
		String sql = "select bi.id,bi.borrowDate,bi.endDate,b.ISBN,b.name BookName,r.paperNO,r.name ReaderName "
				+ "from borrowinfo bi,book b,reader r where bi.fk_reader=r.id and bi.fk_book=b.id limit ?,?";
		QueryRunner qr = new QueryRunner(JDBCTools.getDataSource());
		List<BorrowInfoVO> list = qr.query(sql, new BeanListHandler<>(BorrowInfoVO.class),(pageCode-1)*pageSize,pageSize);
		//设置分页
		PageBean<BorrowInfoVO> pb = new PageBean<>();
		pb.setPageCode(pageCode);
		pb.setPageSize(pageSize);
		String sql_count = "select count(*) from borrowinfo bi,book b,reader r where bi.fk_reader=r.id and bi.fk_book=b.id";
		int totalRecord = Integer.parseInt(qr.query(sql_count, new ScalarHandler<>()).toString());
		pb.setTotalRecord(totalRecord);
		pb.setBeanList(list);
		return pb;
	}
	
	@Override
	public Book getBookByISBN(String iSBN) throws SQLException {
		QueryRunner qr = new QueryRunner(JDBCTools.getDataSource());
		String sql = "select * from book where ISBN=?";
		return qr.query(sql, new BeanHandler<>(Book.class),iSBN);
		
	}
	
	@Override
	public ReaderType getReaderByFK(int fk_readerType) throws SQLException {

		String sql = "select * from readertype where id=?";
		QueryRunner qr = new QueryRunner(JDBCTools.getDataSource());
		return qr.query(sql, new BeanHandler<>(ReaderType.class),fk_readerType);
	}
	
	@Override
	public int getAlreadyBorrowNum(Reader r) throws SQLException {
		QueryRunner qr = new QueryRunner(JDBCTools.getDataSource());
		//因为状态2和状态5为已归还状态，所以查询是要忽略
		String sql = " SELECT COUNT(*) FROM borrowinfo where fk_reader=? and state=0 or state=1 or state=3 or state=4";
		Object o = qr.query(sql, new ScalarHandler<>(),r.getId());
		String s = o.toString();
		return Integer.parseInt(s);
	}
	
	@Override
	public List<ForfeitInfo> getForfeitInfoById(Reader r) throws SQLException {
		QueryRunner qr = new QueryRunner(JDBCTools.getDataSource());
		String sql = "select f.* from forfeitinfo f,borrowinfo b where b.id=f.id and b.fk_reader=?";
		return qr.query(sql, new BeanListHandler<>(ForfeitInfo.class),r.getId());
	}
	
	@Override
	public int add(BorrowInfo borrowInfo) throws SQLException {
		QueryRunner qr = new QueryRunner();
		String sql = "insert into borrowinfo(id,borrowDate,endDate,overday,state,fk_reader,fk_book,penalty,fk_admin) "
				+ "values(null,?,?,?,?,?,?,?,?)";
		int count = qr.update(JDBCTools.getConnection(),sql,
				borrowInfo.getBorrowDate(),
				borrowInfo.getEndDate(),
				borrowInfo.getOverday(),
				borrowInfo.getState(),
				borrowInfo.getFk_reader(),
				borrowInfo.getFk_book(),
				borrowInfo.getPenalty(),
				borrowInfo.getFk_admin()
				);
		//因为后面的归还信息表与借阅表的id是一致的，但是添加后只能拿到一个count，也就是影响行数
		//并不是我们想要的id，但是要设置归还信息，就必须要借阅信息id，就需要拿到插入之后的最后一个id
		if(count > 0){
			BigInteger id = (BigInteger) qr.query(JDBCTools.getConnection(),"select LAST_INSERT_ID()",new ScalarHandler<>(1));
			return id.intValue();
		}
		return 0;
	}
	
	@Override
	public void update(Book book) throws SQLException {
		QueryRunner qr = new QueryRunner();
		String sql = "update book set currentNum =? where id=?";
		qr.update(JDBCTools.getConnection(),sql,book.getCurrentNum(),book.getId());
	}
	@Override
	public void addBackInfo(int id) throws SQLException {
		QueryRunner qr = new QueryRunner();
		String sql = "insert into backinfo(id,backDate,fk_admin) values(?,null,null)";
		qr.update(JDBCTools.getConnection(),sql,id);
	}
	
	
	@Override
	public BorrowShowInfo getBorrowShowInfo(int id) throws SQLException {
		QueryRunner qr = new QueryRunner(JDBCTools.getDataSource());
		String sql = "SELECT bi.id,b.ISBN,b.name bookName,bt.name bookType,r.paperNO,r.name readerName,rt.name readertype,bi.overday overday,ad.name adminName,bi.state "
				+ "FROM borrowinfo bi,admin ad,book b,reader r,bookType bt,readertype rt "
				+ "WHERE bi.fk_book=b.id AND bt.id=b.fk_booktype AND r.fk_readertype=rt.id AND bi.fk_admin=ad.id AND bi.fk_reader=r.id AND bi.fk_book=b.id AND bi.id=?";
		
		return qr.query(sql, new BeanHandler<>(BorrowShowInfo.class),id);
	}

}
