package cn.ray.web.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.ray.beans.Customer;
import cn.ray.dao.BusinessServiceImpl;
import cn.ray.exception.UserExistException;
import cn.ray.utils.WebUtils;
import cn.ray.web.formbean.RegisterForm;


/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	 protected void doGet(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
	        request.setCharacterEncoding("UTF-8");
	        // 1.对提交表单的字段进行合法性校验
	        RegisterForm form = WebUtils.request2Bean(request, RegisterForm.class);
	        boolean b = form.validate();

	        // 2.如果校验失败，跳回到表单页面，回显校验失败信息
	        if(!b) {
	            request.setAttribute("form", form); 
	            request.getRequestDispatcher("/WEB-INF/jsp/register.jsp").forward(request, response);
	            return;
	        }

	        // 3.如果校验成功，则调用service处理注册请求
	        Customer customer = new Customer();
	        // 从formbean将属性拷贝到user对象中去，bean的拷贝。需要用到BeanUtils的copyProperties()方法
	        WebUtils.copyBean(form, customer);
	        customer.setId(WebUtils.generateID());

	        BusinessServiceImpl service = new BusinessServiceImpl();
	        try {
	            service.register(customer);
	            // 6.如果service处理成功，跳转到网站的全局消息显示页面，为用户注册成功的消息
	            request.setAttribute("message", "恭喜您，注册成功！！！3秒后为您自动跳到登录页面！！<meta http-equiv='refresh' content='3;url="+request.getContextPath()+"/LoginUIServlet'");
	            request.getRequestDispatcher("/message.jsp").forward(request, response);
	            return;
	        } catch (UserExistException e) {
	            // 4.如果service处理不成功，并且不成功的原因是因为注册用户已存在的话，则跳回到注册页面，显示注册用户已存在
	            form.getErrors().put("username", "注册的用户名已存在！！！");
	            request.setAttribute("form", form);
	            request.getRequestDispatcher("/WEB-INF/jsp/register.jsp").forward(request, response);
	            return;
	        } catch (Exception e) {
	            // 5.如果service处理不成功，并且不成功的原因是其他问题的话，跳转到网站的全局消息显示页面，为用户显示友好错误消息
	            e.printStackTrace(); 
	            request.setAttribute("message", "服务器出现未知错误！！！");
	            request.getRequestDispatcher("/message.jsp").forward(request, response);
	            return;
	        }

	    }

	    protected void doPost(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
	        doGet(request, response);
	    }


}
