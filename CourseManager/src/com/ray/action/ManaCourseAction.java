package com.ray.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import com.opensymphony.xwork2.ModelDriven;
import com.ray.enitity.Schedule;
//import com.ray.enitity.ReadyClass;
import com.ray.service.ManaService;
import com.ray.service.ManaServiceimpl;
import com.ray.utils.Arrange;

public class ManaCourseAction implements ModelDriven<String>{
	private String select;
	private String showClass;
	Arrange arrange = new Arrange();
	ManaService maservice = new ManaServiceimpl();
	HttpSession session  = ServletActionContext.getRequest().getSession();
	
	public void doArrange() throws SQLException, IOException{
		PrintWriter out = ServletActionContext.getResponse().getWriter();
		//执行排课，且存入数据库
		if(select.equals("add")){
			 arrange.doMana();
//			 out.print(a);
			 
			 ServletActionContext.getRequest().getRequestDispatcher("manaCourse.jsp");
		}else if(select.equals("resetAll")){
			int i = maservice.doReset();
			if(i==1){
				 ServletActionContext.getRequest().getRequestDispatcher("manaCourse.jsp");
			}else{
				out.print("some errors！");
			}
		}
	}
	//获取showclass，选择性生成
	public void doShow() throws SQLException, IOException{
//		PrintWriter out = ServletActionContext.getResponse().getWriter();
		if(showClass.equals("class1")){
//			out.print("class1");
			produceSchedule(showClass);
		}else if(showClass.equals("class2")){
//			out.print("class2");
			produceSchedule(showClass);
		}else if(showClass.equals("class3")){
//			out.print("class3");
			produceSchedule(showClass);
		}else if(showClass.equals("class4")){
//			out.print("class4");
			produceSchedule(showClass);
		}
	}
	
	//查询并生成课表
	private void produceSchedule(String showClass) throws SQLException, IOException{
		try {
			List<Schedule> courseList = maservice.findScheduleCourseByClassName(showClass);
			if(courseList != null){
				for(int i=0;i<courseList.size();i++){
					if(courseList.get(i).getTimeOf()!=null){
						System.out.println(courseList.get(i).getTimeOf()+"-------");
					}
				}
				System.out.println(courseList.get(0).getCName()+"-------");
				session.setAttribute("courseList", courseList);
					ServletActionContext.getRequest().getRequestDispatcher("/scheduleCourseShow.jsp").forward(ServletActionContext.getRequest(), ServletActionContext.getResponse());
				} else {
					ServletActionContext.getRequest().getRequestDispatcher("/scheduleCourseShow.jsp").forward(ServletActionContext.getRequest(), ServletActionContext.getResponse());
				}
			}catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	public String getSelect() {
		return select;
	}

	public void setSelect(String select) {
		this.select = select;
	}

	public String getShowClass() {
		return showClass;
	}
	public void setShowClass(String showClass) {
		this.showClass = showClass;
	}
	@Override
	public String getModel() {
		if(null == select)
			select = ServletActionContext.getRequest().getParameter("select");
			showClass = ServletActionContext.getRequest().getParameter("showClass");
		return select;
	}
}
