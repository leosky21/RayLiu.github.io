package cn.ray.dao;

import cn.ray.beans.Customer;

public interface CustomerDao {

	Customer find(String username, String password);

	void add(Customer customer);

	boolean find(String username);

}