package cn.hhit.eshop.service.impl;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import cn.hhit.eshop.dao.AccountDao;
import cn.hhit.eshop.dao.BaseDao;
import cn.hhit.eshop.dao.CategoryDao;
import cn.hhit.eshop.dao.ForderDao;
import cn.hhit.eshop.dao.PersistentLoginsDao;
import cn.hhit.eshop.dao.ProductDao;
import cn.hhit.eshop.dao.SorderDao;
import cn.hhit.eshop.dao.UserDao;
import cn.hhit.eshop.service.BaseService;

/**
 * 
 * TODO 公共模块的抽取
 * 
 * @author liujun
 *
 */
@SuppressWarnings("rawtypes")
@Service("baseService")
@Lazy(true)
public class BaseServiceImpl<T> implements BaseService<T> {

	private Class clazz;// 存储当前操作的类型 : T
	
	protected BaseDao baseDao;
	
	@Resource
	protected AccountDao accountDao;
	@Resource
	protected CategoryDao categoryDao;
	@Resource
	protected ProductDao productDao;
	@Resource
	protected ForderDao forderDao;
	@Resource
	protected SorderDao sorderDao;
	@Resource
	protected PersistentLoginsDao persistentLoginsDao;
	@Resource
	protected UserDao userDao;
	
	public BaseServiceImpl() {
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

	@PostConstruct
	public void init() {
		String clazzName = clazz.getSimpleName();
		String clazzDao = clazzName.substring(0, 1).toLowerCase() 
				+ clazzName.substring(1) + "Dao"; //ÀýÈçAccount==>accountDao
		System.out.println(clazzDao);
		try {
//			Field clazzField = this.getClass().getField(clazzDao);
//			Field baseField = this.getClass().getField("baseDao");
			Field clazzField = this.getClass().getSuperclass().getDeclaredField(clazzDao);
			Field baseField = this.getClass().getSuperclass().getDeclaredField("baseDao");
			baseField.set(this, clazzField.get(this)); 
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	@Override
	public void save(T t) {
		baseDao.save(t);
	}

	@Override
	public void update(T t) {
		baseDao.update(t);	
	}

	@Override
	public void delete(int id) {
		baseDao.delete(id);
	}

	@Override
	public T get(int id) {
		return (T) baseDao.get(id);
	}

	@Override
	public List<T> query() {
		return baseDao.query();
	}
}
