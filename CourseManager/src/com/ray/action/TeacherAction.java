package com.ray.action;

import java.io.IOException;

import java.sql.SQLException;

import javax.swing.JOptionPane;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ModelDriven;

import com.ray.enitity.TeacherInfo;
import com.ray.service.ClassService;
import com.ray.service.ClassServiceImpl;

public class TeacherAction implements ModelDriven<TeacherInfo>{
	private TeacherInfo tInfo;
	private ClassService cspl = new ClassServiceImpl();
	//获取周工作安排，通过String[] checkbox= request.getParameterValues("workday");
	
	/**处理教师信息*/
	public void doManaTeacher() throws SQLException{
//转化工作安排
//		String[] abc =  ServletActionContext.getRequest().getParameterValues("workday");
		
		switchWorkday(ServletActionContext.getRequest().getParameterValues("workday"));
		tInfo.settId(ServletActionContext.getRequest().getParameter("tId"));
		tInfo.settCourseName(ServletActionContext.getRequest().getParameter("courseName"));
		tInfo.settName(ServletActionContext.getRequest().getParameter("tName"));
		tInfo.settTel(ServletActionContext.getRequest().getParameter("tTel"));
		
		if(tInfo.getSelect().equals("add")){
			doAddTeacher();
		}
		else if(tInfo.getSelect().equals("alter")){
			doAlterTeacher();
		}else {
			JOptionPane.showConfirmDialog(null, "not add or alter!");
		}
	}

	private void doAlterTeacher() throws SQLException {
		boolean  bol = false;
		if(cspl.findTeacherByName(tInfo.gettName()) == null){
			bol = true;
		}
		if(bol){
			cspl.updateTeacher(tInfo);
			outPut("alter success!");
		}else{
			outPut("alter failed,duplicate!");
		}
		
	}

	private void doAddTeacher() throws SQLException {
		boolean  bol = false;
		
		if(cspl.findTeacherByName(tInfo.gettName()) == null){
			bol = true;
		
		}
		if(bol){
			cspl.saveTeacher(tInfo);
			outPut("add success!");
		}else{
			outPut("add failed,duplicate!");
		}
		
	}
	/**强转workday*/
	private void switchWorkday(String[] checkboxValue){
		int workDay = 0;
		String workDaystr = "";
		for(String cv : checkboxValue){
			workDaystr += cv;
		}
		workDay = Integer.parseInt(workDaystr);
//		JOptionPane.showConfirmDialog(null, workDaystr+";"+workDay);
		tInfo.settMana(workDay);
	
	}
	
	private void outPut(String info){
		try {
			ServletActionContext.getResponse().getWriter().printf(info);
		} catch (IOException e) {
			JOptionPane.showConfirmDialog(null, "TeacherAction"+info+",有什么错了。。。");
			e.printStackTrace();
		}
		
	}

	@Override
	public TeacherInfo getModel() {
		if(null == tInfo){
			tInfo = new TeacherInfo();
			return tInfo;
		}
		return tInfo;
	}
}
