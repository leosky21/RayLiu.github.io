 package com.blue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Main {

	public static void main(String[] args) throws IOException {
		int  f1=1;
		int  f2=1;
		int f3 = 0;
		BufferedReader scanner = new BufferedReader(new InputStreamReader(System.in));
		String s = scanner.readLine();
		int input = Integer.parseInt(s);
		if(input<3) {
			System.out.print("1");
		return ;
		}
		if(input>2) {
			for(int i=3;i<=input;i++) {
				if(f2>10007) {
					f2=f2%10007;
				}
				if(f1>10007) {
					f1=f1%10007;
				}
				f3 = f2+f1;
				f1=f2;
				f2=f3;
				}	
		}
		System.out.println(f3%10007);
		
	}
}


 
