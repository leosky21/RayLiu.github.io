package cn.hhit.dao.impl;


import java.util.List;

import org.springframework.stereotype.Repository;

import cn.hhit.pojo.Medicine;
import cn.hhit.pojo.MedicinePlan;

@Repository("medicinePlanDao")
public class MedicinePlanDao extends BaseDaoImpl<MedicinePlan> implements cn.hhit.dao.MedicinePlanDao{

	@Override
	public List<Medicine> queryMedicinePlan(String uphone, String license, Integer page, Integer rows) {
		
		return null;
	}

	@Override
	public Long getCount(String phone) {
		
		return null;
	}
	

}
