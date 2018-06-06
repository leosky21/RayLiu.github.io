package com.ray.action;

import java.io.IOException;

import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.swing.JOptionPane;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ModelDriven;
import com.ray.enitity.CourseInfo;
import com.ray.service.ClassService;
import com.ray.service.ClassServiceImpl;

public class CourseAction implements ModelDriven<CourseInfo>{
private ClassService cspl = new ClassServiceImpl();
private CourseInfo courseInfo;
	@Override
	public CourseInfo getModel() {
		if(null == courseInfo){
			courseInfo = new CourseInfo();
			return courseInfo;
		}
		return courseInfo;
	}
	/**处理班级信息
	 * @throws ServletException */
	public void doManaCourse() throws SQLException, ServletException{
//		JOptionPane.showConfirmDialog(null, courseInfo.getSelect()+"");
//		int is=-1;
		if(courseInfo.getCourseIS().equalsIgnoreCase("true")){
			courseInfo.setIs(1);
			if(courseInfo.getSelect().equals("add")){
				doAdd();
			}
			else if(courseInfo.getSelect().equals("alter")){
				doAlter();
			}else {
				JOptionPane.showConfirmDialog(null, "not add or alter!");
			}
		}
		
		else if(courseInfo.getCourseIS().equalsIgnoreCase("false")){
			courseInfo.setIs(0);
			if(courseInfo.getSelect().equals("add")){
				doAdd();
			}
			else if(courseInfo.getSelect().equals("alter")){
				doAlter();
			}else {
				JOptionPane.showConfirmDialog(null, "not add or alter!");
			}
		}
		
	}

	private void doAlter() throws SQLException, ServletException {
		boolean  bol = false;
		if(cspl.findCourseByName(courseInfo.getCourseName()) == null){
			bol = true;
		}
		if(bol){
			cspl.updateCourse(courseInfo);
			outPut("alter success!");
		}else{
			outPut("alter,duplicate");
		}
		
	}

	private void doAdd() throws SQLException, ServletException {
		boolean  bol = false;
		if(cspl.findCourseByName(courseInfo.getCourseName()) == ""){
			bol = true;
		}
		if(bol){
			cspl.saveCourse(courseInfo);
			outPut("add success!");
		}else{
			outPut("add failed,duplicate!");
		}
		
	}

	
	private void outPut(String info) throws ServletException{
		try {
//			ServletActionContext.getRequest().setAttribute("result", info);
//			ServletActionContext.getRequest().getRequestDispatcher("/result.jsp").forward(ServletActionContext.getRequest(),ServletActionContext.getResponse());
			ServletActionContext.getResponse().getWriter().printf(info);
		} catch (IOException e) {
			JOptionPane.showConfirmDialog(null, "CourseAction"+info+",有什么错了。。。");
			e.printStackTrace();
		}
		
	}
	
}
