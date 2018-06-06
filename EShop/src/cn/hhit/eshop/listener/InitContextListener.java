package cn.hhit.eshop.listener;

import java.util.Timer;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import cn.hhit.eshop.utils.InitProductsTimeTask;

//  TODO 监听器是web层的组件，它是tomcat实例化的，不是Spring实例化的。不能放到Spring中 
public class InitContextListener implements ServletContextListener {
	// private ProductService productService = null;//
	// productService中定义了跟商品相关的业务逻辑
	// private CategoryService categoryService = null;
	// private ApplicationContext context = null;
	//
	// private List<List<ProductBean>> list = new
	// ArrayList<List<ProductBean>>();

	private InitProductsTimeTask initProductsTimeTask = null; // 定义一个ProductTimerTask对象
	private ApplicationContext context = null;
	
	@Override
	public void contextInitialized(ServletContextEvent sce) {
	
		context = WebApplicationContextUtils.getWebApplicationContext(sce.getServletContext());
		initProductsTimeTask = (InitProductsTimeTask) context.getBean("initProductsTimeTask");// 从配置文件中获取ProductTimerTask对象

		// 把内置对象交给productTimerTask,因为productTimerTask里面是拿不到application的，只能通过监听器set给它
		initProductsTimeTask.setApplication(sce.getServletContext());

		// 通过设置定时器，让首页的数据每个一小时同步一次（配置为守护线程）
		new Timer(true).schedule(initProductsTimeTask, 0, 1000 * 60 * 60 * 4);// 每个12小时执行一次productTimerTask任务，即更新一下后台数据
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {

	}

	/**
	 *
	 * @Override public void contextInitialized(ServletContextEvent sce) {
	 *           context =
	 *           WebApplicationContextUtils.getWebApplicationContext(sce.getServletContext());
	 *           productService = (ProductService)
	 *           context.getBean("productService");
	 * 
	 *           categoryService = (CategoryService)
	 *           context.getBean("categoryService");
	 * 
	 *           int index= 0; for (Category c :
	 *           categoryService.queryByHot(true)) { //按类别查询热点商品 List<Product>
	 *           lst = productService.queryByCategoryId(c.getId());
	 *           List<ProductBean> list2 = ExByteUtil.ProductFormBean(lst); try
	 *           {
	 * 
	 *           TODO 在index jsp页面中将pic 的value 取出来,而不是取出对象
	 * 
	 *           System.out.println(list2.get(index).getClass().getDeclaredField("pic"));
	 * 
	 *           } catch (NoSuchFieldException e) { // TODO Auto-generated catch
	 *           block e.printStackTrace(); } catch (SecurityException e) { //
	 *           TODO Auto-generated catch block e.printStackTrace(); }
	 *           list.add(list2);// 将不同category中的热点product装进来 }
	 *           sce.getServletContext().setAttribute("HotProductList", list); }
	 *
	 *
	 * 
	 */

}
