package cn.hhit.dao.impl;

import java.util.List;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import cn.hhit.pojo.Medicine;

@SuppressWarnings("unchecked")
@Repository("medicineDao")
@Lazy(true)
public class MedicineDaoImpl extends BaseDaoImpl<Medicine> implements cn.hhit.dao.MedicineDao {

	@Override
	public List<Medicine> queryMedicine(String medicineName, int page, int rows) {
		String hql = "from Medicine m  where m.medicinename like :medicineName";

		return getSession().createQuery(hql)
				.setString("medicineName", "%" + medicineName + "%")
				.setFirstResult((page - 1) * rows) // 从第几个开始显示
				.setMaxResults(rows) // 显示几个
				.list();
	}

	@Override
	public void deleteByLicenseNumber(String licenseNumbers) {
		String hql = "delete from Medicine p where p.licensenumber in ('" + licenseNumbers + "')";
		getSession().createQuery(hql).executeUpdate();
	}

	@Override
	public Long getCount(String medicineName) {
		String hql = "select count(m) from Medicine m where m.medicinename like :medicineName";
		return (Long) getSession()
				.createQuery(hql)
				.setString("medicineName", "%" + medicineName + "%")
				.uniqueResult(); // 返回一条记录:总记录数
	}

	@Override
	public Medicine findByLicenseNumber(String licensenumber) {
		String hql = "from Medicine m  where m.licensenumber like :licensenumber";
		return (Medicine) getSession()
				.createQuery(hql)
				.setString("licensenumber", licensenumber)
				.uniqueResult();
	}

}
