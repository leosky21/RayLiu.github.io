package cn.hhit.eshop.dao;

import cn.hhit.eshop.model.PersistentLogins;

public interface PersistentLoginsDao {
	
//	void insertSelective(PersistentLogins pLogin);
//
//	void updateByPrimaryKeySelective(PersistentLogins pLogin);

	PersistentLogins selectByUphoneAndUuid(String uphone,String uuidValue);

	PersistentLogins selectByUphone(String uphone);
}
