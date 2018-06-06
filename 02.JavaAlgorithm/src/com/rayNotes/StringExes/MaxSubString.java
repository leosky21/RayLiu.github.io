 package com.rayNotes.StringExes;

import org.junit.Test;
import java.sql.*;
/**
 * TODO 获取两个字符串中最大相同子串。例如，”abcwerthelloyuiodef”和”cvhellobnm”。 
 * @author ray
 * @since 2018.1
 */
public class MaxSubString {
	/**
	 * - 思路：
	 * 		- 判断长短
	 * 		- 依次获取短串的子串，在长串做比较 
	 */
	
	
	/**
	 * judge which is longer
	 * @param str1
	 * @param str2
	 * @return str
	 */
	public String getMaxSubString(String str1,String str2) {
		java.sql.Date date = new Date();
		
		return str1.length()<str2.length() ? str2 : str1;
	}
	/**
	 * judge which is shorter
	 * @param str1
	 * @param str2
	 * @return
	 */
	public String getMinSubString(String str1,String str2) {
		return str1.length()>str2.length() ? str2 : str1;
	}
	/**
	 * ----emmm..
	 * @param s1
	 * @param s2
	 * @return
	 */
	public static String judgeMaxSubString1(String s1, String s2) {
        // 确定哪个是长的哪个是短的
        String longStr, shortStr;
        longStr = s1.length() > s2.length() ? s1 : s2;
        shortStr = s1.equals(longStr) ? s2 : s1;
        // 对短的字符串操作，从短串中取子串，到长字符串中判断，是否存在
        for (int x = 0; x < shortStr.length(); x++) {
            for (int y = 0, z = shortStr.length() - x; z <= shortStr.length(); y++,z++) {
                // 根据y、z获取子串
                String temp = shortStr.substring(y, z);
                 System.out.println(temp+";"+y+":"+z);
                if (longStr.contains(temp)) {
                    return temp;
                }
            }
        }
        
        return null;
    }
	/**
	 * 仍旧 有bug
	 * 
	 * @param str1
	 * @param str2
	 * @return
	 */
	public String judgeMaxSubString(String str1,String str2) {
		String str4 = getMaxSubString(str1,str2);
		String str3 = getMinSubString(str1,str2);
		String str5 = str3.trim();
//		如果是空串也需要处理
		int beginIndex = 0;
		int endIndex = str3.length();
		
		while(!str4.contains(str5)){
			
			while(!str4.contains(str5) && beginIndex<=endIndex) {
				str5 = str3.substring(beginIndex, --endIndex);
				System.out.println("beginIndex----str5:"+str5+"; "+beginIndex+":"+endIndex);
			}
			
			if(endIndex<=1) {
				str5 =str3.substring(beginIndex);
				
				str3 = str5;
				endIndex = str5.length();
			}
			beginIndex=beginIndex+1;
		}
		return str5;
	}
	@Test
	public void test() {
		System.out.println("result:"+
				judgeMaxSubString("abcwerthelloyuiodef","cvhellobnm"));
	}
	public static void main(String[] args) {
		MaxSubString mss = new MaxSubString();
		String s = mss.judgeMaxSubString("abcwerthellobyuiodef", "cvhellobnm");
		System.out.println("result:"+s);
	}
}
