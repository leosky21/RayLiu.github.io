package com.jf.model;

import java.io.Serializable;

/**
 * 图书类型类
 * @author c
 *
 */
public class BookType implements Serializable{
//	private Integer typeId; //图书类型编号
//	private String typeName;	//图书类型名称

	private Integer id;
	private String name;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BookType() {
	}

	public BookType(Integer id) {
		this.id = id;
	}

	public BookType(Integer id, String name) {
		this.id = id;
		this.name = name;
	}
}
