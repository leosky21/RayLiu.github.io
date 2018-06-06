package cn.hhit.eshop.dao.impl;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Blob;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import cn.hhit.eshop.dao.ProductDao;
import cn.hhit.eshop.model.Category;
import cn.hhit.eshop.model.Product;
import cn.hhit.eshop.model.ProductBean;

@SuppressWarnings("unchecked")
@Repository("productDao")
// @Lazy(true)
public class ProductDaoImpl extends BaseDaoImpl<Product> implements ProductDao {
	/** TODO 将list中 key 为 pic 的 value 提取出来,并转换成inutstream ,转换成byte[] */
	
	@Override
	public List<ProductBean> queryJoinCategory2(String name, int page, int size) {
		String hql = "from Product p left join fetch p.category where p.name like :name";

		return getSession().createQuery(hql)
				.setString("name", "%" + name + "%")
				.setFirstResult((page - 1) * size) // 从第几个开始显示
				.setMaxResults(size) // 显示几个
				.list();
	}
	@Override
	public Product productJoinCategory(int parseInt) {
		String hql = "from Product p left join fetch p.category where p.id like :id";
		return (Product) getSession().createQuery(hql)
				.setInteger("id", parseInt)
				.uniqueResult();
	}
	@Override
	public List<Product> queryJoinCategory(String name, int page, int size) {
		String hql = "from Product p left join fetch p.category where p.name like :name";

		return getSession().createQuery(hql).setString("name", "%" + name + "%").setFirstResult((page - 1) * size) // 从第几个开始显示
				.setMaxResults(size) // 显示几个
				.list();
	}

	@Override
	public Long getCount(String name) {
		String hql = "select count(p) from Product p where p.name like :name";
		return (Long) getSession().createQuery(hql).setString("name", "%" + name + "%").uniqueResult(); // 返回一条记录:总记录数
	}

	@Override
	public void deleteByIds(String ids) {
		String hql = "delete from Product p where p.id in (" + ids + ")";
		getSession().createQuery(hql).executeUpdate();
	}

	@Override
	public void save(Product pro, FileInputStream input) throws IOException {
		Session session = getSession();
		// 创建一个Blob对象
		Blob image = Hibernate.getLobCreator(session).createBlob(input, input.available());
		pro.setPic(image);
		session.save(pro);
	}

	@Override
	public void update(Product pro, FileInputStream input) throws IOException {
		Session session = getSession();
		// 创建一个Blob对象
		Blob image = Hibernate.getLobCreator(session).createBlob(input, input.available());
		pro.setPic(image);
		getSession().update(pro);
	}

	@Override
	public void update(ProductBean p) throws IOException {
		Session session = getSession();
		// 创建一个Blob对象
		Blob image = Hibernate.getLobCreator(session).createBlob(p.getPic());
		Product pro = new Product(p.getCategory(), p.getName(), p.getPrice(), image, p.getRemark(), p.getXremark(),
				p.getDate(), p.getCommend(), p.getOpen());
		pro.setId(p.getId());
		getSession().update(pro);
	}

	@Override
	public void save(ProductBean p) throws IOException {
		Session session = getSession();
		// 创建一个Blob对象
		// Blob image = Hibernate.getLobCreator(session).createBlob(input,
		// input.available());
		// pro.setPic(image);
		Blob image = Hibernate.getLobCreator(session).createBlob(p.getPic());
		Product pro = new Product(p.getCategory(), p.getName(), p.getPrice(), image, p.getRemark(), p.getXremark(),
				p.getDate(), p.getCommend(), p.getOpen());
		getSession().save(pro);
	}

	@Override
	public List<Product> queryByCategoryId(int cId) {
		String hql = "from Product p join fetch p.category "
				+ "where p.commend=true and p.open=true and p.category.id=:cid order by p.date desc";

		List<Product> ls = getSession().createQuery(hql).setInteger("cid", cId).setFirstResult(0).setMaxResults(4)
				.list();
		return ls;

	}
	@Override
	public List<Product> queryProductByCategory(Category category) {
		String hql = "from Product p left join fetch p.category where p.category.type like :category";

		return getSession()
				.createQuery(hql)
				.setString("category", "%" + category.getType() + "%") 
				.list();
	}
	
}
