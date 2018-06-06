package cn.itcast.domain;

public class CartItem {
	
	private Book book;
	private int quantity;
	private double price;
	
	public CartItem() {
		super();
	}
	public CartItem(Book book, int quantity, double price) {
		super();
		this.book = book;
		this.quantity = quantity;
		this.price = price;
	}
	public Book getBook() {
		return book;
	}
	public int getQuantity() {
		return quantity;
	}
	public double getPrice() {
		return price;
	}
	public void setBook(Book book) {
		this.book = book;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public void setPrice(double price) {
		this.price = price;
	}
}
