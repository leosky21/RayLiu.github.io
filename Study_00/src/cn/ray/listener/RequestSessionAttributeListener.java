package cn.ray.listener;

import javax.servlet.ServletRequestAttributeEvent;
import javax.servlet.ServletRequestAttributeListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

@WebListener
public class RequestSessionAttributeListener implements HttpSessionAttributeListener, ServletRequestAttributeListener {

	@Override
	public void attributeAdded(ServletRequestAttributeEvent arg0) {
		System.out.println("向request里增加数据-----------");
	}

	@Override
	public void attributeRemoved(ServletRequestAttributeEvent arg0) {
		System.out.println("从request里删除数据-----------");

	}

	@Override
	public void attributeReplaced(ServletRequestAttributeEvent arg0) {
		System.out.println("从request里替换数据-----------");

	}

	
	
	@Override
	public void attributeAdded(HttpSessionBindingEvent arg0) {
		System.out.println("从session里增加数据-----------");

	}

	@Override
	public void attributeRemoved(HttpSessionBindingEvent arg0) {
		System.out.println("从session里移除数据-----------");

	}

	@Override
	public void attributeReplaced(HttpSessionBindingEvent arg0) {
		System.out.println("从session里替换数据-----------");

	}

}
