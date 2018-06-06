package cn.ray.listener;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

import cn.ray.beans.User;

/**
 * Application Lifecycle Listener implementation class Userlistener
 *
 */
@WebListener
public class Userlistener implements HttpSessionAttributeListener {

    /**
     * Default constructor. 
     */
	
    public Userlistener() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see HttpSessionAttributeListener#attributeAdded(HttpSessionBindingEvent)
     */
    public void attributeAdded(HttpSessionBindingEvent arg0)  { 
         
    	Map map = (Map)arg0.getSession().getServletContext().getAttribute("map");
         
         if(map==null){
        	 map = new HashMap();
        	 arg0.getSession().getServletContext().setAttribute("map", map);
         }
         
         if( arg0.getValue() instanceof User){
        	 User user = (User)arg0.getValue();
        	 map.put(user.getUsername(), arg0.getSession());
         }
         
         
    }

	/**
     * @see HttpSessionAttributeListener#attributeRemoved(HttpSessionBindingEvent)
     */
    public void attributeRemoved(HttpSessionBindingEvent arg0)  { 
         // TODO Auto-generated method stub
    }

	/**
     * @see HttpSessionAttributeListener#attributeReplaced(HttpSessionBindingEvent)
     */
    public void attributeReplaced(HttpSessionBindingEvent arg0)  { 
         // TODO Auto-generated method stub
    }
	
}
