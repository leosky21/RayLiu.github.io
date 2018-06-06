package cn.ray.beans;

import java.io.Serializable;
import java.util.Date;
/**
 * 
 * MySQl:
 
create table customer 
(
    id varchar(40) primary key,
    name varchar(40) not null,
    gender varchar(4) not null,
   
);


 *
 */
public class Customer implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String Id;
	private String name;
	private String password;

	
	public String getId() {
		return Id;
	}
	public String getName() {
		return name;
	}
	
	public void setId(String id) {
		Id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
}
