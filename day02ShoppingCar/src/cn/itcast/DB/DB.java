package cn.itcast.DB;

import java.util.LinkedHashMap;
import java.util.Map;
import cn.itcast.domain.Book;


public class DB {
	private static Map<String,Book> map = new LinkedHashMap<String, Book>();
	
	static {
        map.put("1", new Book("1", "javaweb开发", "老张", 38, "一本好书"));
        map.put("2", new Book("2", "jdbc开发", "老黎", 18, "一本好书"));
        map.put("3", new Book("3", "ajax开发", "老佟", 328, "一本好书"));
        map.put("4", new Book("4", "jbpm开发", "老毕", 58, "一本好书"));
        map.put("5", new Book("5", "struts开发", "老方", 28, "一本好书"));
        map.put("6", new Book("6", "spring开发", "老方", 98, "一本好书"));
    }
	 
	public static Map<String, Book> getAll() {
	        return map;
	    }
}
