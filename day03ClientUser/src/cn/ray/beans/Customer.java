package cn.ray.beans;

import java.io.Serializable;
import java.util.Date;
/**
 * 
 * MySQl:
 
create database day03_customer character set utf8 collate utf8_general_ci;
use day03_customer;
create table customer 
(
    id varchar(40) primary key,
    name varchar(40) not null,
    gender varchar(4) not null,
    birthday date,
    cellphone varchar(20),
    email varchar(40),
    preference varchar(255),
    type varchar(100) not null,
    description varchar(255)
);


 *
 */
public class Customer implements Serializable{
	private String Id;
	private String name;
	private String gender;
	private Date birthday;
	private String cellPhone;
	private String Email;
	private String preference;
	private String type;
	private String Description;
	
	
	public String getId() {
		return Id;
	}
	public String getName() {
		return name;
	}
	public String getGender() {
		return gender;
	}
	public Date getBirthday() {
		return birthday;
	}
	public String getCellPhone() {
		return cellPhone;
	}
	public String getEmail() {
		return Email;
	}
	public String getPreference() {
		return preference;
	}
	public String getType() {
		return type;
	}
	public String getDescription() {
		return Description;
	}
	public void setId(String id) {
		Id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public void setCellPhone(String cellPhone) {
		this.cellPhone = cellPhone;
	}
	public void setEmail(String email) {
		Email = email;
	}
	public void setPreference(String preference) {
		this.preference = preference;
	}
	public void setType(String type) {
		this.type = type;
	}
	public void setDescription(String description) {
		Description = description;
	}
	
	
	
}
