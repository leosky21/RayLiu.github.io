package cn.hhit.dao.impl;

import org.springframework.stereotype.Repository;

import cn.hhit.dao.PersistentLoginsDao;
import cn.hhit.pojo.PersistentLogins;

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

	@Override
	public PersistentLogins selectByUphoneNick(String phone, String unick) {
		String hql = "from PersistentLogins p where p.uphone=:uphone and p.unick=:unick";

		return (PersistentLogins) getSession().createQuery(hql).setString("uphone", phone).setString("unick", unick).uniqueResult();
	}

	

}
