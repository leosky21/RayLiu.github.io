package cn.hhit.eshop.service;

import cn.hhit.eshop.model.User;

public interface UserService extends BaseService<User> {
	/**
     * 通过用户登录手机号和UUID值查询自动登录记录
     * 
     * @param username
     *            用户名
     * @param series
     *            UUID值
     */
//    PersistentLogins selectByUphoneAndSeries( String uphone,  String series);
 
    /**
     * 通过用户登录手机号查询自动登录记录
     * 
     * @param uphone
     *            用户登录手机号
     */
    User selectByUphone( String uphone);
    
    /**
     * 通过手机号和登录名查询用户信息
     * 
     * @param Uphone 手机号
     * @param Unick 登录名
     * @return User 
     */
    User selectByUphoneNick(User user ) ;
}
