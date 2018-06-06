package cn.hhit.eshop.service;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import cn.hhit.eshop.model.Category;
import cn.hhit.eshop.model.Product;
import cn.hhit.eshop.model.ProductBean;

public interface ProductService extends BaseService<Product> {
	// 查询商品信息，级联类别
	public List<Product> queryJoinCategory(String type, int page, int size); // 使用商品的名称查询
	// 根据关键字查询总记录数

	public Long getCount(String type);

	// 根据ids删除多条记录
	void deleteByIds(String ids);

	// 保存product
	void save(Product pro, FileInputStream input) throws IOException;

	// 更新product
	void update(Product pro, FileInputStream input) throws IOException;

	void update(ProductBean pro) throws IOException;

	// 保存product
	void save(ProductBean p) throws IOException;

	// 根据热点查询推荐商品(仅仅4个)
	public List<Product> queryByCategoryId(int cId);

	List<ProductBean> queryJoinCategory2(String name, int page, int size);

	public Product productJoinCategory(int parseInt);
	//根据商品类别推送同类更多商品
	public List<Product> queryProductByCategory(Category category);
}
