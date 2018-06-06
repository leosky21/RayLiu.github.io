package test;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.dbcp.BasicDataSource;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.tedu.dao.AdminDao;
import cn.tedu.entity.Admin;

public class TestCase {

	@Test
	public void testDataSource() throws SQLException {
		String conf = "applicationContext.xml";
		ApplicationContext ac = 
			new ClassPathXmlApplicationContext(conf);
		System.out.println( "----------" );
		
		BasicDataSource dataSource =
			ac.getBean("dataSource", BasicDataSource.class);
		System.out.println( dataSource );
		
		Connection conn = 
				dataSource.getConnection();
		System.out.println( conn ); 
	}
	
	@Test
	public void testJdbcAdminDao() {
		String conf = "applicationContext.xml";
		ApplicationContext ac = 
			new ClassPathXmlApplicationContext(conf);
		System.out.println( "----------" );
		
		AdminDao adminDao = 
				ac.getBean(AdminDao.class);
		System.out.println(adminDao);
		
		Admin admin = 
			adminDao.findByCode("caocao");
		System.out.println( admin.getName()
				+ ", " + admin.getEmail()
				+ ", " + admin.getTelephone() );
	}
}


