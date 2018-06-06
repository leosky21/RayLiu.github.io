package cn.hhit.dao;

import java.util.List;

import cn.hhit.pojo.Medicine;
import cn.hhit.pojo.MedicinePlan;

public interface MedicinePlanDao extends BaseDao<MedicinePlan>{
	
	List<Medicine> queryMedicinePlan(String uphone, String license, Integer page, Integer rows);

	Long getCount(String phone);
}
