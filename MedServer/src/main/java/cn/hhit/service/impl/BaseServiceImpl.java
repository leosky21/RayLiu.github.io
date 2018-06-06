package cn.hhit.service.impl;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import cn.hhit.dao.BaseDao;
import cn.hhit.dao.MedicineDao;
import cn.hhit.dao.MedicinePlanDao;
import cn.hhit.dao.PersistentLoginsDao;
import cn.hhit.dao.UserDao;
import cn.hhit.service.BaseService;

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
	protected UserDao userDao;
	@Resource
	protected PersistentLoginsDao persistentLoginsDao;
	@Resource
	protected MedicineDao  medicineDao;
	@Resource
	protected MedicinePlanDao medicinePlanDao;
	
	public BaseServiceImpl() {
		super();
		ParameterizedType type = (ParameterizedType) this.getClass().getGenericSuperclass();
		clazz = (Class) type.getActualTypeArguments()[0];
		System.out.println("BaseServiceImpl()-------------------");
	}

	@PostConstruct
	public void init() {
		String clazzName = clazz.getSimpleName();
		String clazzDao = clazzName.substring(0, 1).toLowerCase() 
				+ clazzName.substring(1) + "Dao"; 
		System.out.println(clazzDao+":: BaseServiceImpl");
		try {
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
