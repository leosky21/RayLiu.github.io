package cn.tedu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.tedu.dao.CostDao;
import cn.tedu.entity.Cost;

@Service
public class CostServiceImpl 
				implements CostService{

	@Autowired
	private CostDao costDao;
	
	public List<Cost> find() {
		return costDao.findAll();
	}
	

}
