package com.ray.enitity;

public class ReadyClass {
	private String className;//班级名
	//初始化classArrange的空间------行 为第几节，列为周几
	private String TimeOf;//课程时间安排
	public String[][] classArrange = new String[4][5] ;//周一到到周五的课程名
	
	
	
	public String getTimeOf() {
		return TimeOf;
	}

	public void setTimeOf(String timeOf) {
		TimeOf = timeOf;
	}

	public String getClassName() {
		return className;
	}
	
	public void setClassName(String className) {
		this.className = className;
	}
	
	
}
