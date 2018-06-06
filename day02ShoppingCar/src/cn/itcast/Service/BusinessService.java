package cn.itcast.Service;

import java.util.Map;

import cn.itcast.dao.BookDao;
import cn.itcast.domain.Book;
import cn.itcast.domain.Cart;
import cn.itcast.domain.CartItem;

public class BusinessService {
/**
 * 	- 给用户展示所有书籍
	- 根据书的ID查询书的信息
 */
	private BookDao dao = new BookDao();

	public Map<String, Book> getAll(){
		return dao.getAll();
	}
	public Book find(String id){
		return dao.find(id);
	}
	public void remove(String id,Cart cart){
		 cart.getMap().remove(id);
	}
	public void removeAll(Cart cart) {
		cart.getMap().clear();
	}
	public void changeItemQuantity(String id, String quantity, Cart cart) {
		CartItem item = cart.getMap().get(id);
        item.setQuantity(Integer.parseInt(quantity));
	}
	
}
