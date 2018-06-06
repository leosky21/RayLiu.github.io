package cn.ray.dao;

import java.util.List;

import cn.ray.beans.Customer;
import cn.ray.beans.QueryResult;
import cn.ray.beans.User;

public interface UsersDao {

	void add(User c);

	void update(User c);

	void delete(int id);

	User find(int id);

	List<User> getAll();
	
	// 获取到页面数据和页面大小
    QueryResult pageQuery(int startindex, int pagesize);

}