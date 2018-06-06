package com.ray.enitity;

public class ClassInfo {
	private String select;
	private String className;//班级名
	private String classRoom;
	private String cTeacher;//班主任
	private int cNum;//班级人数
	
	
	public ClassInfo() {
		super();
	}
	
	public ClassInfo(String select,String className, String classRoom, String cTeacher, int cNum) {
		super();
		this.className = className;
		this.select = select;
		this.classRoom = classRoom;
		this.cTeacher = cTeacher;
		this.cNum = cNum;
	}


	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public String getClassRoom() {
		return classRoom;
	}
	public void setClassRoom(String classRoom) {
		this.classRoom = classRoom;
	}
	public String getSelect() {
		return select;
	}
	public String getcTeacher() {
		return cTeacher;
	}
	public int getcNum() {
		return cNum;
	}
	public void setSelect(String select) {
		this.select = select;
	}
	public void setcTeacher(String cTeacher) {
		this.cTeacher = cTeacher;
	}
	public void setcNum(int cNum) {
		this.cNum = cNum;
	}
	
}
