package stream;
import java.io.*;
import org.w3c.dom.*;
import org.xml.sax.*;
import javax.xml.parsers.*;

public class DOMParser {
    DocumentBuilderFactory builderFactory = 
		DocumentBuilderFactory.newInstance();
    
	//����XML��DOM
    public Document parse(String filePath) {
        Document document = null;
        try {
            //DOM parser ʵ��
            DocumentBuilder builder = builderFactory.newDocumentBuilder();
            //����XML��DOM
            document = builder.parse(new File(filePath));
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return document;
    }

    public static void main(String[] args) {
        DOMParser parser = new DOMParser();
        Document document = parser.parse("books.xml");
        //�õ���Ԫ��
        Element rootElement = document.getDocumentElement();

        //����
        NodeList nodes = rootElement.getChildNodes();
        for (int i=0; i < nodes.getLength(); i++)
        {
            Node node = nodes.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element child = (Element) node;
                //������Խ�һ������
				System.out.println( node.getTextContent() );
            }
        }
		//����ǲ��ҵ�Ԫ��
        NodeList nodeList = rootElement.getElementsByTagName("book");
        if(nodeList != null)
        {
            for (int i = 0 ; i < nodeList.getLength(); i++)
            {
                Element element = (Element)nodeList.item(i);
                String id = element.getAttribute("id");
            }
        }
    }
}