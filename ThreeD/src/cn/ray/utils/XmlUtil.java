package cn.ray.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
@SuppressWarnings("unchecked")
public class XmlUtil {
	private static String filepath = XmlUtil.class.getClassLoader().getResource("").getPath() + File.separator
			+ "db.xml";

	private static List<Element> elementList;
	static {

		if (filepath == null) {
			XmlUtil.createDotXML(filepath, "Element");
		}
		System.out.println("path::db.xml = " + filepath);
	}

	public static Document getDocument() throws DocumentException {
		SAXReader reader = new SAXReader();
		Document document = reader.read(new File(filepath));
		System.out.println("****" + document.getPath() + "");

		return document;
	}

	public static Document getDocument(String path) throws DocumentException {
		SAXReader reader = new SAXReader();
		Document document = reader.read(new File(path));
		System.out.println("****" + document.getPath() + "");

		return document;
	}

	
	public static List<Element> getElementList(String filepath) throws DocumentException {
		SAXReader reader = new SAXReader();
		Document document = reader.read(new File(filepath));
		System.out.println("****" + document.getPath() + "");

		Element root = document.getRootElement();
		elementList = root.elements();

		System.out.println("XMLUtil.readXml root name:" + root.getName());

		return elementList;
	}

	public static void write2Xml(Document document) throws Exception, FileNotFoundException {
		OutputFormat format = OutputFormat.createPrettyPrint();

		format.setEncoding("UTF-8");
		XMLWriter writer = new XMLWriter(new FileOutputStream(filepath), format);
		writer.write(document);
		writer.close();
	}

	public static boolean createDotXML(String path, String type) {

		boolean writen = true;
		OutputStream outputStream = null;
		XMLWriter xmlWriter = null;
		Document document = null;

		String rootPath = path;

		try {
			document = DocumentHelper.createDocument();
			Element new_root_node = DocumentHelper.createElement(type);
			Element new_module_1_node = DocumentHelper.createElement("module");

			new_module_1_node.addAttribute("id", "1");
			new_root_node.add(new_module_1_node);
			document.add(new_root_node);

			OutputFormat outputFormat = new OutputFormat();
			outputFormat.setEncoding("UTF-8");

			outputStream = new FileOutputStream(rootPath);
			xmlWriter = new XMLWriter(outputStream, outputFormat);

			xmlWriter.write(document);

		} catch (IOException e) {

			return false;
		} catch (Exception e) {

			return false;
		} finally {
			XmlUtil.close(xmlWriter, outputStream, null);
		}

		return writen;
	}

	// 如果没有则增加,如果有的话则更新
	public static boolean writeDotXML(
			String path, String module_id, 
			String temperature, String humidity,
			String person,
			String lat,String lon
			) {

		boolean writen = true;
		OutputStream outputStream = null;
		XMLWriter xmlWriter = null;
		Document document;
		try {
			document = XmlUtil.getDocument(path);
			if (document != null) {
				Element root = document.getRootElement();
				List<Element> elementList = root.elements();

				for (Element ele : elementList) {

					String id = ele.attributeValue("id");
					if (id.equals(module_id)) {

						List<Element> list = ele.elements();
						System.out.println("writeDotXML : list.size()::" + list.size());

						if (list != null && list.size() > 0) {
							System.out.println("writeDotXML : list.size()::" + list.size());
							// if (writen) {
							// Element new_humidity_node =
							// DocumentHelper.createElement("humidity");
							// Element new_person_node =
							// DocumentHelper.createElement("person");
							// Element new_temperature_node =
							// DocumentHelper.createElement("temperature");
							// new_humidity_node.setText(humidity);
							// new_person_node.setText(person);
							// new_temperature_node.setText(temperature);
							//
							// ele.add(new_humidity_node);
							// ele.add(new_person_node);
							// ele.add(new_temperature_node);
							// }
							document.getRootElement().element("module").element("humidity").setText(humidity);

							document.getRootElement().element("module").element("person").setText(person);

							document.getRootElement().element("module").element("temperature").setText(temperature);
							document.getRootElement().element("module").element("lat").setText(lat);
							document.getRootElement().element("module").element("lon").setText(lon);
						} else {
							if (writen) {
								Element new_humidity_node = DocumentHelper.createElement("humidity");
								Element new_person_node = DocumentHelper.createElement("person");
								Element new_temperature_node = DocumentHelper.createElement("temperature");
								Element new_lat_node = DocumentHelper.createElement("lat");
								Element new_lon_node = DocumentHelper.createElement("lon");
								
								new_humidity_node.setText(humidity);
								new_person_node.setText(person);
								new_temperature_node.setText(temperature);
								new_lat_node.setText(lat);
								new_lon_node.setText(lon);
								
								ele.add(new_humidity_node);
								ele.add(new_person_node);
								ele.add(new_temperature_node);
								ele.add(new_lat_node);
								ele.add(new_lon_node);
							}
							// Element new_humidity_node =
							// DocumentHelper.createElement("humidity");
							// Element new_person_node =
							// DocumentHelper.createElement("person");
							// Element new_temperature_node =
							// DocumentHelper.createElement("temperature");
							//
							// new_humidity_node.setText(humidity);
							// new_person_node.setText(person);
							// new_temperature_node.setText(temperature);
							// ele.add(new_humidity_node);
							// ele.add(new_person_node);
							// ele.add(new_temperature_node);

						}
						break;
					}
				}
			}
			if (writen) {
				OutputFormat outputFormat = new OutputFormat();
				outputFormat.setEncoding("UTF-8");

				outputStream = new FileOutputStream(path);
				xmlWriter = new XMLWriter(outputStream, outputFormat);

				xmlWriter.write(document);
			}
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			XmlUtil.close(xmlWriter, outputStream, null);
		}

		return writen;
	}
//修改
	// public static boolean modifyDotXML(String path, String module_id, String
	// temperature, String humidity,
	// String person) {
	// OutputStream outputStream = null;
	// XMLWriter xmlWriter = null;
	// Document doc;
	// try {
	// doc = XmlUtil.getDocument(path);
	// if
	// (doc.getRootElement().element("module").attributeValue("index").equals("1"))
	// {
	//
	// doc.getRootElement().element("module").element("humidity").setText(humidity);
	//
	// doc.getRootElement().element("module").element("person").setText(person);
	//
	// doc.getRootElement().element("module").element("temperature").setText(temperature);
	//
	// OutputFormat outputFormat = new OutputFormat();
	// outputFormat.setEncoding("UTF-8");
	//
	// outputStream = new FileOutputStream(path);
	// xmlWriter = new XMLWriter(outputStream, outputFormat);
	//
	// xmlWriter.write(doc);
	// }
	// } catch (DocumentException | IOException e) {
	//
	// e.printStackTrace();
	// return false;
	// }
	// return true;
	//
	// }

	public static void close(XMLWriter xmlWriter, OutputStream outputStream, InputStream inputStream) {

		if (xmlWriter != null) {
			try {
				xmlWriter.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			xmlWriter = null;
		}

		if (outputStream != null) {
			try {
				outputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			outputStream = null;
		}

		if (inputStream != null) {
			try {
				inputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			inputStream = null;
		}
	}
}
