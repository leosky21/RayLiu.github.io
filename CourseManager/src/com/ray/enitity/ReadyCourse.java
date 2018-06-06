package com.ray.enitity;

public class ReadyCourse {
	private String courseName;
	private String tid;
	private int courseTime;
	private int courseIs;//是否为副科
	
	
	public ReadyCourse() {
		super();
	}
	public ReadyCourse(String courseName, int courseTime, int courseIs,String tid) {
		super();
		this.courseName = courseName;
		this.courseTime = courseTime;
		this.courseIs = courseIs;
		this.tid = tid;
	}
	
	public String getTid() {
		return tid;
	}
	public void setTid(String tid) {
		this.tid = tid;
	}
	public int getCourseIs() {
		return courseIs;
	}
	public void setCourseIs(int courseIs) {
		this.courseIs = courseIs;
	}
	public String getCourseName() {
		return courseName;
	}
	public int getCourseTime() {
		return courseTime;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public void setCourseTime(int courseTime) {
		this.courseTime = courseTime;
	}
	
	
}
