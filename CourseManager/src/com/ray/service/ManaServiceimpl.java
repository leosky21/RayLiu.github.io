package com.ray.service;

import java.sql.SQLException;
import java.util.List;

import com.ray.dao.ClassCourseDao;
import com.ray.dao.ClassDao;
import com.ray.dao.CourseDao;
import com.ray.dao.ManaDao;
import com.ray.dao.TeacherDao;
import com.ray.enitity.ClassCourseInfo;
import com.ray.enitity.ClassInfo;
import com.ray.enitity.CourseInfo;
import com.ray.enitity.ReadyCourse;
import com.ray.enitity.ReadyTeacher;
import com.ray.enitity.Schedule;
import com.ray.enitity.TeacherInfo;

public class ManaServiceimpl implements ManaService {
	/**操作类*/
	private  ClassDao classDao = new ClassDao();
	private  ClassCourseDao classCourseDao = new ClassCourseDao();
	private  TeacherDao  teacherDao = new TeacherDao();
	private  CourseDao courseDao = new CourseDao();
	private ManaDao manaDao = new ManaDao();
	
	@Override
	public List<ClassInfo> findAllClass() throws SQLException {
		return classDao.findAllClass();
	}
	
	@Override
	public List<ClassCourseInfo> findAll() throws SQLException{
		return classCourseDao.findAll();
	}

	@Override
	public List<TeacherInfo> findAllTeacher() throws SQLException{
		return teacherDao.findAllTeacher();
	}

	@Override
	public List<CourseInfo> findAllCourse() throws SQLException{
		return courseDao.findAllCourse();
	}

	@Override
	public List<ReadyCourse> findReadyCourseViewByClassName(String whichClass) throws SQLException {
		return manaDao.findReadyCourseViewByClassName(whichClass);
	}

	@Override
	public List<ReadyTeacher> findReadyTeacherViewByClassName(String whichClass) throws SQLException {
		return manaDao.findReadyTeacherViewByClassName(whichClass);
	}

	@Override
	public int  addSchedule(List<Schedule> schedule1) {
		 return manaDao.addSchedule(schedule1);	
	}

	@Override
	public int doReset() {
		return manaDao.resetAll();
	}

	@Override
	public List<Schedule> findScheduleCourseByClassName(String whichClass) throws SQLException {
		// TODO Auto-generated method stub
		return manaDao.findReadyCourseByClassName(whichClass);
	}


}
