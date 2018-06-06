package cn.hhit.dao.impl;

import java.util.List;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import cn.hhit.dao.UserDao;
import cn.hhit.pojo.User;

@SuppressWarnings("unchecked")
@Repository("userDao")
@Lazy(true)
public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao {

	
	@Override
	public User findUserByUphone(String uphone) {
		String hql = "from User u  where u.uphone=:uphone";
		return (User) getSession()
				.createQuery(hql)
				.setString("uphone", uphone)
				.uniqueResult();
	}

	@Override
	public User selectByUphonePass(User user) {
		String hql = "from User u  where u.phone=:phone and u.password=:password and u.nick=:nick";
		return (User) getSession()
				.createQuery(hql)
				.setString("phone", user.getPhone())
				.setString("nick", user.getNick())
				.setString("password", user.getPassword())
				.uniqueResult();
	}

	@Override
	public User findUserByUphoneNick(String uphone, String unick) {
		String hql = "from User u  where u.phone=:phone and u.nick=:nick";
		return (User) getSession()
				.createQuery(hql)
				.setString("phone", uphone)
				.setString("nick", unick)
				.uniqueResult();
	}


	@Override
	public List<User> queryUser(String phone, Integer page, Integer rows) {
		String hql = "from User u  where u.phone like :phone";

		return getSession().createQuery(hql)
				.setString("phone", "%" + phone + "%")
				.setFirstResult((page - 1) * rows) // 从第几个开始显示
				.setMaxResults(rows) // 显示几个
				.list();
	}

	@Override
	public Long getCount(String nick) {
		String hql = "select count(m) from User m where m.nick like :nick";
		return (Long) getSession()
				.createQuery(hql)
				.setString("nick", "%" + nick + "%")
				.uniqueResult(); // 返回一条记录:总记录数
	}
	
	
}
