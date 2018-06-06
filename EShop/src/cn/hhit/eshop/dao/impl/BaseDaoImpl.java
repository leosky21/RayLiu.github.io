package cn.hhit.eshop.dao.impl;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import cn.hhit.eshop.dao.BaseDao;

/**
 * 
 * TODO 公共模块的抽取
 * 
 * @author liujun
 *
 */
@SuppressWarnings("rawtypes")
@Repository("baseDao")
@Lazy(true)
public class BaseDaoImpl<T> implements BaseDao<T> {

	private Class clazz;// 存储当前操作的类型 : T
	@Resource  //在属性上 ,将会使用反射进来
	private SessionFactory sessionFactory;

	public BaseDaoImpl() {
		super();
//		System.out.println("this代表的是当前调用构造方法的对象" + this);
//		System.out.println("获取当前this对象的父类信息" + this.getClass().getSuperclass());
//		System.out.println("获取当前this对象的父类信息(包括泛型信息)" + this.getClass().getGenericSuperclass());

		// 范式应该在编译的时候指定,运行的时候则会报错
		// java.lang.ClassCastException: java.lang.Class cannot be cast to
		// java.lang.reflect.ParameterizedType
		ParameterizedType type = (ParameterizedType) this.getClass().getGenericSuperclass();
		clazz = (Class) type.getActualTypeArguments()[0];
		
	}
//
//	public void setSessionFactory(SessionFactory sessionFactory) {
//		this.sessionFactory = sessionFactory;
//	}

	protected Session getSession() {
		// 从当前线程获取session，如果没有则创建一个新的session
		return sessionFactory.getCurrentSession();
	}
 
	@Override
	public void save(T t) {
		getSession().save(t);
	}

	@Override
	public void update(T t) {
		getSession().update(t);
	}

	@Override
	public void delete(int id) {
		System.out.println(clazz.getSimpleName());
		String hql = "delete " + clazz.getSimpleName() + " as c where c.id=:id";
		getSession().createQuery(hql) //
				.setInteger("id", id) //
				.executeUpdate();
	}

	@Override
	public T get(int id) {
		return (T) getSession().get(clazz, id);
	}

	@Override
	public List<T> query() {
		String hql = "from " + clazz.getSimpleName();  
        return getSession().createQuery(hql).list();
	}

}
