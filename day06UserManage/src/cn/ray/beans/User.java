package cn.ray.beans;

import javax.servlet.http.HttpSessionActivationListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;
import javax.servlet.http.HttpSessionEvent;

public class User implements HttpSessionBindingListener,HttpSessionActivationListener {
	private String username;
	private String password;
	
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	@Override
	public void sessionDidActivate(HttpSessionEvent se) {
		System.out.println("User随着session从硬盘回到内存了！！！");
		
	}
	@Override
	public void sessionWillPassivate(HttpSessionEvent se) {
		System.out.println("User随着session从内存到硬盘了！！！");
		
	}
	@Override
	public void valueBound(HttpSessionBindingEvent event) {
		System.out.println("user对象存到session中了！！！");
		
	}
	@Override
	public void valueUnbound(HttpSessionBindingEvent event) {
		System.out.println("user对象从session解除绑定了！！！");
		
	}
	
	
}
