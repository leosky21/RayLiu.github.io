package com.rayNotes.StringExes;

import org.junit.Test;

public class StringBuilderExec {
	
	 /**
	  * 更具体可以看看 com.rayNotes.StringTest;
	  JDK1.5版本之后，出现了StringBuilder，它和StringBuffer用法一样，所以就不再过多讲解了。 
		- StringBuffer与StringBuilder的区别：
			StringBuffer是线程同步的
			StringBuilder是线程不同步的
		一般可以建议选择StringBuilder，因为其速度快。
		但是将StringBuilder的实例用于多个线程时是不安全的。
		如果需要这样的同步，则建议使用StringBuffer。
	  */
		
	
	@Test
	public void method_change() {
		int a = Integer.parseInt("123");
		double b = Double.parseDouble("12.23");
		boolean c = Boolean.parseBoolean("true");

		Integer i = 4; // 自动装箱。Integer.valueOf(4);
		System.out.println(Integer.toBinaryString(6));
		System.out.println(Integer.toBinaryString(-6));
		System.out.println(Integer.toHexString(60));
		
//		将其他进制转成String ，再告诉这是个多少进制，转换成十进制
		int x = Integer.parseInt("11", 20);
		System.out.println("x="+x);
	}
	
}
