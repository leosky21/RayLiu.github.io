package cn.hhit.dao;

import java.util.List;

import cn.hhit.pojo.User;

public interface UserDao {

	User findUserByUphone(String uphone);

	User selectByUphonePass(User user);

	User findUserByUphoneNick(String uphone, String unick);

	List<User> queryUser(String phone, Integer page, Integer rows);

	Long getCount(String nick);

}