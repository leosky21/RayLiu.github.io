package com.jf.service;

import com.jf.model.BorrowInfo;
import com.jf.model.PageBean;
import com.jf.model.Reader;
import com.jf.pagebean.BorrowInfoVO;
import com.jf.pagebean.BorrowShowInfo;

public interface BorrowServiceI {

	public PageBean<BorrowInfoVO> dataGrid(int pageCode, int pageSize);

	public int addBorrow(Reader reader, Integer aid, String iSBN);

	public BorrowShowInfo getBorrowShowInfo(int id);

	


}
