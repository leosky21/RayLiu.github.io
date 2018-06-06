package cn.ray.controller;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.ray.utils.XmlUtil;

/**
 * Servlet implementation class InfoGet
 */
@WebServlet("/InfoGet")
public class InfoGetServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private  String filepath2 = XmlUtil.class.getClassLoader().getResource("").getPath();
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String temperature = request.getParameter("temperature");//温度
		String humidity = request.getParameter("humidity");//适度
		String person = request.getParameter("person");//红外监测
		String lat = request.getParameter("lat");//经度
		String lon = request.getParameter("lon");//纬度
		System.out.println("filepath2::"+filepath2);
		System.out.println(temperature +"::" + humidity+"::"+person + "::" +lat+"::"+lon+"::"+new Timestamp(System.currentTimeMillis()));

//		XmlUtil.createDotXML(filepath2+File.separator+"db.xml", "Element");
		XmlUtil.writeDotXML(filepath2+File.separator+"db.xml", "1", temperature, humidity, person,lat,lon);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
