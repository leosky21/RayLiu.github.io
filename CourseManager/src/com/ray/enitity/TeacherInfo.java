package com.ray.enitity;

public class TeacherInfo {
	private String select;
	private String tId;
	private String tName;
	private String tCourseName;
	private int tMana;
	private String tTel;
	
	public TeacherInfo() {
		super();
	}
	
	
	public TeacherInfo(String select,String tId, String tName, String tCourseName, int tMana, String tTel) {
		super();
		this.tId = tId;
		this.tName = tName;
		this.tCourseName = tCourseName;
		this.tMana = tMana;
		this.tTel = tTel;
		this.select = select;
	}


	public String getSelect() {
		return select;
	}


	public void setSelect(String select) {
		this.select = select;
	}


	public String gettId() {
		return tId;
	}
	public String gettName() {
		return tName;
	}
	public String gettCourseName() {
		return tCourseName;
	}
	public int gettMana() {
		return tMana;
	}
	public String gettTel() {
		return tTel;
	}
	public void settId(String tId) {
		this.tId = tId;
	}
	public void settName(String tName) {
		this.tName = tName;
	}
	public void settCourseName(String tCourseName) {
		this.tCourseName = tCourseName;
	}
	public void settMana(int tMana) {
		this.tMana = tMana;
	}
	public void settTel(String tTel) {
		this.tTel = tTel;
	}
	
	
}
