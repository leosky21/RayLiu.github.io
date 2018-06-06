package test;

import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.tedu.dao.CostDao;
import cn.tedu.entity.Cost;

public class TestCost {

	@Test
	public void testFindAll() {
		String conf = "applicationContext.xml";
		ApplicationContext ac = 
			new ClassPathXmlApplicationContext(conf);
		System.out.println( "----------" );
		
		CostDao costDao = 
				ac.getBean(CostDao.class);
		System.out.println(costDao);
		
		List<Cost> list = costDao.findAll();
		for( Cost c : list ) {
			System.out.println( c.getName()
				+ " " + c.getBaseDuration() );
		}
	}
	
}
