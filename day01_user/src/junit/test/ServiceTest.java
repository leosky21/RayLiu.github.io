package junit.test;

import java.util.Date;

import org.junit.Test;

import cn.itcast.dao.impl.BusinessServiceImpl;
import cn.itcast.domain.User;
import cn.itcast.exception.UserExistException;

public class ServiceTest {
	@Test
    public void testRegister() {
        User user = new User();
        user.setBirthday(new Date());
        user.setEmail("bb@sina.com");
        user.setId("2142354354");
        user.setNickname("李子");
        user.setUsername("lizi");
        user.setPassword("123");

        BusinessServiceImpl service = new BusinessServiceImpl();
        try {
            service.register(user);
            System.out.println("注册成功！！！");
        } catch (UserExistException e) {
            System.out.println("用户已存在");
        }
    }

    @Test
    public void testLogin() {
        BusinessServiceImpl service = new BusinessServiceImpl();
        User user = service.login("lizi", "123");
        System.out.println(user);
    }
}
