package com.jf.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 图书信息类
 * 
 * @author c
 *
 */
public class Book implements Serializable {
	private Integer id; // 图书编号
	private String ISBN;// ISBN 国际标准书号

	private String name; // 图书名称
	private String autho; // 作者名称
	private String press; // 出版社
	private Date putdate; // 上架日期
	private Integer num; // 总数量
	private Integer currentNum; // 在馆数量
	private Double price; // 价格
	private String description; // 简介

	private Integer fk_admin;//操作管理员id

	private Integer fk_booktype;//图书类型

	private BookType bookType; // 图书类型
	public Integer getFk_booktype() {
		return fk_booktype;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}



	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setFk_booktype(Integer fk_booktype) {
		this.fk_booktype = fk_booktype;
	}


	public String getISBN() {
		return ISBN;
	}

	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}

	public BookType getBookType() {
		return bookType;
	}

	public void setBookType(BookType bookType) {
		this.bookType = bookType;
	}


	public String getAutho() {
		return autho;
	}

	public void setAutho(String autho) {
		this.autho = autho;
	}

	public String getPress() {
		return press;
	}

	public void setPress(String press) {
		this.press = press;
	}

	public Date getPutdate() {
		return putdate;
	}

	public void setPutdate(Date putdate) {
		this.putdate = putdate;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public Integer getCurrentNum() {
		return currentNum;
	}

	public void setCurrentNum(Integer currentNum) {
		this.currentNum = currentNum;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Book() {
	}

	public Book(Integer id) {
		this.id = id;
	}

	public Integer getFk_admin() {
		return fk_admin;
	}

	public void setFk_admin(Integer fk_admin) {
		this.fk_admin = fk_admin;
	}

	@Override
	public String toString() {
		return "Book [id=" + id + ", ISBN=" + ISBN + ", name=" + name
				+ ", autho=" + autho + ", press=" + press + ", putdate="
				+ putdate + ", num=" + num + ", currentNum=" + currentNum
				+ ", price=" + price + ", description=" + description
				+ ", fk_admin=" + fk_admin + ", fk_booktype=" + fk_booktype
				+ ", bookType=" + bookType + "]";
	}
	
	
}
