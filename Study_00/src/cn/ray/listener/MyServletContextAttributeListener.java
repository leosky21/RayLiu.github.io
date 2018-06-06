package cn.ray.listener;

import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class MyServletContextAttributeListener implements ServletContextAttributeListener{

	@Override
	public void attributeAdded(ServletContextAttributeEvent scae) {
		 // 通过事件对象scae可获知到底往ServletContext域里面存了哪个属性以及其属性值
        String name = scae.getName();
        Object value = scae.getValue();
        System.out.println("向ServletContext域里面存了：" + name.toString() + "=" + value.toString());
	}

	@Override
	public void attributeRemoved(ServletContextAttributeEvent scae) {
		System.out.println("从ServletContext域里面删除了：" + scae.getName() + "属性"+scae.getValue());
		
	}

	@Override
	public void attributeReplaced(ServletContextAttributeEvent scae) {
		// TODO Auto-generated method stub
		System.out.println("ServletContext域里面" + scae.getName() + "属性被替换了");
	}

	
}
