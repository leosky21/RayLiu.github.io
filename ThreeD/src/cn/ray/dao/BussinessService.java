package cn.ray.dao;

import java.util.List;

import cn.ray.beans.PageBean;
import cn.ray.beans.QueryInfo;
import cn.ray.beans.User;

public interface BussinessService {

	void addUser(User c);

	void updateUser(User c);

	void deleteUser(int id);

	User findUser(int id);

	List<User> getAllUser();
	
	PageBean pageQuery(QueryInfo queryInfo);

}