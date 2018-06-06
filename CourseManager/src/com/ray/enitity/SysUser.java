package com.ray.enitity;

public class SysUser {
	private String userID;
	private String userName;
	private String userType;
	private String Pass;
	private String Tel;
	
	public SysUser() {
		super();
	}
	
	public SysUser(String userID, String userName, String userType, String pass, String tel) {
		super();
		this.userID = userID;
		this.userName = userName;
		this.userType = userType;
		Pass = pass;
		Tel = tel;
	}

	public String getUserID() {
		return userID;
	}
	public String getUserName() {
		return userName;
	}
	public String getUserType() {
		return userType;
	}
	public String getPass() {
		return Pass;
	}
	public String getTel() {
		return Tel;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public void setPass(String pass) {
		Pass = pass;
	}
	public void setTel(String tel) {
		Tel = tel;
	}
	
	
}
