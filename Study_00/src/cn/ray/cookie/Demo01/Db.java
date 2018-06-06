package cn.ray.cookie.Demo01;

import java.util.LinkedHashMap;
import java.util.Map;

//代表数据库
public class Db {
	/**
	 *  在 java 中有两种类型的容器,一种是单列的容器(例如: List )
	 *  		一种是双列的容器(map集合)
	 *  	要保存网站所有的商品,s是使用单列的容器还是双列的容器?
	 *  	- 有没有检索数据的需求? 
	 *  		如果有,则通通使用  双列的容器
	 *  		没有,则使用 单列
	 *  
	 *  
	 *  // 希望 存进去的顺序和取出来顺序一样,就不要用HashMap,应该是使用LinkedHashMap--带链表的数据结构,保证有序
	 */
	private static Map<String,Book> map = new LinkedHashMap();
	
	static {
        map.put("1", new Book("1", "javaweb开发", "老张", "一本好书！！"));
        map.put("2", new Book("2", "jdbc开发", "老张", "一本好书！！"));
        map.put("3", new Book("3", "spring开发", "老黎", "一本好书！！"));
        map.put("4", new Book("4", "struts开发", "老毕", "一本好书！！"));
        map.put("5", new Book("5", "android开发", "老黎", "一本好书！！"));
    }
	
	public static Map<String,Book> getAll(){
		return map;
	}
}
