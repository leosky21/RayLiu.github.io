package cn.ray.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebFilter(
		filterName="CharacterEncodingFilter",
		urlPatterns = "/*",
		initParams={
			@WebInitParam(name="charset",value="UTF-8")
		}
		)
public class CharacterEncodingFilter implements Filter {

	private FilterConfig config;
	private String defaultCharset="UTF-8";
	
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		String charset = this.config.getInitParameter("charset");
		 if(charset!=null){
			 charset = defaultCharset;
		 }
		
		 HttpServletRequest req = (HttpServletRequest)request;
		 HttpServletResponse resp = (HttpServletResponse)response;
		 
		 req.setCharacterEncoding(charset);
		 resp.setCharacterEncoding(charset);
		 resp.setCharacterEncoding("text/html;charset="+charset);
		 
		 chain.doFilter(req, resp);
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		this.config = filterConfig;
		
	}
	

	

}
