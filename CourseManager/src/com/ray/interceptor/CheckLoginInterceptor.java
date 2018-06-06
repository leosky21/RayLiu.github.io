package com.ray.interceptor;

import org.apache.struts2.ServletActionContext;


import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

/**
 *  interceptor implementation class MethodFilterInterceptor
 */

public class CheckLoginInterceptor extends MethodFilterInterceptor {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4519693479870360311L;

	@Override
	protected String doIntercept(ActionInvocation arg0) throws Exception {
		System.out.println("-----------start-------------------------");
		Object userName = ServletActionContext.getRequest().getSession().getAttribute("userName");
		if(null == userName){
			return "reLogin";
		}
		System.out.println("-----------over-------------------------");
		return arg0.invoke();
	}


}
