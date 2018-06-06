package cn.ray.file;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Timestamp;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

public class XmlUtil {
	private static String filepath;
	private static List<Element> elementList ;
	static {
		filepath = XmlUtil.class.getClassLoader().getResource("db.xml").getPath();
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
	
	public static List<Element> getElementList() throws DocumentException {
		SAXReader reader = new SAXReader();
		Document document = reader.read(new File(filepath));
		System.out.println("****" + document.getPath() + "");
		
		Element root = document.getRootElement();
        elementList = root.elements();
        
        System.out.println("XMLUtil.readXml root name:" + root.getName());
        //System.out.println("XMLUtil.readXml root name:" + root.getName());
		return elementList;
	}
	
	 public static void write2Xml(Document document) throws Exception, FileNotFoundException {
	        OutputFormat format = OutputFormat.createPrettyPrint();
	       
	        format.setEncoding("UTF-8");
	        XMLWriter writer = new XMLWriter( new FileOutputStream(filepath), format );
	        writer.write( document );
	        writer.close();
	    }
	 
	 public static boolean createUserDotXML(String path,String type) {

	        boolean writen = true;
	        OutputStream outputStream = null;
	        XMLWriter xmlWriter = null;
	        Document document = null;

	        String rootPath = path;

	        try {
	            document = DocumentHelper.createDocument();
	            Element new_root_node = DocumentHelper.createElement(type);
	            Element new_module_1_node = DocumentHelper.createElement("module");

	            new_module_1_node.addAttribute("id","1");          
	            new_root_node.add(new_module_1_node);
	            document.add(new_root_node);

	            OutputFormat outputFormat = new OutputFormat();
	            outputFormat.setEncoding("UTF-8");

	            outputStream = new FileOutputStream(rootPath);
	            xmlWriter = new XMLWriter(outputStream,outputFormat);

	            xmlWriter.write(document);

	        } catch (IOException e){
	           
	            return false;
	        } catch (Exception e){
	            
	            return false;
	        } finally {
	            XmlUtil.close(xmlWriter,outputStream,null);
	        }

	        return writen;
	    }
	 public static boolean writeUserDotXML(String path, String module_id, String userName, String passWord) {

	        boolean writen = true;
	        OutputStream outputStream = null;
	        XMLWriter xmlWriter = null;
	        Document document;

	      //  String rootPath = path;

	        try {
	            document = XmlUtil.getDocument(path);
	            if (document != null) {
	                Element root = document.getRootElement();
	                List<Element> elementList = root.elements();

	                for (Element ele : elementList) {

	                    String id = ele.attributeValue("id");
	                    if (id.equals(module_id)) {

	                        List<Element> userList = ele.elements();

	                        if (userList != null && userList.size() > 0) {

	                            // 进行比对，是否已存在数据
	                            for (Element user : userList) {

	                                Element name = user.element("name");
	                                String nameValue = name.getText();
System.out.println(nameValue);
	                                if (nameValue.equals(userName)) {
	                                    writen = false;
	                                    break;
	                                }
	                            }

	                            // 有需求才新增
	                            if (writen) {

	                                Element new_user_node = DocumentHelper.createElement("user");
	                                Element new_name_node = DocumentHelper.createElement("name");
	                                Element new_pass_node = DocumentHelper.createElement("password");
	                                Element new_date_node = DocumentHelper.createElement("date");

	                                new_name_node.setText(userName);
	                                new_pass_node.setText(passWord);
	                                new_date_node.setText(new Timestamp(System.currentTimeMillis())+"");
	                                new_user_node.addAttribute("index", userList.size() + 1 + "");

	                                new_user_node.add(new_name_node);
	                                new_user_node.add(new_pass_node);
	                                new_user_node.add(new_date_node);

	                                ele.add(new_user_node);
	                            }
	                        } else {

	                            Element new_user_node = DocumentHelper.createElement("user");
	                            Element new_name_node = DocumentHelper.createElement("name");
	                            Element new_pass_node = DocumentHelper.createElement("password");
	                            Element new_date_node = DocumentHelper.createElement("date");

	                            new_name_node.setText(userName);
	                            new_pass_node.setText(passWord);
	                            new_date_node.setText(new Timestamp(System.currentTimeMillis())+"");
	                            new_user_node.addAttribute("index", "1");

	                            new_user_node.add(new_name_node);
	                            new_user_node.add(new_pass_node);
	                            new_user_node.add(new_date_node);

	                            ele.add(new_user_node);
	                        }
	                        break;
	                    }
	                }
	            }

	            if (writen){
	                OutputFormat outputFormat = new OutputFormat();
	                outputFormat.setEncoding("UTF-8");

	                outputStream = new FileOutputStream(path);
	                xmlWriter = new XMLWriter(outputStream,outputFormat);

	                xmlWriter.write(document);
	            }
	        } catch (IOException e){
	           e.printStackTrace();
	           return false;
	        } catch (Exception e){
	        	e.printStackTrace();
	        	 return false;
	        } finally {
	            XmlUtil.close(xmlWriter,outputStream,null);
	        }

	        return writen;
	    }
	 public static void close(XMLWriter xmlWriter, OutputStream outputStream,InputStream inputStream){

	        if (xmlWriter != null){
	            try{
	                xmlWriter.close();
	            } catch (IOException e) {
	               e.printStackTrace();
	            }
	            xmlWriter = null;
	        }

	        if (outputStream != null){
	            try{
	                outputStream.close();
	            } catch (IOException e) {
	            	 e.printStackTrace();
	            }
	            outputStream = null;
	        }

	        if (inputStream != null){
	            try{
	                inputStream.close();
	            } catch (IOException e) {
	            	 e.printStackTrace();
	            }
	            inputStream = null;
	        }
	    }
}
