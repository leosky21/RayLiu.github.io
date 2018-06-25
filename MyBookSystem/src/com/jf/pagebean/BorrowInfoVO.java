package com.jf.pagebean;

import java.sql.Date;

//针对数据库多表连接查询的结果集
public class BorrowInfoVO {
	private Integer id;    //借阅编号
	private String ISBN;
	private String BookName;//图书名称
	private String paperNO;//读者证件号
	private String ReaderName; //读者名称
	private Date borrowDate;    //借阅日期
	private Date endDate;    //截止日期

	//针对view 层，其实就是把一些不容易理解的字段转成我们能看懂的
	//例如date，上面的date类型是为了方便从数据库取值
	//下面的date是为了在页面显示，互不影响
	private String bDate;//借阅格式化的字段
	private String eDate;//截止格式化的字段
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getISBN() {
		return ISBN;
	}
	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}
	
	public String getBookName() {
		return BookName;
	}
	public void setBookName(String bookName) {
		BookName = bookName;
	}
	public String getbDate() {
		return bDate;
	}
	public void setbDate(String bDate) {
		this.bDate = bDate;
	}
	public String geteDate() {
		return eDate;
	}
	public String getPaperNO() {
		return paperNO;
	}
	public void setPaperNO(String paperNO) {
		this.paperNO = paperNO;
	}
	public void seteDate(String eDate) {
		this.eDate = eDate;
	}
	
	public String getReaderName() {
		return ReaderName;
	}
	public void setReaderName(String readerName) {
		ReaderName = readerName;
	}
	public Date getBorrowDate() {
		return borrowDate;
	}
	public void setBorrowDate(Date borrowDate) {
		this.borrowDate = borrowDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
}
