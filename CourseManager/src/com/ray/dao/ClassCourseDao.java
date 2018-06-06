package com.ray.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ray.enitity.ClassCourseInfo;
import com.ray.utils.DBOperator;

public class ClassCourseDao {
	DBOperator dbo = new DBOperator();

	
	public List<ClassCourseInfo>  findAll() throws SQLException{
		String sql = "select * from ClassCourse";
		ResultSet rs= dbo.query(sql);
		List<ClassCourseInfo> classCourses = new ArrayList<ClassCourseInfo>();
		
		while(rs.next()){
			ClassCourseInfo classCourseInfo = new ClassCourseInfo();
			classCourseInfo.setId(rs.getInt("CCID"));
			classCourseInfo.setcName(rs.getString("CName"));
			classCourseInfo.setCourseName(rs.getString("CourseName"));
			classCourseInfo.settId(rs.getString("TID"));
			classCourses.add(classCourseInfo);
		}
		return classCourses;
	}
	
	public int findClassCourseByName(int id) throws SQLException {
		// TODO findClassCourseByName
		String sql = "select * from ClassCourse where CCID = '"+id+"'"; 
		ResultSet rs= dbo.query(sql);
			while(rs.next())
				return rs.getInt("CCID");
		return 0;
	}

	public int findClassCourseByInfo(String courseName, String className, String teacherID) throws SQLException {
		// TODO findClassCourseByInfo
		String sql = "select * from ClassCourse where CourseName = '"+courseName+"',CName='"+className+"',TID='"+teacherID+"'"; 
		ResultSet rs= dbo.query(sql);
			while(rs.next())
				return rs.getInt("CCID");
		return 0;
	}

	public boolean saveClassCourse(ClassCourseInfo classCourseInfo) {
		// TODO !!!saveClassCourse
		String sql = "insert into ClassCourse(CourseName,CName,TID) values (?,?,?)";
		boolean bol = dbo.executeInsert(sql, 
				classCourseInfo.getCourseName(), classCourseInfo.getcName(), classCourseInfo.gettId());
		return bol;
	}

	public boolean updateClassCourse(ClassCourseInfo classCourseInfo) {
		// TODO !!!updateClassCourse
		return false;
	}
//	public static void main(String[] args) throws SQLException {
//		ClassCourseDao d= new ClassCourseDao();
//		List<ClassCourseInfo> list= new ArrayList<ClassCourseInfo>(); 
//				list = d.findAll();
//				for(int i=0;i<list.size();i++){
//					System.out.println(list.get(i).getcName());
//				}
//	}
}
