package cn.hhit.eshop.dao.impl;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import cn.hhit.eshop.dao.AccountDao;
import cn.hhit.eshop.model.Account;

@Repository("accountDao")
@Lazy(true)
public class AccountDaoImpl extends BaseDaoImpl<Account> implements AccountDao{

	@Override
	public Account findAccountByUserName(String username) {
		String hql = "from Account a where a.login=:username";
		return (Account) getSession().createQuery(hql)
				.setString("username", username)
				.uniqueResult();
	}

}
