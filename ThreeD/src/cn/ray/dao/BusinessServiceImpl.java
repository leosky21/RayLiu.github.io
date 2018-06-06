package cn.ray.dao;


import cn.ray.beans.Customer;
import cn.ray.exception.UserExistException;
import cn.ray.utils.ServiceUtils;
//对web层提供所有的业务服务
public class BusinessServiceImpl {

    private CustomerDao dao = new CustomerDaoImpl();

    // 对web层提供注册服务
    public void register(Customer customer) throws UserExistException {
        // 先判断当前要注册的用户是否存在
        boolean b = dao.find(customer.getName());
        if(b) {
            
            throw new UserExistException(); // 发现要注册的用户已存在，则给web层抛一个编译时异常
        } else {
        	customer.setPassword(ServiceUtils.md5(customer.getPassword()));
            dao.add(customer);
        }
    }

    // 对web层提供登录服务
    public Customer login(String username, String password) { // aaa 123
       // password = ServiceUtils.md5(password); // 要把密码md5一把再找
        return dao.find(username, password);
    }
}
