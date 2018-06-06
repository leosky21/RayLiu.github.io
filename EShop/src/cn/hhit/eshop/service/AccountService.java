package cn.hhit.eshop.service;

import cn.hhit.eshop.model.Account;

public interface AccountService extends BaseService<Account>{

	Account findAccountByUserName(String username);
	 

	
}
