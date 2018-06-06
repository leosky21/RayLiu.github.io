package cn.ray.beans;
/**
 * 
 * MySQl:
 
create table users 
(
    id int primary key auto_increment,
    name varchar(40) not null,
    password varchar(40) not null
);

 *
 */
public class User {
	private int id;
    private String username;
    private String company;
    private String tel;
    private String password;

    
    public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	
   
    public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
}
