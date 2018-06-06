package cn.ray.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dom4j.DocumentException;
import org.dom4j.Element;

import cn.ray.utils.XmlUtil;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class DataGet
 * 
 *  将单片机的数据读取出来 ,放进json 
 */
@WebServlet("/DataGet")
public class DataGet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String filepath2 = XmlUtil.class.getClassLoader().getResource("").getPath();
	private HashMap<String, String> hashMap;
	private PrintWriter out;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// String module_id = request.getParameter("id");

		response.setHeader("ContentType", "apolication/json; charset=UTF-8");
		try {
			List<Element> elementList = XmlUtil.getElementList(filepath2 + File.separator + "db.xml");
			if (elementList != null) {
				for (Element e : elementList) {
					String index = e.attributeValue("id");
					if ("1".equals(index)) {
						hashMap = new HashMap<String, String>();

						String humidityValue = e.element("humidity").getText();
						String temperatureValue = e.element("temperature").getText();
						String person = e.element("person").getText();
						String lat = e.element("lat").getText();
						String lon = e.element("lon").getText();
					
						System.out.println(humidityValue + "::" + temperatureValue+"::"+lon+"::"+lat);
						
						hashMap.put("humidity", humidityValue);
						hashMap.put("temperature", temperatureValue);
						hashMap.put("person",person);
						hashMap.put("lat", lat);
						hashMap.put("lon", lon);
					}
				}
			} else {
				JSONObject reJson = JSONObject.fromObject("error");
				out = response.getWriter();
				out.append(reJson.toString());
				return;
			}

		} catch (DocumentException e) {
			e.printStackTrace();
		}
		JSONObject reJson = JSONObject.fromObject(hashMap);
		out = response.getWriter();
		out.append(reJson.toString());

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
