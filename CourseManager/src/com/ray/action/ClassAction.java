package com.ray.action;

import java.io.IOException;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import org.apache.struts2.ServletActionContext;
import com.opensymphony.xwork2.ModelDriven;
import com.ray.enitity.ClassInfo;
import com.ray.service.ClassService;
import com.ray.service.ClassServiceImpl;

public class ClassAction implements ModelDriven<ClassInfo> {
	private ClassInfo classInfo= new ClassInfo();
	private ClassService cspl = new ClassServiceImpl();
/**为什么得不到 班主任和班级人数？？
 * */	
	@Override
	public ClassInfo getModel() {
		if(null == classInfo){
			classInfo = new ClassInfo();
			return classInfo;
		}
		return classInfo;
	}
	/**处理班级信息*/
	public void doManaClass() throws SQLException{
		/**为什么得不到 班主任和班级人数？？
		 * */	
//		JOptionPane.showConfirmDialog(null, ServletActionContext.getRequest().getParameter("cTeacher")+";"
//	+ServletActionContext.getRequest().getParameter("cNum"));
		classInfo.setcTeacher(ServletActionContext.getRequest().getParameter("cTeacher"));
		classInfo.setcNum(Integer.parseInt(ServletActionContext.getRequest().getParameter("cNum")));
		
		if(classInfo.getSelect().equals("add")){
			doAdd();
		}
		else if(classInfo.getSelect().equals("alter")){
			doAlter();
		}else {
			JOptionPane.showConfirmDialog(null, "not add or alter!");
		}
	}

	private void doAlter() throws SQLException {
		boolean  bol = false;
		if(cspl.findClassByName(classInfo.getClassName()) == null){
			bol = true;
		}
		if(bol){
			cspl.updateClass(classInfo);
			outPut("alter success!");
		}else{
			outPut("alter failed,duplicate!");
		}
		
	}

	private void doAdd() throws SQLException {
		boolean  bol = false;
		if(cspl.findClassByName(classInfo.getClassName()) == null){
			bol = true;
		}
		if(bol){
			cspl.saveClass(classInfo);
			outPut("add success!");
		}else{
			outPut("add failed，duplicate!");
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
	
}
