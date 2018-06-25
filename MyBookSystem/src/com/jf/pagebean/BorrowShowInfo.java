package com.jf.pagebean;

import java.util.Date;

import com.jf.model.Admin;
import com.jf.model.Book;
import com.jf.model.Reader;

public class BorrowShowInfo {
	private Integer id;    //借阅编号
    private String ISBN;    //借阅书籍ISBN
    private String bookName;    //借阅书籍名称
    private String bookType;    //借阅书籍类型
    private String paperNO;    //读者证件号
    private String readerName;    //读者名称
    private String readertype;    //读者类型
    private Integer overday;    //逾期天数
    private String adminName;
    private Integer state; //状态 (未归还=0,逾期未归还=1,归还=2,续借未归还=3,续借逾期未归还=4,续借归还=5)
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
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public String getBookType() {
		return bookType;
	}
	public void setBookType(String bookType) {
		this.bookType = bookType;
	}
	public String getPaperNO() {
		return paperNO;
	}
	public void setPaperNO(String paperNO) {
		this.paperNO = paperNO;
	}
	public String getReaderName() {
		return readerName;
	}
	public void setReaderName(String readerName) {
		this.readerName = readerName;
	}
	public String getReadertype() {
		return readertype;
	}
	public void setReadertype(String readertype) {
		this.readertype = readertype;
	}
	public Integer getOverday() {
		return overday;
	}
	public void setOverday(Integer overday) {
		this.overday = overday;
	}
	public String getAdminName() {
		return adminName;
	}
	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
    
    
}
