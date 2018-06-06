package cn.hhit.eshop.dao;

import cn.hhit.eshop.model.Account;

public interface AccountDao extends BaseDao<Account>{

	Account findAccountByUserName(String username);

}
