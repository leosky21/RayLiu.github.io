package com.rayNotes.StringExes;

/**
 * TODO 模拟一个trim()方法，去除字符串两端的空格。 
 * @author liujun
 * @since 2018.1
 */
public class StringTrimTest {
	
	public static void main(String[] args) {
		String str = "    a s c      ";
		String str1 = "";
		StringTrimTest stt = new StringTrimTest();
		System.out.println("("+str+")");
		System.out.println("("+stt.myTrim(str)+")");
		System.out.println("("+stt.myTrim(str1)+")");
	}
	public String myTrim(String str) {
		int start=0;
		int end=str.length()-1;
		while(start <= end && str.charAt(start) == ' ' ) 
			start++;
		while(start <= end && str.charAt(end) == ' ' ) 
			end--;
		/**
		 * 官方文档：
		 *   ((start > 0) || (end < str.length())) 的判断确保可能出现的意外
		 *   ----- 可能出现在什么场景呢？
		 *   		
		 */
		return ((start > 0) || (end < str.length())) ? str.substring(start, end+1) : this.toString();
	}
}
