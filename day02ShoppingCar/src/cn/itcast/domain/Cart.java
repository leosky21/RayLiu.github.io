package cn.itcast.domain;

import java.util.LinkedHashMap;
import java.util.Map;


/**
 * —— 为了避免在购物车里面出现重复商品，
	 * 我们这时会设计一个专门的对象——
	 * CartItem（购物项，代表某个商品以及这个商品出现的次数）。 
 * @author ray
 *
 */
public class Cart {
	/*
     * 为了避免在购物车里面出现重复商品，
     * 我们这时会设计一个专门的对象——CartItem(购物项，代表某个商品以及这个商品出现的次数)
     */
    private Map<String, CartItem> map = new LinkedHashMap<>();
    private double price; // 记住购物车中的所有商品多少钱

    public void add(Book book) {
        // 看购物车中有没有，要添加的书对应的购物项
        CartItem item = map.get(book.getId());
        if(item==null) {
            item = new CartItem();
            item.setBook(book);
            item.setQuantity(1);
            item.setPrice(book.getPrice());
            map.put(book.getId(), item);
        } else {
            item.setQuantity(item.getQuantity()+1);
            item.setPrice(item.getPrice()*2);
        }
    }

    public Map<String, CartItem> getMap() {
        return map;
    }
    public void setMap(Map<String, CartItem> map) {
        this.map = map;
    }
    public double getPrice() {
        double totalprice = 0;
        for(Map.Entry<String, CartItem> entry : map.entrySet()) {
            CartItem item = entry.getValue();
            totalprice += item.getPrice();
        }
        this.price = totalprice;
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
}
