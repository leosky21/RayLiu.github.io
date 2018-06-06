package cn.hhit.service;

import java.util.List;

import cn.hhit.pojo.User;

public interface UserService extends BaseService<User> {

	User selectByUphonePass(User user);

	User findUserByUphone(String uphone);

	User findUserByUphoneNick(String uphone, String unick);

	List<User> queryUser(String phone, Integer page, Integer rows);

	Long getCount(String nick);

}
