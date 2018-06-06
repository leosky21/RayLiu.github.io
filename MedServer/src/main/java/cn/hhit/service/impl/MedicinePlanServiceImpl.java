package cn.hhit.service.impl;

import java.util.List;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import cn.hhit.pojo.Medicine;
import cn.hhit.pojo.MedicinePlan;
import cn.hhit.service.MedicinePlanService;

@Service("medicinePlanService")
@Lazy(true)
public class MedicinePlanServiceImpl extends BaseServiceImpl<MedicinePlan> implements MedicinePlanService {

	@Override
	public List<Medicine> queryMedicinePlan(String uphone, String license, Integer page, Integer rows) {
		
		return medicinePlanDao.queryMedicinePlan(uphone, license, page, rows);
	}

	@Override
	public Long getCount(String phone) {
		
		return medicinePlanDao.getCount(phone);
	}

}
