package cn.itcast.domain;

import java.io.Serializable;

public class Book implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
    private String name;
    private String author;
    private double price;
    private String description;
    
	public Book() {
		super();
	}
	public Book(String id, String name, String author, double price, String description) {
		super();
		this.id = id;
		this.name = name;
		this.author = author;
		this.price = price;
		this.description = description;
	}
	public String getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public String getAuthor() {
		return author;
	}
	public double getPrice() {
		return price;
	}
	public String getDescription() {
		return description;
	}
	public void setId(String id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public void setDescription(String description) {
		this.description = description;
	}
    
    
}
