package cn.itcast.dao;

import java.util.Map;

import cn.itcast.DB.DB;
import cn.itcast.domain.Book;

public class BookDao {
	public Map<String, Book> getAll(){
		return DB.getAll();
	}
	public Book find(String id){
		return DB.getAll().get(id);
	}
	public Book remove(String id) {
		
		return  DB.getAll().remove(id);
	}
	
}
