package com.rayNotes.StringExes;

/**
 * 
 * TODO 字符串可以比较大小吗？如果有！将字符串数组排序。 
 * @author ray
 *
 */
public class CompareString {
	 public static void main(String[] args) {
	        String[] strs = {"haha", "nba", "abc", "cba", "haha", "qq", "hiahia"};

	        printArray(strs);

	        // 对字符串数组排序
	        sort(strs);

	        printArray(strs);

	    }
	public static void sort(String[] strs) {
	        for (int x = 0; x < strs.length - 1; x++) {
	            for (int y = x + 1; y < strs.length; y++) {
	                if (strs[x].compareTo(strs[y]) > 0) {
	                    swap(strs, x, y);
	                }
	            }
	        }
	    }

	    private static void swap(String[] strs, int x, int y) {
	        String temp = strs[x];
	        strs[x] = strs[y];
	        strs[y] = temp;
	    }

	    public static void printArray(String[] strs) {
	        for (int i = 0; i < strs.length; i++) {
	            if (i != strs.length - 1) {
	                System.out.print(strs[i] + ",");
	            } else {
	                System.out.println(strs[i]);
	            }
	        }
	    }

}
