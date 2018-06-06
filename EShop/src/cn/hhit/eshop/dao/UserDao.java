package cn.hhit.eshop.dao;

import cn.hhit.eshop.model.PersistentLogins;
import cn.hhit.eshop.model.User;

public interface UserDao extends BaseDao<User>{
	/**
     * 通过用户名和UUID值查询自动登录记录
     * 
     * @param username
     *            用户名
     * @param series
     *            UUID值
     */
    PersistentLogins selectByUphoneAndSerie( String uphone,  String series);
 
    /**
     * 通过用户名查询自动登录记录
     * 
     * @param uphone
     *            用户名
     */
    User selectByUphone( String uphone);
    
    /**
     * 通过手机号和登录名查询用户信息
     * 
     * @param Uphone 手机号
     * @param Unick 登录名
     * @return User 
     */
    User selectByUphoneNick(User user) ;
}
