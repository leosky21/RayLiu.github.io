package cn.ray.service;


import java.util.ArrayList;
import java.util.List;

import cn.ray.domain.User;

public class BusinessService {
	
	private static List<User> list = new ArrayList<User>();
	
	static{
		list.add(new User("aaa","123"));
        list.add(new User("bbb","123"));
        list.add(new User("ccc","123"));
	}
	
	public User login(String name,String password){
		for(User user : list){
			if(user.getUsername().equals(name)
					&& user.getPassword().equals(password)
					){
				return user;
			}	
		}
		return null;
	}
	
	public User findUser(String name){
		for(User user : list){
			if(user.getUsername().equals(name)){
				return user;
			}	
		}
		return null;
	}
}
