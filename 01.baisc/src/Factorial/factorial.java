package Factorial;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.*;

public class factorial {
	//阶乘计算的几种方法
	public static int simpleFactor(int num){//简单循环计算阶乘
		int sum = 1;
		if(num<0){//传入负数
			throw new IllegalArgumentException("输入正整数");
		}for(int i=1;i<num;i++){//循环 num
			sum*=i;
		}
		return sum;//返回阶乘的值
		
	}
	public  static int recursion(int num){//递归
		int sum = 1;
		if(num<0)throw new IllegalArgumentException("输入正整数");
		if(num==1)return 1;
		else {
			sum=num*recursion(num-1);
		}
		return num;
	}
	public static long addArray(int num){//数组添加计算阶乘
		int last = 0;
		long arr[] = new long[21];
		arr[0] = 1;
		if(num>=arr.length)throw new IllegalArgumentException("传入值太大");
		if(num<0)throw new IllegalArgumentException("输入正整数");
		//建立满足小于传入数的while循环
		while(last<num){
			arr[last+1] = arr[last]+(last+1);
			last++;
		}
		return arr[num];
	}
	public static synchronized BigInteger bigNumber(int num){//利用bigIntegerbig类进行计算
		/*
		 * Java语言的关键字，当它用来修饰一个方法或者一个代码块的时候，能够保证在同一时刻最多只有一个线程执行该段代码。

     一、当两个并发线程访问同一个对象object中的这个synchronized(this)同步代码块时，一个时间内只能有一个线程得到执行。另一个线程必须等待当前线程执行完这个代码块以后才能执行该代码块。

     二、然而，当一个线程访问object的一个synchronized(this)同步代码块时，另一个线程仍然可以访问该object中的非synchronized(this)同步代码块。

     三、尤其关键的是，当一个线程访问object的一个synchronized(this)同步代码块时，其他线程对object中所有其它synchronized(this)同步代码块的访问将被阻塞。

     四、第三个例子同样适用其它同步代码块。也就是说，当一个线程访问object的一个synchronized(this)同步代码块时，它就获得了这个object的对象锁。结果，其它线程对该object对象所有同步代码部分的访问都被暂时阻塞。

     五、以上规则对其它对象锁同样适用.------后将有针对synchronized 的例子
		 */
		ArrayList list = new ArrayList();//创建集合数组
		list.add(BigInteger.valueOf(1));//调用BigInteger的valueof方法加入一个数值
		for(int i =list.size();i<=num;i++){//此时list只有一个元素
			BigInteger last = (BigInteger) list.get(i-1);//获取地址为 i-1 = 0的第一个元素
			BigInteger next = last.multiply(BigInteger.valueOf(i));
			//获取下一个数组
			list.add(next);
		}
		return (BigInteger) list.get(num) ;
	}
	public static void main(String[] arg){
		int num = 5;
		int num1 = 23;
		System.out.println("simple:"+simpleFactor(num));
		System.out.println("递归："+recursion(num));
		System.out.println("数组："+addArray(num));
		System.out.println("biginteger:"+bigNumber(num1));
	}
}
