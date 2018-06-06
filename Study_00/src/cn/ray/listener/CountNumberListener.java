package cn.ray.listener;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;


@WebListener
public class CountNumberListener implements HttpSessionListener {

	@Override
	public void sessionCreated(HttpSessionEvent se) {
		ServletContext context = se.getSession().getServletContext();
		
		Integer count = (Integer)context.getAttribute("count");
		
		if(count==null){
			count = 1;
			
		}else{
			count++;
		}
		context.setAttribute("count", count);
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		
		ServletContext context = se.getSession().getServletContext();
        Integer count = (Integer) context.getAttribute("count");
        count--;
        context.setAttribute("count", count);
		
	}

	
}
