package cn.hhit.service.impl;

import java.util.List;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import cn.hhit.pojo.Medicine;
import cn.hhit.service.MedicineService;

@Service("medicineService")
@Lazy(true)
public class MedicineServiceImpl extends BaseServiceImpl<Medicine> implements MedicineService {

	//分页查询药品信息
	@Override
	public List<Medicine> queryMedicine(String medicineName, int page, int rows) {
		
		return medicineDao.queryMedicine(medicineName,page,rows);
	}

	//根据药品准字号删除药品
	@Override
	public void deleteByLicenseNumber(String licenseNumber) {

		medicineDao.deleteByLicenseNumber(licenseNumber);
	}

	//查询数据库中存储药品总数
	@Override
	public Long getCount(String medicineName) {
		
		return medicineDao.getCount(medicineName);
	}

	@Override
	public Medicine findByLicenseNumber(String licenseNumber) {
		return medicineDao.findByLicenseNumber(licenseNumber);
	}
	
}
