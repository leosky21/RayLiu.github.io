package cn.hhit.dao.impl;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import cn.hhit.dao.BaseDao;

/**
 * 
 * TODO 公共模块的抽取
 * 
 * @author liujun
 *
 */

@SuppressWarnings({"rawtypes","unchecked"})
@Repository("baseDao")
@Lazy(true)
public class BaseDaoImpl<T> implements BaseDao<T> {

	private Class clazz;
	@Resource  
	private SessionFactory sessionFactory;

	public BaseDaoImpl() {
		super();

		ParameterizedType type = (ParameterizedType) this.getClass().getGenericSuperclass();
		clazz = (Class) type.getActualTypeArguments()[0];
		
	}
	protected Session getSession() {
		// 从当前线程获取session，如果没有则创建一个新的session
		return sessionFactory.getCurrentSession();
	}
 
	/* (non-Javadoc)
	 * @see cn.hhit.dao.impl.BaseDao#save(T)
	 */
	@Override
	public void save(T t) {
		getSession().save(t);
	}

	
	/* (non-Javadoc)
	 * @see cn.hhit.dao.impl.BaseDao#update(T)
	 */
	@Override
	public void update(T t) {
		getSession().update(t);
	}

	
	/* (non-Javadoc)
	 * @see cn.hhit.dao.impl.BaseDao#delete(int)
	 */
	@Override
	public void delete(int id) {
		System.out.println(clazz.getSimpleName());
		String hql = "delete " + clazz.getSimpleName() + " as c where c.id=:id";
		getSession().createQuery(hql) //
				.setInteger("id", id) //
				.executeUpdate();
	}

	
	/* (non-Javadoc)
	 * @see cn.hhit.dao.impl.BaseDao#get(int)
	 */
	@Override
	public T get(int id) {
		return (T) getSession().get(clazz, id);
	}

	/* (non-Javadoc)
	 * @see cn.hhit.dao.impl.BaseDao#query()
	 */
	@Override
	public List<T> query() {
		String hql = "from " + clazz.getSimpleName();  
        return getSession().createQuery(hql).list();
	}

}
