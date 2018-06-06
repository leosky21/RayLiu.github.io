package cn.ray.service;

import cn.ray.beans.PageBean;
import cn.ray.beans.QueryInfo;
import cn.ray.beans.QueryResult;
import cn.ray.dao.StudentDao;

public class BuinessService {
	
	
	public PageBean pageBean(QueryInfo info){
		StudentDao dao = new StudentDao();
		
		QueryResult qr = dao.pageQuery(info.getStartindex(), info.getPagesize());
		
		PageBean bean = new PageBean();
		
		bean.setCurrentpage(info.getCurrentpage());
		bean.setList(qr.getList());
		bean.setPagesize(info.getPagesize());
		bean.setTotalrecord(qr.getTotalrecord());
		
		return bean;
	}
}
