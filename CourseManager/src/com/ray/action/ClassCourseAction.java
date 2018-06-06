package com.ray.action;

import java.io.IOException;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ModelDriven;
import com.ray.enitity.ClassCourseInfo;
import com.ray.service.ClassService;
import com.ray.service.ClassServiceImpl;

public class ClassCourseAction implements ModelDriven<ClassCourseInfo>{
	private ClassService cspl = new ClassServiceImpl();
	private ClassCourseInfo classCourseInfo;
	
	/**处理班级信息*/
	public void doManaClassCourse() throws SQLException{
		
		classCourseInfo.setcName(ServletActionContext.getRequest().getParameter("cName"));
		classCourseInfo.setCourseName(ServletActionContext.getRequest().getParameter("courseName"));
		classCourseInfo.settId(ServletActionContext.getRequest().getParameter("tId"));
		
		if(classCourseInfo.getSelect().equals("add")){
			doAdd();
		}
		else if(classCourseInfo.getSelect().equals("alter")){
			doAlter();
		}else {
			JOptionPane.showConfirmDialog(null, "not add or alter!");
		}
	}

	private void doAlter() throws SQLException {
		boolean  bol = false;
		if(cspl.findClassCourseByInfo(classCourseInfo.getCourseName(),classCourseInfo.getcName(),classCourseInfo.gettId()) == 0){
			bol = true;
		}
		if(bol){
			cspl.updateClassCourse(classCourseInfo);
			outPut("alter success!");
		}else{
			outPut("alter failed,duplicate!");
		}
		
	}

	private void doAdd() throws SQLException {
		boolean  bol = false;
		if(cspl.findClassCourseByID(classCourseInfo.getId()) == 0){
			bol = true;
		}
		if(bol){
			cspl.saveClassCourse(classCourseInfo);
			outPut("add success!");
		}else{
			outPut("add failed,duplicate!");
		}
		
	}

	
	
	private void outPut(String info){
		try {
			ServletActionContext.getResponse().getWriter().printf(info);
		} catch (IOException e) {
			JOptionPane.showConfirmDialog(null, "ClassAction"+info+",有什么错了。。。");
			e.printStackTrace();
		}
		
	}
	
		
	@Override
	public ClassCourseInfo getModel() {
		if(null == classCourseInfo){
			classCourseInfo = new ClassCourseInfo();
		}
		return classCourseInfo;
	}

}
