package cn.hhit.service.impl;

import java.util.List;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import cn.hhit.pojo.User;
import cn.hhit.service.UserService;

@Service("userService")
@Lazy(true)
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService  {


	@Override
	public User selectByUphonePass(User user) {
		return userDao.selectByUphonePass(user);
	}


	@Override
	public User findUserByUphone(String uphone) {
		
		return userDao.findUserByUphone(uphone);
	}


	@Override
	public User findUserByUphoneNick(String uphone, String unick) {
		
		return userDao.findUserByUphoneNick(uphone,unick);
	}


	@Override
	public List<User> queryUser(String phone, Integer page, Integer rows) {
		
		return userDao.queryUser( phone,  page,  rows);
	}


	@Override
	public Long getCount(String nick) {
		return userDao.getCount(nick);
	}

	

}
