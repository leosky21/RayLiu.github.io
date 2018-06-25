package com.jf.dao.impl;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.logging.SimpleFormatter;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.jf.dao.BookDaoI;
import com.jf.model.Admin;
import com.jf.model.Book;
import com.jf.model.BookType;
import com.jf.model.PageBean;
import com.jf.pagebean.BookQuery;
import com.jf.pagebean.BookVO;
import com.jf.utils.DataUtils;
import com.jf.utils.JDBCTools;

public class BookDaoImpl implements BookDaoI {

	@Override
	public PageBean<BookVO> queryBook(BookQuery bq, int pageCode, int pageSize) throws SQLException {
		
		String query = "";
		if(bq != null){
			if(DataUtils.isValid(bq.getISBN())){
				query += " and b.ISBN like'%"+bq.getISBN()+"%' ";
				//注意不能写成like'% "+bq.getISBN()+" %'这样，%%之间不能加空格
			}
			if(DataUtils.isNumber(bq.getBookTypeId())){
				query += " and b.fk_booktype like'%"+bq.getBookTypeId()+"%' ";
			}
			if(DataUtils.isValid(bq.getBookName())){
				query += " and b.name like'%"+bq.getBookName()+"%' ";
			}
			if(DataUtils.isValid(bq.getAutho())){
				query += " and b.autho like'%"+bq.getAutho()+"%' ";
			}
			if(DataUtils.isValid(bq.getPress())){
				query += " and b.press like'%"+bq.getPress()+"%' ";
			}
		}
		
		String sql_count = "select count(*) from book b where 1=1";
		String sql_data = "select b.* from book b,booktype bt where b.fk_booktype=bt.id ";
		
		sql_count += query;
		sql_data += query+" limit ?,?";
		QueryRunner qr = new QueryRunner(JDBCTools.getDataSource());
		Object o = qr.query(sql_count, new ScalarHandler());
		int allCount = Integer.parseInt(o.toString());
		PageBean<BookVO> pb = new PageBean<>();
		pb.setPageCode(pageCode);
		pb.setPageSize(pageSize);
		pb.setTotalRecord(allCount);
		
		System.out.println("----------");
		List<Book> list = qr.query(sql_data, new BeanListHandler<>(Book.class),(pageCode-1)*pageSize,pageSize);
//		System.out.println(list.get(0).toString());
		System.out.println(sql_data);
		
		List<BookVO> vos = new ArrayList<>();
		for (Book book : list) {
			BookVO vo = new BookVO();
			vo.setId(book.getId());
			vo.setAutho(book.getAutho());
			int id = book.getFk_booktype();
			String typename = qr.query("select * from booktype where id=?", new BeanHandler<>(BookType.class),id).getName();
			vo.setBookType(typename);
			vo.setCurrentNum(book.getCurrentNum());
			vo.setDescription(book.getDescription());
			vo.setISBN(book.getISBN());
			vo.setName(book.getName());
			vo.setNum(book.getNum());
			vo.setPress(book.getPress());
			vo.setPrice(book.getPrice()+"");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
			vo.setPutdate(sdf.format(book.getPutdate()));
			vos.add(vo);
		}
		pb.setBeanList(vos);
		
		return pb;
	}
	
	@Override
	public List<BookType> getAllBookType() throws SQLException {
		QueryRunner qr = new QueryRunner(JDBCTools.getDataSource());
		String sql = "select * from booktype";
		List<BookType> book = qr.query(sql, new BeanListHandler<>(BookType.class));
		return book;
	}
	
	@Override
	public int add(BookVO vo, String bookTypeId, int fk_admin)
			throws SQLException {
		String sql = " insert into book (id, name, ISBN, autho, num, currentNum, press, description, price, putdate, fk_booktype, fk_admin) values(null,?,?,?,?,?,?,?,?,?,?,?)";
		QueryRunner qr = new QueryRunner();
		System.out.println("---"+vo.getName());
		int num = qr.update(JDBCTools.getConnection(),sql,
				vo.getName(),
				vo.getISBN(),
				vo.getAutho(),
				vo.getNum(),
				vo.getNum(),
				vo.getPress(),
				vo.getDescription(),
				vo.getPrice(),
				new Date(),
				bookTypeId,
				fk_admin
				);
		return num;
	}
	
	@Override
	public Book getBookById(int parseInt) throws SQLException {
		QueryRunner qr = new QueryRunner(JDBCTools.getDataSource());
		String sql = "select * from book where id=?";
		return qr.query(sql, new BeanHandler<>(Book.class),parseInt);
	}
	
	@Override
	public int updateBook(Book book) throws SQLException {
		QueryRunner qr = new QueryRunner();
		String sql = "update book set ISBN=?,name=?,fk_booktype=?,autho=?,press=?,price=?,description=? where id=?";
		int num = qr.update(JDBCTools.getConnection(),sql,
				book.getISBN(),
				book.getName(),
				book.getFk_booktype(),
				book.getAutho(),
				book.getPress(),
				book.getPrice(),
				book.getDescription(),
				book.getId()
				);
		return num;
	}
	
	@Override
	public int updateBookNum(Book book) throws SQLException {
		QueryRunner qr = new QueryRunner();
		String sql = "update book set num=?, currentNum=? where id=?";
		int count = qr.update(JDBCTools.getConnection(),sql,book.getNum(),book.getCurrentNum(),book.getId());
		return count;
	}
	
	@Override
	public Admin getAdmin(Integer fk_admin) throws SQLException {
		QueryRunner qr = new QueryRunner(JDBCTools.getDataSource());
		String sql = "select * from admin where id=?";
		return qr.query(sql, new BeanHandler<>(Admin.class),fk_admin);
	}
}
