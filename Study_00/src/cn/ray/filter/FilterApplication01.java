package cn.ray.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class FilterApplication01 implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain arg2)
			throws IOException, ServletException {
		// 做全局性的设置(对request和response进行一些预处理)
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=UTF-8");
		
		System.out.println("FilterTest1执行之前！！！");
        // 拦截下目标资源，然后放行(目标资源也会执行)
        arg2.doFilter(req, resp); // 放行
        System.out.println("FilterTest1执行之后！！！");
        
        //filter可以在放行之后，可以捕获到目标资源的输出，从而对输出做出类似于压缩这样的设置。
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}
