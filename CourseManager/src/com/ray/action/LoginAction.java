package com.ray.action;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.swing.JOptionPane;

import org.apache.struts2.ServletActionContext;
import com.opensymphony.xwork2.ModelDriven;
import com.ray.enitity.ClassCourseInfo;
import com.ray.enitity.ClassInfo;
import com.ray.enitity.CourseInfo;
import com.ray.enitity.SysUser;
import com.ray.enitity.TeacherInfo;
import com.ray.service.ClassService;
import com.ray.service.ClassServiceImpl;
//import com.ray.service.ManaService;
//import com.ray.service.ManaServiceimpl;
import com.ray.service.SysUserService;
import com.ray.service.SysUserServiceImp;

public class LoginAction implements ModelDriven<SysUser>{
	private SysUser user;
	private SysUserService ls = new SysUserServiceImp();
	HttpSession session  = ServletActionContext.getRequest().getSession();
	//TODO：a、前端传来user的值
	public String  doLogin(){
		
//		JOptionPane.showConfirmDialog(null, user.getUserID()+";"+user.getUserName()+";"+user.getPass());
		//id+密码
		if( (ls.findUserByName( user.getUserName(), user.getPass())) 
			||  ls.findUserById( user.getUserID(), user.getPass())){
			user.setUserType("teacher");
			setUser(user);
			session.setAttribute("userType", user.getUserType());
			//登陆前查询所有数据
				FindAllToSession();
//				System.out.println("------------------查询了嘛？");
			    return "success";
		}
			else{
				return "reLogin";
		}
	}
	
	public String  doAlter(){
//		HttpSession session  = ServletActionContext.getRequest().getSession(false);
//		JOptionPane.showConfirmDialog(null, user.getUserID()+";"+user.getUserName()+";"+user.getPass());
		//id+密码
		if( (ls.findUserByName( user.getUserName(), user.getPass())) 
			||  ls.findUserById( user.getUserID(), user.getPass())){
			user.setPass(ServletActionContext.getRequest().getParameter("newPass"));
			
			ls.alterUserName(user);
			    return "success";
		}
			else{
				return "reLogin";
		}
	}
	public void FindAllToSession(){
		ClassService cs = new ClassServiceImpl();
//		查看全部课程
//		ManaService ms = new ManaServiceimpl();
		try {
			List<ClassInfo> classes= cs.findAllClass();
			List<CourseInfo> courses = cs.findAllCourse();
			List<TeacherInfo> teachers = cs.findAllTeacher();
			List<ClassCourseInfo> classCourses = cs.findAllClassCourse();
			session.setAttribute("classes", classes);
			session.setAttribute("courses", courses);
			session.setAttribute("teachers", teachers);
			session.setAttribute("classCourses", classCourses);
//			for(int i=0;i<classes.size();i++){
//				System.out.println(classes.get(i).getClassName()+";"+classes.get(i).getClassRoom());
//			}
//			for(int i=0;i<courses.size();i++){
//				System.out.println(courses.get(i).getCourseName()+";"+courses.get(i).getCourseTime());
//			}
			for(int i=0;i<teachers.size();i++){
				System.out.println(teachers.get(i).gettId()+";"+teachers.get(i).gettCourseName()+";"+teachers.get(i).gettMana());
			}
//			for(int i=0;i<classCourses.size();i++){
//				System.out.println(classCourses.get(i).gettId()+";"+classCourses.get(i).getCourseName());
//			}
		} catch (SQLException e) {
			JOptionPane.showConfirmDialog(null, "find All  to Session  error!!");
			e.printStackTrace();
		}
	}
	public SysUser getUser() {
		return user;
	}

	public void setUser(SysUser user) {
		this.user = user;
	}


	@Override
	public SysUser getModel() {
		if(null == user){
			user = new SysUser();
			return user;
		}
		return user;
	}
	
}
