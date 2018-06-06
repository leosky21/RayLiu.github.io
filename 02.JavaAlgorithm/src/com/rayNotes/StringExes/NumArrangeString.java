package com.rayNotes.StringExes;

import java.util.Arrays;

/**
 * TODO : String num 排序
 * 练习：
 * 	有这样一个数字字符串：”23 10 -8 0 3 7 108”，
 * 	请对字符串中的数值进行升序排序后，生成一个数值有序的字符串——”-8 0 3 7 10 23 108”。 
 * @author ray
 * @since 2018.1
 *
 */
public class NumArrangeString implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final String SEPARATOR;
	
	public static void main(String[] args) {
		String str = "23 10 -8 0 3 7 108";
		NumArrangeString ast = new NumArrangeString(" ");
		System.out.println(ast.sortNumberString(str));
	}
	
	public NumArrangeString() {
		 this.SEPARATOR = "";
	}
	
	
	public NumArrangeString(String c) {
		 this.SEPARATOR = "".valueOf(c);
	}
	
	/**
	 * String -> sort(String) -> return string
	 * @param str
	 * @return string
	 */
	public String sortNumberString(String str) {
		String str1 = str.trim();
		int[] strInt = toIntArray(toStringArray(str)); 
		sortInt(strInt);
		return toString(strInt);
	}
	
	/**
	 * strs[0] ... strs[strs.length]
	 * @param strInt
	 * @return str1 str2... 
	 */
	public  String toString(int[] strInt) {
		StringBuilder sb = new StringBuilder();
		for(int in : strInt) {
			sb.append(in).append(SEPARATOR);
		}
		return sb.toString();
	}
	/**
	 * 
	 * @param strInt
	 * @return
	 */
	public void sortInt(int[] strInt) {
		 Arrays.sort(strInt);
	}
	
	/**
	 * TODO: String[] --> int[]
	 * @param strs
	 * @return int[]
	 */
	public int[] toIntArray(String[] strs) {
		int[] strInt = new int[strs.length]; 
		int i=0;
		for(String s : strs) {
			strInt[i++] = Integer.parseInt(s);
		}
		return strInt;
	}
	/**
	 * TODO:String --> String[]
	 * @param str
	 * @return String[]
	 */
	public String[] toStringArray(String str) {
		return str.split(SEPARATOR);
	}
	
}
