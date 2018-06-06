package cn.hhit.eshop.service.impl;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Blob;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import cn.hhit.eshop.model.Category;
import cn.hhit.eshop.model.Product;
import cn.hhit.eshop.model.ProductBean;
import cn.hhit.eshop.service.ProductService;
import cn.hhit.eshop.utils.ExByteUtil;


@Service("productService")
@Lazy(true)
public class ProductServiceImpl extends BaseServiceImpl<Product> implements ProductService {
/**TODO  将list中 key 为 pic 的 value 提取出来,并转换成inutstream ,转换成byte[]  */
	
	@Override  
    public List<Product> queryJoinCategory(String name, int page, int size) {  
        return productDao.queryJoinCategory(name, page, size);
    }  
	@Override  
    public List<ProductBean> queryJoinCategory2(String name, int page, int size) {  
        return productDao.queryJoinCategory2(name, page, size);
    }  
    @Override  
    public Long getCount(String name) {  
       return productDao.getCount(name);
    }  
    
    @Override  
    public void deleteByIds(String ids) {  
        productDao.deleteByIds(ids); 
    }

	@Override
	public void save(Product pro, FileInputStream input) throws IOException {
		
		productDao.save(pro, input);
	}  
	@Override
	public void update(Product pro, FileInputStream input) throws IOException {
		productDao.update(pro, input);
	}
	@Override
	public void update(ProductBean p) throws IOException {
		productDao.update(p);
	}
	
	@Override
	public void save(ProductBean p) throws IOException {
		productDao.save(p);
	}

	@Override
	public List<Product> queryByCategoryId(int cId) {
		return productDao.queryByCategoryId(cId);
	
	}
	@Override
	public Product productJoinCategory(int parseInt) {
		
		return productDao.productJoinCategory(parseInt);
	}
	@Override
	public List<Product> queryProductByCategory(Category category) {
		
		return productDao.queryProductByCategory(category);
	}
}
