package cn.hhit.eshop.service;

import cn.hhit.eshop.model.PersistentLogins;

public interface PersistentLoginsService extends BaseService<PersistentLogins> {

//	void insertSelective(PersistentLogins pLogin);

//	void updateByPrimaryKeySelective(PersistentLogins pLogin);

	PersistentLogins selectByUphoneAndUuid(String uphone,String uuidByCookie);

	PersistentLogins selectByUphone(String uphone);
	
}
