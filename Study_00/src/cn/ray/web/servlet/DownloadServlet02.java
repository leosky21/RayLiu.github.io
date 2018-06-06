package cn.ray.web.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DownloadServlet02 extends HttpServlet {
	/**
		 * The doGet method of the servlet. <br>
		 *
		 * This method is called when a form has its tag value method equals to get.
		 * 
		 * @param request the request send by the client to the server
		 * @param response the response send by the server to the client
		 * @throws ServletException if an error occurred
		 * @throws IOException if an error occurred
		 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		URL url = new URL("http://localhost:8080/Study_00/DownloadServlet");
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();//开始连接
		
		 conn.setRequestProperty("Range", "bytes=5-");//从第五个字节开始
		
		 InputStream in = conn.getInputStream();
		int len=0;
		byte[] buffer = new byte[1024];
		ServletOutputStream out = response.getOutputStream();
		while((len = in.read(buffer))!=-1){
			out.write(buffer,0,len);
		}
		in.close();
		out.close();
	}

	/**
		 * The doPost method of the servlet. <br>
		 *
		 * This method is called when a form has its tag value method equals to post.
		 * 
		 * @param request the request send by the client to the server
		 * @param response the response send by the server to the client
		 * @throws ServletException if an error occurred
		 * @throws IOException if an error occurred
		 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}

}
