package com.ray.enitity;

public class ClassCourseInfo {
	private String select;
	private int  id;
	private String courseName;
	private String cName;
	private String tId;
	
	
	public ClassCourseInfo() {
		super();
	}
	
	
	public ClassCourseInfo(String select,int id, String courseName, String cName, String tId, String ccAllocation) {
		super();
		this.select = select;
		this.id = id;
		this.courseName = courseName;
		this.cName = cName;
		this.tId = tId;
		
	}


	public String getSelect() {
		return select;
	}


	public void setSelect(String select) {
		this.select = select;
	}


	public int getId() {
		return id;
	}
	public String getCourseName() {
		return courseName;
	}
	public String getcName() {
		return cName;
	}
	public String gettId() {
		return tId;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public void setcName(String cName) {
		this.cName = cName;
	}
	public void settId(String tId) {
		this.tId = tId;
	}
	
	
}
