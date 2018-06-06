package cn.hhit.dao;

import java.util.List;

import cn.hhit.pojo.Medicine;

public interface MedicineDao extends BaseDao<Medicine> {

	List<Medicine> queryMedicine(String licenseNumber, int page, int rows);

	void deleteByLicenseNumber(String licenseNumber);

	Long getCount(String medicineName);

	Medicine findByLicenseNumber(String licenseNumber);
	
}
