package com.rayNotes.StringExes;

import org.junit.Test;

/**
 * 
 * TODO 将字符串中指定的部分进行反转，"ab cdef g"--->"ab fed cg"。
 * @author ray
 * @since 2018.1
 */
public class ReverseString {
	
	/**
	 * reverse string  from 0 to str.length
	 * @param str
	 * @return string
	 */
	public String reverseString(String str) {
		return reverseString(str,0,str.length(),true) ;
	}
	/**
	 * String -> char[]
	 * reverse char[] from  offset to  count+offset-1
	 * 
	 * 
	 * @param str
	 * @param offset
	 * @param count
	 * @param bol 	true->all,false->single
	 * @return String
	 */
	public String reverseString(String str,int offset,int count,boolean bol) {
		char[] resStr = str.toCharArray();
		if(bol) {
			reverse(resStr,offset,count);
		}else {
			swap(resStr,offset,count);
		}
		return new String(resStr);
	}
	
	
	/**
	 * reverse from offset ,total number is  count
	 * @param resStr
	 * @param offset
	 * @param count
	 */
	private  void reverse(char[] resStr, int offset, int count) {
		count--;
		for(;offset<count;offset++,count--) {
			swap(resStr,offset,count);
		}
	}
	/**
	 * just reverse fir and sec
	 * @param resStr
	 * @param offset
	 * @param count
	 */
	private void swap(char[] resStr, int fir, int sec) {
		char temp = resStr[fir];
		resStr[fir] = resStr[sec];
		resStr[sec] = temp;
	}

	@Test
	public void test() {
		System.out.println("abcdefg");
		System.out.println(reverseString("abcdefg",2,7,true));
		System.out.println(reverseString("abcdefg",2,7,false));
	}
}
