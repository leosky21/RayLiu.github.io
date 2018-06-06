package cn.hhit.service;

import cn.hhit.pojo.PersistentLogins;

public interface PersistentLoginsService extends BaseService<PersistentLogins>{

	PersistentLogins selectByUphone(String phone);

	PersistentLogins selectByUphoneNick(String phone, String nick);

	PersistentLogins selectByphoneAndUuid(String uphoneByCookie, String uuidByCookie);

}
