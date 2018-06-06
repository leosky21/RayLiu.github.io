package cn.hhit.eshop.service.impl;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import cn.hhit.eshop.model.PersistentLogins;
import cn.hhit.eshop.model.User;
import cn.hhit.eshop.service.UserService;


@Service("userService")
@Lazy(true)
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService {
//
//	@Override
//	public PersistentLogins selectByUphoneAndSeries(String username, String series) {
//		
//		return userDao.selectByUphoneAndSerie(username, series);
//	}

	@Override
	public User selectByUphone(String uphone) {
		
		return userDao.selectByUphone(uphone);
	}

	@Override
	public User selectByUphoneNick(User user) {
		
		return userDao.selectByUphoneNick(user);
	}

}
