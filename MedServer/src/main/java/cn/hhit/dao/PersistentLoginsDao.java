package cn.hhit.dao;

import cn.hhit.pojo.PersistentLogins;

public interface PersistentLoginsDao {
	
//	void insertSelective(PersistentLogins pLogin);
//
//	void updateByPrimaryKeySelective(PersistentLogins pLogin);

	PersistentLogins selectByUphoneAndUuid(String uphone,String uuidValue);

	PersistentLogins selectByUphone(String uphone);

	PersistentLogins selectByUphoneNick(String phone, String nick);

}
