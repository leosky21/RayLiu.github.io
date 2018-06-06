package com.rayNotes.StringExes;

import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

import org.junit.Test;

public class ComparatorStringByLength {
/**
 *  练习一：对多个字符串(不重复)按照长度排序(由短到长)
 *  练习二：对多个字符串(重复)，按照长度排序。
 */
	public void repeatedString() {
	/**
	 *  思路：
     *     1，多个字符串，需要容器存储。
     *     2，选择哪个容器。字符串是对象，可以选择集合，而且不重复，选择Set集合
     *     3，还需要排序，可以选择TreeSet集合。
	 */
		Set set = new TreeSet(new ComparatorByLength()); 
		set.add("haha");
        set.add("abc");
        set.add("zz");
        set.add("nba");
        set.add("xixixi");
        for(Object s : set) {
        		System.out.println(s);
        }
		
	}
	@Test
	public void unRepeatedString() {
		/**
		 *  思路：
	     *     1，多个字符串，需要容器存储。
	     *     2，选择哪个容器。字符串是对象，可以选择集合，而且重复，不可以使用TreeSet，可以考虑 数组和 ArrayList
	     *     3，长度排序---需要比较器
		 */
		String[] strs = {"nba", "haha", "abccc", "zero", "xixi", "nba", "abccc", "cctv", "zero"};
		ComparatorByLength com = new ComparatorByLength();
		// 排序就需要嵌套循环，位置置换
        for (int x = 0; x < strs.length - 1; x++) {
            for (int y = x + 1; y < strs.length; y++) {
                 
            	if (strs[x].compareTo(strs[y]) > 0) { // 按照字典顺序

//                if (com.compare(strs[x], strs[y]) > 0) { // 按照长度顺序
                    swap(strs, x, y);
                }
            }
        } 
        for (String s : strs) {
            System.out.println(s);
        }
	}
	private static void swap(String[] strs, int x, int y) {
        String temp = strs[x];
        strs[x] = strs[y];
        strs[y] = temp;
    }
}
class ComparatorByLength implements Comparator{

	@Override
	public int compare(Object o1, Object o2) {
		String str1 = (String) o1;
		String str2 = (String) o2;
		int temp = str1.length() - str2.length();
//		长度相同，再按字典排序
		return temp==0 ? str1.compareTo(str2): temp;
	}
	
}