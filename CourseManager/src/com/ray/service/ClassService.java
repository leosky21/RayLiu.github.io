package com.ray.service;

import java.sql.SQLException;
import java.util.List;

import com.ray.enitity.ClassCourseInfo;
import com.ray.enitity.ClassInfo;
import com.ray.enitity.CourseInfo;
import com.ray.enitity.TeacherInfo;

public interface ClassService {
	public List<ClassInfo>  findAllClass() throws SQLException;
	public boolean saveClass(ClassInfo classInfo);
	public boolean updateClass(ClassInfo classInfo);
	public String findClassByName(String  className) throws SQLException;
	
	public List<TeacherInfo>  findAllTeacher() throws SQLException;
	public String findTeacherByName(String  teacherName) throws SQLException;
	public boolean saveTeacher(TeacherInfo teacherInfo);
	public boolean updateTeacher(TeacherInfo teacherInfo);
	
	public List<ClassCourseInfo>  findAllClassCourse() throws SQLException;
	public int findClassCourseByID(int id) throws SQLException;
	public int findClassCourseByInfo(String  courseName,String  className,String teacherID) throws SQLException;
	public boolean saveClassCourse(ClassCourseInfo classCourseInfo);
	public boolean updateClassCourse(ClassCourseInfo classCourseInfo);
	
	public List<CourseInfo>  findAllCourse() throws SQLException;
	public boolean updateCourse(CourseInfo courseInfo);
	public boolean saveCourse(CourseInfo courseInfo);
	public String findCourseByName(String courseName) throws SQLException;
}
