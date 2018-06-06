package com.ray.enitity;

public class ReadyTeacher {
	private  String TID;
	private  String teacherName;
	private  String courseName;
	private int Tmana;
	public  int[] mana = new int[5] ;//周一到到周五的工作安排
	public  String[][] classArrange = new String[4][5];//周一到到周五的上课班级名
	
	public static int[] workNum = new int[5];//每一周工作日至多两次参与排课
	
	static {
		for(int i=0;i<5;i++){
			workNum[i] = 2;
		}
	}
	public ReadyTeacher() {
		super();
	}
	public ReadyTeacher(String tID, String teacherName, int Tmana,String courseName) {
		super();
		TID = tID;
		this.teacherName = teacherName;
		this.Tmana = Tmana;
		this.courseName = courseName;
	}
	
	public int getTmana() {
		return Tmana;
	}
	public void setTmana(int tmana) {
		Tmana = tmana;
	}
	public String getTID() {
		return TID;
	}
	public void setTID(String tID) {
		TID = tID;
	}
	public String getTeacherName() {
		return teacherName;
	}
	public String getCourseName() {
		return courseName;
	}
	public static int[] getWorkNum() {
		return workNum;
	}
	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public static void setWorkNum(int[] workNum) {
		ReadyTeacher.workNum = workNum;
	}
//	public static void main(String[] args) {
//		ReadyTeacher r = new ReadyTeacher();
//		System.out.println(r.workNum[2]);
//	}
	
}
