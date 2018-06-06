package com.rayNotes.StringExes;

import org.junit.Test;

/**
 * TODO 获取一个字符串在另一个字符串中出现的次数。
 * 		例如，”kk”在”abkkcdkkefkkskk”出现的次数。 
 * 思路：
 *  1. 定义一个计数器 
	2. 获取”kk”第一次出现的位置 
	3. 从第一次出现的位置后剩余的字符串中继续获取”kk”出现的位置，每获取一次就计数一次 
	4. 当获取不到时，计数完成
 * @author  ray
 * @since 2018.1
 */
public class StringKeyCount {
	
	/**
	 * find the times of str2 in str1  
	 * @param str1 
	 * @param str2
	 * @return int
	 */
	public int getKeyCount(String str1,String str2) {
		int count =0;
		int endlength = str1.length();
		int local=str1.indexOf(str2);
		if(local==-1)
			return count;
		else {
			do{
				count++;
			
				local += str2.length();
				
				str1 = str1.substring(local, str1.length());
					
			}while((local =str1.indexOf(str2))!=-1&&local<=endlength);
			return count;
		}
	}
	
	/**
	 * find the times of str2 in str1  
	 * @param str1 
	 * @param str2
	 * @return int
	 */
	public int getKeyCount2(String str1,String str2) {
		int count =0;
		int local=str1.indexOf(str2);
			if(local==-1) {
				return count;
			}
			else {
				do{
					count++;
					local += str2.length();
				}while((local = str1.indexOf(str2,local))!=-1);
			}
			return count;
	}
	@Test
	public void test1() {
		System.out.println(getKeyCount("abkkcdkkefkkskk","kk"));
		System.out.println(getKeyCount2("abkkcdkkefkkskk","kk"));
	}
}
