package TextControl;

import java.util.Scanner;

public class yueshu{
	// 初始建立两个int类型变量
	static int firstInt;
	static int secondInt;
	public static void main(String[] args) {
		// scanner 类读取后的都是字符串，是String类型，需要转换
		String temp="";
		//新建一个读取键盘输入类 scanner
		Scanner scanner = new Scanner(System.in);
		System.out.println("print in first Integer:");
		// 通过scanner对象 调用nextLine（）方法读取键盘输入的信息
		temp = scanner.nextLine();
		// 强制转换数据类型
		firstInt = Integer.parseInt(temp);
		
		System.out.print("print in second Integer:");
		temp = scanner.nextLine();
		secondInt = Integer.parseInt(temp);
		
		if(firstInt >= secondInt){
			int f = result(firstInt,secondInt);
			System.out.println("最大公约数："+f);
			System.out.println("最大公倍数："+(firstInt*secondInt) / f);
		}else if (firstInt < secondInt) {
			int f = result(secondInt,firstInt);
			System.out.println("最大公约数："+f);
			System.out.println("最大公倍数："+(firstInt*secondInt) / f);
		}
 	}
 	// 如果f>s,则 f % s = 0，那么 s是最大公约数
 	// 否则将重复步骤直到 余数=0
 	public static int result(int f,int s){
 		int temp=0;
 		while(s != 0){
				temp = f % s;
				f = s;
				s = temp;
			}	
			return f;
 	}


}