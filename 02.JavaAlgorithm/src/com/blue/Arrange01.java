package com.blue;

public class Arrange01 {
	static int[] first = new int[9];
	static int[] s = new int[9];
	static  int sum=0;
	private static void seek(int code) {
		if(code==9) {
			if(s[0]+s[1]+s[3]+s[5]==s[0]+s[2]+s[4]+s[8]&&
					s[0] + s[1] + s[3] + s[5] == s[5] + s[6] + s[7] + s[8]) {
				sum++;
				return ;
			}
		}
		for(int i=0;i<9;i++) {
			if(first[i]==0) {
				first[i]=1;
				s[code] = i+1;
				seek(code+1);
				first[i]=0;
			}
		}
	}
	
	public static void main(String[] args) {
		seek(0);
		System.out.println(sum/6);
	}
}
