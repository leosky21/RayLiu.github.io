package cn.hhit.eshop.service.impl;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import cn.hhit.eshop.model.Account;
import cn.hhit.eshop.service.AccountService;

@Service("accountService")
@Lazy(true)
public class AccountServiceImpl extends BaseServiceImpl<Account> implements AccountService{

	@Override
	public Account findAccountByUserName(String username) {
		
		return accountDao.findAccountByUserName(username);
	}
	/**
	 * admin登录相关的服务 独家拥有
	 */

}
