package cn.ray.pojo;


public class User {
	private String name;
	private String password;
	private String email;
	private int age;
	private Address address;
	
	
	public User() {
		super();
	}
	public User(String name, String password, String email, int age, Address address) {
		super();
		this.name = name;
		this.password = password;
		this.email = email;
		this.age = age;
		this.address = address;
	}
	public String getName() {
		return name;
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	
	
}
