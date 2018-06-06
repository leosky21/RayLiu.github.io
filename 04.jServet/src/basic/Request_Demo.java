package basic;

import java.io.IOException;
import javax.servlet.GenericServlet;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;

/**
 * Servlet implementation class Request_Demo
 */
@WebServlet("/Request_Demo")
public class Request_Demo extends GenericServlet  {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see GenericServlet#GenericServlet()
     */
    public Request_Demo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#service(ServletRequest request, ServletResponse response)
	 */
	public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
		ServletOutputStream out = response.getOutputStream();
		request.setCharacterEncoding("UTF-8");
		out.println("infromation about Servlet request:");
		out.println(request.getCharacterEncoding()+"------CharacterEncoding");
		out.println(request.getContentType()+"-------------Contenttype");
//		out.println(request.getLocalAddr()+"-------------返回接受请求的接口的IP地址");
		out.println(request.getLocalAddr()+"-------------reqesust IP");
//		out.println(request.getLocalName()+"-------------返回接受请求的IP主机名");
		out.println(request.getLocalAddr()+"-------------request IP name");
		out.println(request.getProtocol()+"-------------Protocol");
		out.println(request.getContentLength()+"------------textlength");
		out.println(request.getServerName()+"------------server Name");
//		out.println(request.getRemoteAddr()+"------------请求的客户端或者最后一个代理的IP源IP地址");
		
	}

}
