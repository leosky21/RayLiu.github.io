package cn.itcast.dao.impl;

import cn.itcast.dao.UserDao;
import cn.itcast.domain.User;
import cn.itcast.exception.UserExistException;
import cn.itcast.utils.ServiceUtils;
//对web层提供所有的业务服务
public class BusinessServiceImpl {
	/*
     * 业务逻辑层和数据访问层要解耦
     * 要解耦，有两张方法：
     * 1. 工厂模式
     * 2. spring
     */
    private UserDao dao = new UserDaoImpl();

    // 对web层提供注册服务
    public void register(User user) throws UserExistException {
        // 先判断当前要注册的用户是否存在
        boolean b = dao.find(user.getUsername());
        if(b) {
            /*
             * service层是由web层来调用的，
             * 发现当前要注册的用户已存在，要提醒给web层，web层给用户一个友好提示
             * 希望web层一定要处理，处理之后给用户一个友好提示，所以抛一个编译时异常，
             * 抛运行时异常是不行的，因为web层可处理可不处理
             */
            throw new UserExistException(); // 发现要注册的用户已存在，则给web层抛一个编译时异常，提醒web层处理这个异常，给用户一个友好提示。
        } else {
            user.setPassword(ServiceUtils.md5(user.getPassword()));
            dao.add(user);
        }
    }

    // 对web层提供登录服务
    public User login(String username, String password) { // aaa 123
        password = ServiceUtils.md5(password); // 要把密码md5一把再找
        return dao.find(username, password);
    }
}
