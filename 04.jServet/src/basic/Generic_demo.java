package basic;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;

/**
 * Servlet implementation class Generic_dmeo
 */
@WebServlet("/Generic_demo")
public class Generic_demo extends GenericServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see GenericServlet#GenericServlet()
     */
    public Generic_demo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#service(ServletRequest request, ServletResponse response)
	 */
	public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
		doReponse("Hello::::",response);
	}
	
	public String getServletInfo() {
		return "Generic  Ray'demo刘骏";
	}
	
	public void doReponse(String str,ServletResponse res) throws ServletException,IOException {
		PrintWriter out = res.getWriter();
		out.println(str);
		out.println(getServletInfo());
		out.close();
	}
}
