package cn.hhit.service;

import java.util.List;

import cn.hhit.pojo.Medicine;

public interface MedicineService extends BaseService<Medicine> {

	List<Medicine> queryMedicine(String licenseNumber, int page, int rows);

	Long getCount(String medicineName) ;

	void deleteByLicenseNumber(String licenseNumber);

	Medicine findByLicenseNumber(String licenseNumber);
	
}
