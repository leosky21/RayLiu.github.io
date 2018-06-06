package cn.ray.request;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ParamGet01")
public class ParamGet01 extends HttpServlet {

	/**
	 * request对象作为一个域对象(Map容器)使用时，主要是通过以下的四个方法来操作：

			- setAttribute(String name,Object o)方法，将数据作为request对象的一个属性存放到request对象中，
					例如：request.setAttribute(“data”, data);
			
			- getAttribute(String name)方法，获取request对象的name属性的属性值，
					例如：request.getAttribute(“data”);
			
			- removeAttribute(String name)方法，移除request对象的name属性，
					例如：request.removeAttribute(“data”);
			
			- getAttributeNames方法，获取request对象的所有属性名，返回的是一个枚举，
					例如：Enumeration<String> attrNames = request.getAttributeNames();
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String value = request.getParameter("username1");
		System.out.println("--------------_数据获取方式 1 -------------------");
		// 获取请求数据时一般来说都要先检查再使用
        if(value!=null && !value.trim().equals("")) {
            System.out.println(value);
        }
		System.out.println("username1::"+value);
		
		System.out.println("--------------_数据获取方式 2 使用 数组-------------------");
		String[] values = request.getParameterValues("password");
		 // 获取数组数据的技巧，可以避免values数组为null时引发的空指针异常错误！
        for(int i=0;values!=null && i<values.length;i++) {
            System.out.println("values[i]::"+values[i]);
        }
       
        System.out.println("--------------_数据获取方式 3 : 使用 Enumeration-------------------");
        Enumeration<String> e = request.getParameterNames();
        while(e.hasMoreElements()){
        	String name = e.nextElement();//username password
        	value = request.getParameter(name);
        			
        	System.out.println(name+"::"+value);
        }
        
        System.out.println("--------------_数据获取方式 4 : 使用 Bean-------------------");
        
        User user = new User();
        Map<String , String[]> map = request.getParameterMap();
        try{
        	
        	
        	//内省--关于BeanUtil 的使用:https://www.zybuluo.com/liayun/note/403726
        	/**
        	 * 实际开发中比较实用,通过 map集合填充 bean
        	 */
        	//import org.apache.commons.beanutils.BeanUtils;  /lib/commons-beanutils-1.9.3.jar
        	//BeanUtils.populate(user,map);
        	
        	/**
        	 * 实际开发中, 从formBean将属性拷贝到user中,bean的拷贝
        	 */
        	 // BeanUtils.copyProperties(user, formbean);
        }catch (Exception e1) {
			
		}
	}

}

class User{
	private String username1;
	private String username2;
	private String password[];
	public String getUsername1() {
		return username1;
	}
	public String getUsername2() {
		return username2;
	}
	public String[] getPassword() {
		return password;
	}
	public void setUsername1(String username1) {
		this.username1 = username1;
	}
	public void setUsername2(String username2) {
		this.username2 = username2;
	}
	public void setPassword(String[] password) {
		this.password = password;
	}	
}
