package com.ray.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.ray.enitity.CourseInfo;
import com.ray.utils.DBOperator;

public class CourseDao {
	DBOperator dbo = new DBOperator();

	public  boolean saveCourse(CourseInfo courseInfo) {
		String sql = "insert into course values (?,?,?)";
		boolean bol = dbo.executeCourseInsert(sql, 
				courseInfo.getCourseName(), courseInfo.getCourseTime(), courseInfo.getIs());
		return bol;
	}
	
	public boolean updateCourse(CourseInfo courseInfo) {
//		String sql = "update  class where CourseName = '"+courseInfo.getCourseName()+"' set"+; 
		//新建一个存储过程，一旦触发本方法，则先删除该条记录，同时新增修改信息	
		return false;
		}
	
	public String findCourseByName(String courseName) throws SQLException {
		
		String sql = "select * from course where CourseName = '"+courseName+"'"; 
		ResultSet rs = dbo.query(sql);
		if(rs.next()){
			return rs.getString("CourseName");
			} 
		else{
			return "";
		}
	}

	public List<CourseInfo> findAllCourse() throws SQLException {
		String sql = "select * from course";
		ResultSet rs= dbo.query(sql);
		List<CourseInfo> courses = new ArrayList<CourseInfo>();
		
		while(rs.next()){
			CourseInfo courseInfo = new CourseInfo();
			courseInfo.setCourseName(rs.getString("CourseName"));
			courseInfo.setCourseTime(rs.getInt("CourseTime"));
			courseInfo.setIs(rs.getInt("CourseIs"));
			courses.add(courseInfo);
		}
		return courses;
		
	}

//	public static void main(String[] args) throws SQLException {
//		CourseDao d= new CourseDao();
//		List<CourseInfo> list= new ArrayList<CourseInfo>(); 
//				list = d.findAllCourse();
//				for(int i=0;i<list.size();i++){
//					System.out.println(list.get(i).getCourseName());
//				}
//	}
}
