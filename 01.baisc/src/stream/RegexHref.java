package stream;

import java.util.regex.*;
class RegexHref {
	public static void main(String[] args) {
		String patternString = 
			"\\s*(href|src)\\s*=\\s*(\"([^\"]*\")|(\'[^\']*\')|([^\'\">\\s]+))"; 
		String text = 
			"<a href=\"http://aaa.htm\">bbb</a> <img src=\"http://bb.com/pic.jpg\">";
		Pattern pattern = Pattern.compile(patternString, 
			Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher( text );
		StringBuffer buffer = new StringBuffer();
		while (matcher.find()) {
			//���������൱��goup(0)
			buffer.append("�񲶵�" + matcher.group()); 
			//�����е�һ����(��2��Բ���Ŷ�Ӧ�ģ�������ַ)
			buffer.append(" ������ַΪ" + matcher.group(2)); 
			buffer.append("\r\n");
		}
		System.out.println(buffer.toString());
	}
}