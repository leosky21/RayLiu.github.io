package cn.ray.listener;

import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

public class UserBeanHttpSessionBindingListener implements HttpSessionBindingListener{

	@Override
	public void valueBound(HttpSessionBindingEvent arg0) {
		System.out.println("user对象存到session中了！！！");
		
	}

	@Override
	public void valueUnbound(HttpSessionBindingEvent arg0) {
		 System.out.println("user对象从session解除绑定了！！！");
		
	}

}
