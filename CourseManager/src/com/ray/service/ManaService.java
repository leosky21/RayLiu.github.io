package com.ray.service;

import java.sql.SQLException;
import java.util.List;

import com.ray.enitity.ClassCourseInfo;
import com.ray.enitity.ClassInfo;
import com.ray.enitity.CourseInfo;
import com.ray.enitity.ReadyCourse;
import com.ray.enitity.ReadyTeacher;
import com.ray.enitity.Schedule;
import com.ray.enitity.TeacherInfo;

public interface ManaService {
	public List<ClassInfo> findAllClass() throws SQLException;
	public List<ClassCourseInfo> findAll() throws SQLException;
	public List<TeacherInfo> findAllTeacher() throws SQLException;
	public List<CourseInfo> findAllCourse() throws SQLException;
	public List<ReadyCourse> findReadyCourseViewByClassName(String whichClass) throws SQLException;
	public List<ReadyTeacher> findReadyTeacherViewByClassName(String whichClass) throws SQLException;
	public int addSchedule(List<Schedule> schedule1);
	public int doReset();
	public List<Schedule> findScheduleCourseByClassName(String whichClass) throws SQLException;
} 
