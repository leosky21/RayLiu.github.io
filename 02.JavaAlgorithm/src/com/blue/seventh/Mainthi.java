/*
凑算式

     B      DEF
A + --- + ------- = 10
     C      GHI
     
（如果显示有问题，可以参见【图1.jpg】）
     
     
这个算式中A~I代表1~9的数字，不同的字母代表不同的数字。

比如：
6+8/3+952/714 就是一种解法，
5+3/1+972/486 是另一种解法。

这个算式一共有多少种解法？

注意：你提交应该是个整数，不要填写任何多余的内容或说明性文字。
*/
public class Mainthi{

	public static void main(String[] args) {
		Mainthi m = new Mainthi();
		//m.method1();
		//int count = 
		m.method2(1);
	}
	boolean[] k = new boolean[10];
	int[] g = new int[10];
	int count =0;
	private void  method2(int m){
		if(m<=9){
			for(int i=1;i<=9;i++){
				if(!k[i]){
					k[i] = true;
					g[m]=i;
					method2(m+1);
					k[i] = false;
				}
				System.out.println("第"+m+"次"+"::"+count);
				if( g[1]+ (g[2]*1.00 / g[3]) +(100*g[4]+10*g[5]+g[6])*1.00/(100*g[7]+10*g[8]+g[9]) == 10){
					count++;
				}
			}	
		}
	}
	private void  method1(){
		double aa,bc,defghi;int end = 0;
		for(int a=1;a<=9;a++){//1
			aa = a;
			for(int b=1;b<=9;b++){//2
				if(b==a) continue;
				for(int c=1;c<=9;c++){//3
					if(c==b||c==a) continue;
					bc = b*1.00/c;			//**1.00***很重要

					for(int d=1;d<=9;d++){//4
						if(d==c||d==b||d==a) continue;
						for(int e=1;e<=9;e++){//5
							if(e==d||e==c||e==b||e==a) continue;
							for(int f=1;f<=9;f++){//6
								if(f==e||f==d||f==c||f==b||f==a) continue;
								

								for(int g=1;g<=9;g++){//7
									if(g==f||g==e||g==d||g==c||g==b||g==a) continue;
									for(int h=1;h<=9;h++){//8
										if(h==g||h==f||h==e||h==d||h==c||h==b||h==a) continue;
										for(int i=1;i<=9;i++){//9
											if(i==h||i==g||i==f||i==e||i==d||i==c||i==b||i==a) continue;
											
											defghi = (100*d+10*e+f)*1.00/(100*g+10*h+i);  //**1.00***很重要

											if(aa+bc+defghi == 10){
												end++;
												//System.out.println(""+a+b+c+d+e+f+g+h+i);
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
		System.out.println(end);
	}
}