package cn.ray.dao;

import java.util.List;

import cn.ray.beans.Customer;
import cn.ray.beans.PageBean;
import cn.ray.beans.QueryInfo;

public interface BussinessService {

	void addCustomer(Customer c);

	void updateCustomer(Customer c);

	void deleteCustomer(String id);

	Customer findCustomer(String id);

	List<Customer> getAllCustomer();
	
	PageBean pageQuery(QueryInfo queryInfo);

}