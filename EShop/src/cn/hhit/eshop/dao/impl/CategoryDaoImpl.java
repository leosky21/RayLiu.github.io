package cn.hhit.eshop.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import cn.hhit.eshop.dao.CategoryDao;
import cn.hhit.eshop.model.Category;
import cn.hhit.eshop.model.HibernateSessionFactory;
import cn.hhit.eshop.service.CategoryService;
@SuppressWarnings("unchecked")
@Repository("categoryDao")
@Lazy(true)
public class CategoryDaoImpl extends BaseDaoImpl<Category> implements CategoryDao {	
	
	
	@Override
	public List<Category> queryJoinAccount(String type) {
		String hql = "select c.id,c.type from Category c  where c.id > ?";
		return getSession()
				.createQuery(hql)
				.setInteger(0, 0)
				.list();
	}
	/**
	 * 
	 * TODO 级联:
	 * 		将fetch设置生FetchType.LAZY就不会发多条语句了，
	 * 	但是设置成LAZY后，
	 * 		我们就拿不到Account对象了，比较好的解决方法是我们自己写hql语句，使用join fetch。
	 */
	/**
	 * TODO 分页
	 */
	@Override
	public List<Category> queryJoinAccount(String type, int page, int size) {
		String hql = " from Category c left join fetch c.account where c.type like :type";
		return getSession()
				.createQuery(hql)
				.setMaxResults(size)
				.setFirstResult((page-1)*size)
				.setString("type", "%"+type+"%")
				.list();
	}

	@Override
	public Long getCount(String type) {
		String hql = "select count(c) from Category c where c.type like :type";
		return (Long) getSession().createQuery(hql).setString("type", "%" + type + "%").uniqueResult(); // 返回一条记录:总记录数
	}

	@Override
	public void deleteByIds(String ids) {
		 String hql = "delete from Category c where c.id in (" + ids + ")";  
	        getSession().createQuery(hql).executeUpdate();  
	}
	
	
	@Override
	public List<Category> queryByHot(boolean hot) {
		
		String hql = "from Category c where c.hot=:hot";
		return getSession()
				.createQuery(hql)
				.setBoolean("hot", hot)
				.list();
	}

}
