package cn.tedu.dao;

import cn.tedu.annotation.MyBatisRepository;
import cn.tedu.entity.Admin;

@MyBatisRepository
public interface AdminDao {

	Admin findByCode( String code );
	//public abstract，接口中所有方法都是公开抽象的
	//public static final 所有属性都是常量
}
