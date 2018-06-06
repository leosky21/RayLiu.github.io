package cn.hhit.eshop.dao.impl;

import org.springframework.stereotype.Repository;

import cn.hhit.eshop.dao.UserDao;
import cn.hhit.eshop.model.PersistentLogins;
import cn.hhit.eshop.model.User;

@Repository("userDao")
public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao {

	@Override
	public PersistentLogins selectByUphoneAndSerie(String uphone, String series) {
		
		return null;
	}

	@Override
	public User selectByUphone(String uphone) {
		String hql = "from User u where u.uphone=:uphone";
		return (User) getSession().createQuery(hql)
				.setString("uphone", uphone)
				.uniqueResult();
	}

	@Override
	public User selectByUphoneNick(User user) {
		
		String hql = "from User u where u.uphone=:uphone";
		System.out.println("UserDaoImpl  ::  "+user.getUphone());
		return (User) getSession().createQuery(hql)
//				.setString("unick", user.getUnick())
				.setString("uphone", user.getUphone())
				.uniqueResult();
	}

	

}
