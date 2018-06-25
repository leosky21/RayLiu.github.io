package com.jf.model;

import java.io.Serializable;

/**
 * 罚金信息类
 * @author c
 *
 */
public class ForfeitInfo implements Serializable{
	
	private Integer id;	//借阅编号

	private Double forfeit;	//罚金金额
	private int isPay;	//是否已经支付罚金
	private Integer aid;//操作的管理员



	private BorrowInfo borrowInfo;
	private Admin admin;	//操作员


	public Integer getAid() {
		return aid;
	}

	public void setAid(Integer aid) {
		this.aid = aid;
	}


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public BorrowInfo getBorrowInfo() {
		return borrowInfo;
	}




	public void setBorrowInfo(BorrowInfo borrowInfo) {
		this.borrowInfo = borrowInfo;
	}




	public Admin getAdmin() {
		return admin;
	}




	public void setAdmin(Admin admin) {
		this.admin = admin;
	}




	public Double getForfeit() {
		return forfeit;
	}




	public void setForfeit(Double forfeit) {
		this.forfeit = forfeit;
	}




	public int getIsPay() {
		return isPay;
	}




	public void setIsPay(int isPay) {
		this.isPay = isPay;
	}




	public ForfeitInfo() {
		
	}





	
	
}
