package cn.ray;

import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import cn.ray.exception.CustomerException;

public class GlobalExceptionResolver implements HandlerExceptionResolver{

	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
            Exception exception) {
		// 判断异常的种类
        String msg = null;
        if (exception instanceof CustomerException) {
            // 如果是自定义异常，就从异常里面取出错误消息
            CustomerException custExp = (CustomerException) exception;
            msg = custExp.getExpMessage();
        } else {
            // 如果是运行时异常，则取错误的堆栈信息
            exception.printStackTrace(); // 向控制台上打印堆栈信息

            StringWriter s = new StringWriter();
            PrintWriter printWriter = new PrintWriter(s);
            exception.printStackTrace(printWriter);
            msg = s.toString();
        }

        // 写日志、发短信、发邮件
        // 在此省略这一步......

        // 返回一个友好的错误页面，并显示错误消息
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("msg", msg);
        modelAndView.setViewName("error");
        return modelAndView;
	}

}
