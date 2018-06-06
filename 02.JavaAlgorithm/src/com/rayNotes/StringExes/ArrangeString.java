package com.rayNotes.StringExes;

import java.util.Arrays;

import org.junit.Test;

/**
 * TODO 对字符串中的字符进行自然排序。
 * @author ray
 * @since 2018.1
 */
public class ArrangeString {
	public char[] toCharArray(String str) {
		return str.toCharArray();
	}
	public String sort(char[] ch) {
		Arrays.sort(ch);
		return new String(ch);
	}
	
	@Test
	public void test() {
		 String str = "jbdsakncv";
		System.out.println( sort(toCharArray(str)));	
	}
	
}
