package cn.hhit.service.impl;

import org.springframework.stereotype.Service;

import cn.hhit.pojo.PersistentLogins;
import cn.hhit.service.PersistentLoginsService;

@Service("persistentLoginsService")
public class PersistentLoginsServiceImpl extends BaseServiceImpl<PersistentLogins> implements PersistentLoginsService {
	
	@Override
	public PersistentLogins selectByphoneAndUuid(String uphone,String uuidvalue) {
		return 	persistentLoginsDao.selectByUphoneAndUuid(uphone,uuidvalue);
	}
	//通过手机号找自动登录字段
	@Override
	public PersistentLogins selectByUphone(String phone) {
		return persistentLoginsDao.selectByUphone(phone);
	}

	@Override
	public PersistentLogins selectByUphoneNick(String phone, String nick) {
		
		return persistentLoginsDao.selectByUphoneNick(phone,nick);
	}

}
