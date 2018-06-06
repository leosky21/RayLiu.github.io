package com.ray.service;

import java.sql.SQLException;
import java.util.List;

import com.ray.dao.ClassCourseDao;
import com.ray.dao.ClassDao;
import com.ray.dao.CourseDao;
import com.ray.dao.TeacherDao;
import com.ray.enitity.ClassCourseInfo;
import com.ray.enitity.ClassInfo;
import com.ray.enitity.CourseInfo;
import com.ray.enitity.TeacherInfo;

public class ClassServiceImpl implements ClassService {
	ClassDao cd = new ClassDao();
	TeacherDao td = new TeacherDao();
	CourseDao cda = new CourseDao();
	ClassCourseDao ccd = new ClassCourseDao();
	
/**class*/
	@Override
	public boolean saveClass(ClassInfo classInfo) {
		
		return cd.saveClass(classInfo);
	}

	@Override
	public boolean updateClass(ClassInfo classInfo) {
		
		return cd.updateClass(classInfo);
	}

	@Override
	public String findClassByName(String className) throws SQLException {
		
		return cd.findClassByName(className);
	}
/**teacher*/
	@Override
	public String  findTeacherByName(String teacherName) throws SQLException {
		
		return td.findTeacherByName(teacherName);
	}

	@Override
	public boolean saveTeacher(TeacherInfo teacherInfo) {
		
		return td.saveTeacher(teacherInfo);
	}
	
	@Override
	public boolean updateTeacher(TeacherInfo teacherInfo) {
		
		return td.updateTeacher(teacherInfo);
	}
/**class Course*/
	@Override
	public int findClassCourseByID(int id) throws SQLException {
		// TODO Auto-generated method stub
		return ccd.findClassCourseByName(id);
	}

	@Override
	public int findClassCourseByInfo(String courseName, String className, String teacherID) throws SQLException {
		// TODO Auto-generated method stub
		return ccd.findClassCourseByInfo(courseName,className,teacherID);
	}

	@Override
	public boolean saveClassCourse(ClassCourseInfo classCourseInfo) {
		// TODO Auto-generated method stub
		return ccd.saveClassCourse(classCourseInfo);
	}

	@Override
	public boolean updateClassCourse(ClassCourseInfo classCourseInfo) {
		// TODO Auto-generated method stub
		return ccd.updateClassCourse(classCourseInfo);
	}
/**Course*/

	@Override
	public boolean updateCourse(CourseInfo courseInfo) {
		// TODO Auto-generated method stub
		return cda.updateCourse(courseInfo);
	}

	@Override
	public boolean saveCourse(CourseInfo courseInfo) {
		// TODO Auto-generated method stub
		return cda.saveCourse(courseInfo);
	}

	@Override
	public String findCourseByName(String courseName) throws SQLException {
		// TODO Auto-generated method stub
		return cda.findCourseByName(courseName);
	}
	
	/**find all*/

	@Override
	public List<ClassInfo> findAllClass() throws SQLException {
		
		return cd.findAllClass();
	}

	@Override
	public List<TeacherInfo> findAllTeacher() throws SQLException {
		// TODO Auto-generated method stub
		return td.findAllTeacher();
	}

	@Override
	public List<ClassCourseInfo> findAllClassCourse() throws SQLException {
		// TODO Auto-generated method stub
		return ccd.findAll();
	}

	@Override
	public List<CourseInfo> findAllCourse() throws SQLException {
		// TODO Auto-generated method stub
		return cda.findAllCourse();
	}
	
	
}
