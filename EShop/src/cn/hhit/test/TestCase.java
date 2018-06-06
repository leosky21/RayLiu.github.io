package cn.hhit.test;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.opensaml.ws.wssecurity.impl.TimestampImpl;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import cn.hhit.eshop.model.Category;
import cn.hhit.eshop.model.Product;
import cn.hhit.eshop.service.CategoryService;
import cn.hhit.eshop.service.ProductService;
import cn.hhit.eshop.service.impl.ProductServiceImpl;

/**
 * Spring3.1后多了个spring-test-4.2.4.RELEASE.jar包，这个jar包专门用来支持JUnit基于注解的测试的，该jar包在spring-4.2.4-core中
 * 该jar包里有个SpringJUnit4ClassRunner.class，用@RunWith注解加进来即可
 * 
 * 注解@ContextConfiguration表示将ApplicationContext对象注入进来，就不用像以往那样在测试程序里先new了，直接使用
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class TestCase {
	@Resource
	private Date date;
	@Resource
	private CategoryService categoryService;
	@Resource
	private ProductService productService;
	@Test
	public void testSpringIOC() {
		System.out.println(date);
		System.out.println(System.currentTimeMillis());
		System.out.println(new Timestamp(System.currentTimeMillis()));
	}

	/**
	 * TODO GetPath
	 * */
	@Test
	public void testPath(){
		//方式一  
        System.out.println(System.getProperty("user.dir"));  
        //方式二  
        File directory = new File("");//设定为当前文件夹  
        try{  
            System.out.println(directory.getCanonicalPath());//获取标准的路径  
            System.out.println(directory.getAbsolutePath());//获取绝对路径  
        }catch(Exception e)  
        {  
            e.printStackTrace();  
        }  
        //方式三  
        System.out.println(TestCase.class.getResource("/"));  
        System.out.println(TestCase.class.getResource(""));  
        //方式4  
        System.out.println(TestCase.class.getClassLoader().getResource(""));  
        System.out.println("hibernate.cfg.xml::::"+TestCase.class.getClassLoader().getResource("hibernate.cfg.xml"));  
	}
	@Test
	public void testQueryJoinAccount() {
		for (Category c : categoryService.queryJoinAccount("")) {
			System.out.println(c);
			System.out.println(c.getAccount());
		}
	}
	@Test
	public void testQueryJoinCategory() {
		
		for (Product c : productService.queryJoinCategory("", 1, 1) ) {
			System.out.println(c);
			System.out.println(c.getCategory());
			System.out.println("--"+c.getCategory().getAccount());
			//System.out.println(c.getCategory());
		}
	}
	
	
	public void update(@RequestParam("pic") CommonsMultipartFile pic/*,HttpServletRequest request*/) throws IOException {
		/**
		 * https://www.cnblogs.com/zhangzhen894095789/p/6475033.html
		 * 
		 * 
		 * */
		
		// String pic = fileUpload.uploadFile(fileImage);
		// model.setPic(pic);
		// model.setDate(new Timestamp(new Date().getTime()));
		// System.out.println(model);
		// //更新商品
		// productService.update(model);
		 /**第一种方法*/
//		//文件上传的三部曲
//        //创建工厂
//        DiskFileItemFactory factoy=new DiskFileItemFactory();
//        //创建解析器
//        ServletFileUpload sfu=new ServletFileUpload(factoy);
//        //设置上传文件的大小
//        sfu.setFileSizeMax(200*1024);
//		List<Product> list=sfu.parseRequest(request);

//		//product = list.get(0);
//		System.out.println(product.getName()+"----update----"+list.size());
//		System.out.println(
//				list.get(0)+"\n"+
//						list.get(1)+"\n"+
//						list.get(2)+"\n"+
//						list.get(3)+"\n"+
//						list.get(4)+"\n"
//				);
		/**第二种方法*/
//		  long  startTime=System.currentTimeMillis();
//		System.out.println("fileName："+pic.getOriginalFilename());
//		 InputStream is=pic.getInputStream();
//
//         int len;
//         //一个一个字节的读取并写入
//         while((len=is.read()) > -1){
//             //os.write(buffer, 0, len);
//        	 System.out.print(len);
//         }
//     
//        is.close();
//        long  endTime=System.currentTimeMillis();
//        System.out.println("方法的运行时间："+String.valueOf(endTime-startTime)+"ms");
//        
        /**第三种方法*/
		long  startTime=System.currentTimeMillis();
        String path = System.getProperty("user.dir");
        File newFile=new File(path+"/"+pic.getOriginalFilename());
        
        pic.transferTo(newFile);
        System.out.println(newFile.canRead()+"--"+newFile.getAbsolutePath());// /Applications/MyEclipse 2017 CI/MyEclipse 2017 CI.app/Contents/MacOS/pic05.jpg
        long  endTime=System.currentTimeMillis();
        System.out.println("方法二的运行时间："+String.valueOf(endTime-startTime)+"ms");
	}
	
	@Test
	public void testqueryByCategoryId(){
		ProductService ps = new ProductServiceImpl();
		List<Product> ls = ps.queryByCategoryId(1);
			System.out.println(ls);	
	}
	
}
/*
 * Caused by: org.springframework.beans.factory.BeanCreationException: Error
 * creating bean with name
 * 'org.springframework.validation.beanvalidation.OptionalValidatorFactoryBean#0
 * ': Invocation of init method failed; nested exception is
 * java.lang.AbstractMethodError:
 * org.hibernate.validator.engine.ConfigurationImpl.
 * getDefaultParameterNameProvider()Ljavax/validation/ParameterNameProvider;
 */

/*
 * c3p0 配置连接MySQL异常： 
 * Caused by: java.sql.SQLException: Connections could not be acquired from the underlying database!
 * 
 * (1)c3p0-0.9.5.2.jar (2)mchange-commons-java-0.2.3.4.jar
 * 
 * <c3p0-config> <!-- This app is massive! --> <named-config name="mvcapp">
	 * <property name="user">root</property>
	 *  <property
	 * 		name="password">123456</property> 
	 * <property
	 * 		name="driverClass">com.mysql.jdbc.Driver</property> 
	 * <property
	 * 		name="jdbcUrl">jdbc:mysql://localhost:3306/db_studentinfo</property>
	 * 
	 * 
	 * <property name="acquireIncrement">5</property> 
	 * <property
	 * 		name="initialPoolSize">10</property> 
	 * <property
	 * 		name="minPoolSize">10</property> 
	 * <property name="maxPoolSize">50</property>
	 * 
	 * <!-- intergalactoApp adopts a different approach to configuring statement
	 * caching --> 
	 * <property name="maxStatements">20</property> 
	 * <property
	 * 		name="maxStatementsPerConnection">5</property> 
	 * </named-config> 
 * </c3p0-config>
 */


