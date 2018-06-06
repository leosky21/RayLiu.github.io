package com.ray.enitity;

public class CourseInfo {
	private String select;
	private String courseName;
	private int courseTime;
	private String courseIS;
	private int is;
	public CourseInfo() {
		super();
	}
	public CourseInfo(String select, String courseName, int courseTime, String courseIS) {
		super();
		this.select = select;
		this.courseName = courseName;
		this.courseTime = courseTime;
		this.courseIS = courseIS;
	}
	
	
	public int getIs() {
		return is;
	}
	public void setIs(int is) {
		this.is = is;
	}
	public String getSelect() {
		return select;
	}
	public String getCourseName() {
		return courseName;
	}
	public int getCourseTime() {
		return courseTime;
	}
	public String getCourseIS() {
		return courseIS;
	}
	public void setSelect(String select) {
		this.select = select;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public void setCourseTime(int courseTime) {
		this.courseTime = courseTime;
	}
	public void setCourseIS(String courseIS) {
		this.courseIS = courseIS;
	}
	
	
}
