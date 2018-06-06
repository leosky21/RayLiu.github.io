package cn.ray.session.DemoLogin02;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import cn.ray.cookie.Demo01.Book;

public class DB {
private static List<User> list = new ArrayList<User>();
	
	static {
       list.add(new User("张三","123"));
       list.add(new User("admin","123"));
       list.add(new User("ray","123"));
       list.add(new User("leo","123"));
    }
	
	public static List<User> getAll(){
		return list;
	}
}
