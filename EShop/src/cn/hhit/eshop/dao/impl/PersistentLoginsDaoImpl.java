package cn.hhit.eshop.dao.impl;

import org.springframework.stereotype.Repository;

import cn.hhit.eshop.dao.PersistentLoginsDao;
import cn.hhit.eshop.model.PersistentLogins;
import cn.hhit.eshop.model.User;

@Repository("persistentLoginsDao")
public class PersistentLoginsDaoImpl extends BaseDaoImpl<PersistentLogins> implements PersistentLoginsDao {

	@Override
	public PersistentLogins selectByUphoneAndUuid(String uphone, String uuidValue) {
		String hql = "from PersistentLogins p where p.uphone=:uphone  and p.series=:series";

		return (PersistentLogins) getSession()
				.createQuery(hql)
				.setString("uphone", uphone)
				.setString("series", uuidValue)
				.uniqueResult();
	}

	@Override
	public PersistentLogins selectByUphone(String uphone) {
		String hql = "from PersistentLogins p where p.uphone=:uphone";

		return (PersistentLogins) getSession().createQuery(hql).setString("uphone", uphone).uniqueResult();
	}

}
