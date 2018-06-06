package cn.tedu.dao;

import java.util.List;

import cn.tedu.annotation.MyBatisRepository;
import cn.tedu.entity.Cost;

@MyBatisRepository
public interface CostDao {

	List<Cost> findAll();
	
}
