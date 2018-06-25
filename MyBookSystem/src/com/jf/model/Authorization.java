package com.jf.model;

import java.io.Serializable;

/**
 * 对应数据库中的权限表Authorization
 */
public class Authorization implements Serializable{
	private Integer id;	//管理员id
	private Integer sysSet;	//系统设置权限
	private Integer readerSet;	//读者设置权限
	private Integer bookSet;	//图书设置权限
	private Integer typeSet;	//图书分类设置权限
	private Integer borrowSet;	//借阅设置权限
	private Integer backSet;	//归还设置权限
	private Integer forfeitSet;	//逾期设置权限
	private Integer superSet;	//超级管理权限

	public Authorization(Integer id) {
		this.id = id;
	}

	public Authorization(Integer id, Integer sysSet, Integer readerSet, Integer bookSet, Integer typeSet,
						 Integer borrowSet, Integer backSet, Integer forfeitSet, Integer superSet) {
		this.id = id;
		this.sysSet = sysSet;
		this.readerSet = readerSet;
		this.bookSet = bookSet;
		this.typeSet = typeSet;
		this.borrowSet = borrowSet;
		this.backSet = backSet;
		this.forfeitSet = forfeitSet;
		this.superSet = superSet;
	}

	public Integer getId() { return id; }
	public void setId(Integer id) { this.id = id; }
	public Integer getForfeitSet() {
		return forfeitSet;
	}
	public void setForfeitSet(Integer forfeitSet) {
		this.forfeitSet = forfeitSet;
	}
	public Integer getSysSet() {
		return sysSet;
	}
	public void setSysSet(Integer sysSet) {
		this.sysSet = sysSet;
	}
	public Integer getReaderSet() {
		return readerSet;
	}
	public void setReaderSet(Integer readerSet) {
		this.readerSet = readerSet;
	}
	public Integer getBookSet() {
		return bookSet;
	}
	public void setBookSet(Integer bookSet) {
		this.bookSet = bookSet;
	}
	public Integer getTypeSet() {
		return typeSet;
	}
	public void setTypeSet(Integer typeSet) {
		this.typeSet = typeSet;
	}
	public Integer getBorrowSet() {
		return borrowSet;
	}
	public void setBorrowSet(Integer borrowSet) {
		this.borrowSet = borrowSet;
	}
	public Integer getBackSet() {
		return backSet;
	}
	public void setBackSet(Integer backSet) {
		this.backSet = backSet;
	}
	public Integer getSuperSet() {
		return superSet;
	}
	public void setSuperSet(Integer superSet) {
		this.superSet = superSet;
	}
	public Authorization() {
	}
}