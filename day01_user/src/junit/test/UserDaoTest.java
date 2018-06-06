package junit.test;



import java.util.Date;

import org.junit.Test;

import cn.itcast.dao.UserDao;
import cn.itcast.dao.impl.UserDaoImpl;
import cn.itcast.domain.User;

public class UserDaoTest {
	@Test
    public void testAdd() {
        User user = new User();
        user.setBirthday(new Date());
        user.setEmail("bb@sina.com");
        user.setId("2142354354");
        user.setNickname("李子");
        user.setUsername("bbbb");
        user.setPassword("123");

        UserDao dao = new UserDaoImpl();
        dao.add(user);
        user = dao.find("bbbb", "123");
        System.out.println(user.getNickname()+"::");
    }

    @Test
    public void testFind() {
        UserDao dao = new UserDaoImpl();
        User user = dao.find("aaa", "123"); // 在断点模式Watch
      
    }

    @Test
    public void testFindByUsername() {
        UserDao dao = new UserDaoImpl();
        System.out.println(dao.find("adsad"));
    }
}
