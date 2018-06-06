package cn.hhit.eshop.utils;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;

import javax.annotation.Resource;
import javax.servlet.ServletContext;

import org.springframework.stereotype.Component;

import cn.hhit.eshop.model.Category;
import cn.hhit.eshop.model.Product;
import cn.hhit.eshop.model.ProductTest;
import cn.hhit.eshop.service.CategoryService;
import cn.hhit.eshop.service.ProductService;

@Component
public class InitProductsTimeTask extends TimerTask {

	@Resource
	private ProductService productService;// productService中定义了跟商品相关的业务逻辑
	
	@Resource
	private CategoryService categoryService;

	private ServletContext application;

	private List<List<ProductTest>> listTest = new ArrayList<List<ProductTest>>();
	
	public void setApplication(ServletContext application) {
		this.application = application;
	}

	@Override
	public void run() {
		
//		int index = 0;
		for (Category c : categoryService.queryByHot(true)) {
			// 按类别查询热点商品
			List<Product> lst = productService.queryByCategoryId(c.getId());
			List<ProductTest> list2;
			try {
				list2 = ExByteUtil.ProductFormBean(lst);
				/**
				 * TODO 在index jsp页面中将pic 的value 取出来,而不是取出对象
				 */
//				System.out.println(list2.get(index).getClass().getDeclaredField("pic"));
				listTest.add(list2);// 将不同category中的热点product装进来
//			} catch (NoSuchFieldException e) {
//				
//				e.printStackTrace();
//			} 
			}catch (SecurityException e) {
				
				e.printStackTrace();
			}catch (UnsupportedEncodingException e1) {
				System.out.println("InitProductsTimeTask  转换成utf-8 失败");
				e1.printStackTrace();
			}
			
		}
		application.setAttribute("HotProductList", listTest);
	}

}
