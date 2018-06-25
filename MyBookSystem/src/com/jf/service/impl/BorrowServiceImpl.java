package com.jf.service.impl;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.jf.dao.BorrowDaoI;
import com.jf.dao.ReaderDaol;
import com.jf.dao.impl.BorrowDaoImpl;
import com.jf.dao.impl.ReaderDaoImpl;
import com.jf.model.Book;
import com.jf.model.BorrowInfo;
import com.jf.model.ForfeitInfo;
import com.jf.model.PageBean;
import com.jf.model.Reader;
import com.jf.model.ReaderType;
import com.jf.pagebean.BorrowInfoVO;
import com.jf.pagebean.BorrowShowInfo;
import com.jf.service.BorrowServiceI;
import com.jf.utils.JDBCTools;
/**
 * 
 * @author fandezhi
 *
 */
public class BorrowServiceImpl implements BorrowServiceI {
	private BorrowDaoI borrowDao = new BorrowDaoImpl();
	private ReaderDaol rdao = new ReaderDaoImpl();
	@Override
	public PageBean<BorrowInfoVO> dataGrid(int pageCode, int pageSize) {
		PageBean<BorrowInfoVO> pb = null;
		try {
			
			pb = borrowDao.getAllBorrowInfo(pageCode,pageSize);
			return pb;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public int addBorrow(Reader reader, Integer aid, String iSBN) {
		/**
		 * 1.先得到借阅的读者
		 * 2.读者输入的借阅密码是否匹配
		 * 	2.1如果不匹配，提示密码错误
		 * 	2.2如果匹配则继续执行
		 * 3.看读者的借阅数量
		 * 	3.1查询读者的读者类型，查到读者的最大借阅数量
		 * 	3.2查询读者已经借阅的多少本书
		 * 		3.2.1如果没有达到最大的借阅数量，继续下一步
		 * 		3.2.1如果已经达到了，甚至超了，就不能借阅，返回读者借阅数量已超过
		 * 4.看读者是否有罚金未缴纳
		 * 	4.1查询罚款信息表，如果查到用户还有罚金未缴纳，则不给借书，返回缴纳未缴纳的罚金
		 * 	4.2如果没有查到，则继续下一步
		 * 5.查看被借阅的书籍
		 * 	5.1如果别借阅的书籍的在馆数量大于1，则继续下一步
		 * 	5.2如果被借阅的书籍的在馆数量小于等于1，无法借阅，返回必须在馆内浏览
		 * 6.处理借阅信息
		 * 	6.1得到该读者的最大借阅天数和每日罚金
		 * 		6.1.1获得当前时间
		 * 		6.1.2根据最大借阅天数计算出截止天数
		 * 		6.1.3为借阅信息设置每日的罚金金额
		 * 	6.2获取读者信息，为 借阅信息设置读者信息
		 * 	6.3获取读书信息，为借阅信息设置读书信息
		 * 	6.4获取管理员信息，为借阅信息设置管理员信息
		 * 7.存储借阅信息
		 * 8.需要把该书籍的在馆数量减一
		 * 9.生成归还记录
		 */
		//现获取读者的用户名和密码
		try {
			JDBCTools.beginTransaction();
			Reader r = rdao.getReader(reader.getPaperNO(), reader.getPwd());
			if(r == null){
				return -1;
			}
			//看读书的编号是或否有误
			Book book = borrowDao.getBookByISBN(iSBN);
			if(book == null){
				return 3;
			}
			
			//得到读者类型的外键
			int fk_readerType = r.getFk_readerType();
			//查询读者类型
			ReaderType readerType = borrowDao.getReaderByFK(fk_readerType);
			//得到读者的最大借阅数量
			int maxNum = readerType.getMaxNum();
			int alreadyBorrowNum = borrowDao.getAlreadyBorrowNum(r);
			//判断读者借阅数量是否大于最大借阅数量
			if(alreadyBorrowNum >= maxNum){
				return -2;
			}
			//判断是否有未缴纳罚金
			//优化sql select count(*) from forfeitinfo f,borrowinfo b where b.id=f.id and b.fk_reader=? and isPay=0;
			List<ForfeitInfo> list = borrowDao.getForfeitInfoById(r);
			for (ForfeitInfo f : list) {
				if(f.getIsPay() == 0 ){ //IsPay是否缴纳罚金
					return -3;
				}
			}
			//查询书籍在馆数量是否大于1
			if(book.getCurrentNum() <= 1){
				return -4;
			}
			
			//处理借阅信息
			//得到读者的最大借阅天数和每日罚金
			int maxDay = readerType.getBday();//最大借阅天数
			Double penalty = readerType.getPenalty();//每日罚金
			//设置截止日期
			//现获取当前日期，然后加上最大借阅日期，得到截止日期
			Date thisDate = new Date();
			//截止日期的计算
//			Long l =
			Calendar c = Calendar.getInstance();
			c.setTime(thisDate);
			c.add(Calendar.DAY_OF_MONTH, +maxDay);
			//截止日期
			Date endDate = c.getTime();
			//封装借阅信息
			BorrowInfo borrowInfo = new BorrowInfo();
			borrowInfo.setFk_reader(r.getId());
			borrowInfo.setFk_admin(aid);
			borrowInfo.setFk_book(book.getId());
			
			borrowInfo.setBorrowDate(thisDate);
			borrowInfo.setEndDate(endDate);
			//设置罚款信息
			borrowInfo.setPenalty(penalty);
			//设置初始化默认值
			borrowInfo.setOverday(0);
			borrowInfo.setState(0);
			//开始添加
			int id = borrowDao.add(borrowInfo);
			if(id != 0 ){
				//把图书的在馆数量-1
				book.setCurrentNum(book.getCurrentNum()-1);
				borrowDao.update(book);
				//设置归还还信息
				borrowDao.addBackInfo(id);
				JDBCTools.commitTransaction();
				return 1;

			}
			JDBCTools.commitTransaction();
	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	
	@Override
	public BorrowShowInfo getBorrowShowInfo(int id) {
		BorrowShowInfo bsi;
		try {
			bsi = borrowDao.getBorrowShowInfo(id);
			return bsi;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}

}
