 package com.blue;
/**
*基础练习 数列排序  
  
*问题描述  
　　给定一个长度为n的数列，将这个数列按从小到大的顺序排列。
1<=n<=200  
- 输入格式  
　 - 第一行为一个整数n。  
　 - 第二行包含n个整数，为待排序的数，每个整数的绝对值小于10000。  
- 输出格式  
　　- 输出一行，按从小到大的顺序输出排序后的数列。  
样例输入  
5  
8 3 6 4 9  
样例输出  
3 4 6 8 9    
*/
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;

public class NumberArrange{
	public static  void main(String[] args) throws NumberFormatException, IOException  {
		BufferedReader reader=new BufferedReader(new InputStreamReader(System.in)); 
//		Scanner reader = new Scanner(System.in);
		int t = Integer.parseInt(reader.readLine());
//		int t = reader.nextInt();
		int[] tt = new int[t];
		
		String s = reader.readLine();
		
		String[] ss = s.split(" ");

		for (int i=0;i<ss.length;i++ ) {
//			System.out.print(s+"  ");
			tt[i] = Integer.parseInt(ss[i]);
		}
		Arrays.sort(tt);
		for (int i : tt) {
			System.out.print(i+" ");
		}
	}
}
//import java.util.Arrays;
//import java.util.Scanner;
//
//public class NumberArrange {
//    public static void main(String[] args) {
//        Scanner sc=new Scanner(System.in);
//        int n= sc.nextInt();
//        int [] a=new int[n];
//        for(int i=0;i<n;i++){
//            a[i]=sc.nextInt();
//        }
//        Arrays.sort(a);
//        for(int b:a){
//            System.out.print(b+" ");
//        }
//    }
//}