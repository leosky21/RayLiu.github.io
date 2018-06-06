package cn.ray.listener;

import java.io.Serializable;

import javax.servlet.http.HttpSessionActivationListener;
import javax.servlet.http.HttpSessionEvent;

public class UserBeanSessionActivationListener implements HttpSessionActivationListener,Serializable {

	/**
	 * 
	为了观察绑定到HttpSession对象中的javabean对象随HttpSession对象一起被钝化到硬盘上和从硬盘上重新活化回到内存中的的过程，我们需要借助tomcat服务器帮助我们完成HttpSession对象的钝化和活化过程，具体做法如下： 
		在WebRoot\META-INF文件夹下创建一个context.xml文件
	 */
	@Override
	public void sessionDidActivate(HttpSessionEvent arg0) {
		System.out.println("javabean随着session从硬盘回到内存了！！！");
		
	}

	@Override
	public void sessionWillPassivate(HttpSessionEvent arg0) {
		System.out.println("javabean随着session到硬盘中去了！！！");
	}
	
	
}
