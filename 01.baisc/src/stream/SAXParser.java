package stream;

import java.util.*;
import java.io.*;
import org.xml.sax.*;
import org.xml.sax.helpers.*;

public class SAXParser {
    class BookHandler extends DefaultHandler {
        private List<String> nameList;
        private boolean title = false;

        public List<String> getNameList() {
            return nameList;
        }
        // �������ĵ���ʼ
        @Override
        public void startDocument() throws SAXException {
            System.out.println("Start parsing document...");
            nameList = new ArrayList<String>();
        }
        // �������ĵ�����
        @Override
        public void endDocument() throws SAXException {
            System.out.println("End");
        }

        /**
         * �������ĵ�Ԫ�ؿ�ʼ.
         * @param namespaceURI  ���ƿռ�
         * @param localName  ������
         * @param qName  ��ǰ׺������
         * @param atts  ����
         */
        @Override
        public void startElement(String uri, String localName, String qName,
                                 Attributes atts) throws SAXException {
            // ���д���
            if (qName.equals("title")) {
                title = true;
            }
        }

        @Override
        public void endElement(String namespaceURI, String localName, String qName)
        throws SAXException {
            // ����Ԫ�ؽ���
            if (title) {
                title = false;
            }
        }

        @Override
        public void characters(char[] ch, int start, int length) {
            // ����һ��Ԫ���ڲ����ַ�
            if (title) {
                String bookTitle = new String(ch, start, length);
                System.out.println("Book title: " + bookTitle);
                nameList.add(bookTitle);
            }
        }
    }

    public static void main(String[] args) throws SAXException, IOException {
        XMLReader parser = XMLReaderFactory.createXMLReader();
        BookHandler bookHandler = (new SAXParser()).new BookHandler();
        parser.setContentHandler(bookHandler);
        parser.parse("books.xml");
        System.out.println(bookHandler.getNameList());
    }
}