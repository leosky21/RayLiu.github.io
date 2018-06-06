package cn.test;

import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.junit.Test;

import cn.ray.utils.JdbcUtils;
import cn.ray.utils.XmlUtil;

public class TestCase {
	private final String filepath2 = XmlUtil.class.getClassLoader().getResource("").getPath();

	@Test
	public void testGetPath() {
		String filepath = XmlUtil.class.getClassLoader().getResource("db.xml").getPath();
		String filepath2 = XmlUtil.class.getClassLoader().getResource("").getPath();
		System.out.println(File.separator+"path::db.xml = " + filepath2);
	}

//	@Test
//	public void testXmlUtil() {
//		try {
//			XmlUtil.getDocument();
//		} catch (DocumentException e) {
//			e.printStackTrace();
//		}
//	}

	@Test
	public void testXmlUtil2() {
		try {
			XmlUtil.getElementList(filepath2+File.separator+"db.xml");
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testXmlUtil3() {
		List<HashMap<String, String>> users = new ArrayList<HashMap<String, String>>();

		try {
			List<Element> elementList = XmlUtil.getElementList(filepath2+File.separator+"db.xml");
			//System.out.println(elementList.get(0).getStringValue());
			
			if (elementList != null) {
				for (Element e : elementList) {
					
					String index = e.attributeValue("id");
					String module_id = e.attributeValue("id");
					if (module_id.equals(index)) {
						List<Element> userList = e.elements();
							
						System.out.println("userList.size()==="+userList.size());
						System.out.println(e.element("humidity").getText());
						
					}
				}
			}
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testXmlCreate() {
	//	String filepath2 = XmlUtil.class.getClassLoader().getResource("").getPath();
		boolean bol = XmlUtil.createDotXML(filepath2+File.separator+"db.xml", "Element");
		System.out.println(bol);
	}
	
	@Test
	public void testXmlWrite() {
		//String filepath2 = XmlUtil.class.getClassLoader().getResource("").getPath();
		if(XmlUtil.writeDotXML(filepath2+File.separator+"db.xml","1", "2", "3","1","4","5")){
			System.out.println(true);
		}
	}
	
	@Test
	public void testXmlModified() {
		//String filepath2 = XmlUtil.class.getClassLoader().getResource("").getPath();
		if(XmlUtil.writeDotXML(filepath2+File.separator+"db.xml","1", "aaa", "bb","ccc","dd","ee")){
			System.out.println(true);
		}
	}
	@Test
	public void testJdbcUtil() {
		System.out.println(JdbcUtils.class.getClassLoader().getResourceAsStream("db.properties"));
	}
	@Test
	public void testJdbcUtilConn() {
		try {
			JdbcUtils.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
