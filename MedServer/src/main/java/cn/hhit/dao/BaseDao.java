package cn.hhit.dao;

import java.util.List;

public interface BaseDao<T> {

	void save(T t);

	void update(T t);

	void delete(int id);

	T get(int id);

	List<T> query();

}