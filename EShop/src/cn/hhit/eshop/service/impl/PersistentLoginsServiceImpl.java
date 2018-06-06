package cn.hhit.eshop.service.impl;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import cn.hhit.eshop.model.PersistentLogins;
import cn.hhit.eshop.service.PersistentLoginsService;


@Service("persistentLoginsService")
@Lazy(true)
public class PersistentLoginsServiceImpl extends BaseServiceImpl<PersistentLogins> implements PersistentLoginsService {

	@Override
	public PersistentLogins selectByUphoneAndUuid(String uphone,String uuidvalue) {
		return 	persistentLoginsDao.selectByUphoneAndUuid(uphone,uuidvalue);
	}

	@Override
	public PersistentLogins selectByUphone(String uphone) {
		
		return persistentLoginsDao.selectByUphone(uphone);
	}

	

}
