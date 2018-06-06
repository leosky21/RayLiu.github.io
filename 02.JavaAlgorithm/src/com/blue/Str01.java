package com.blue;

import org.junit.Test;

/*  
基础练习 01字串  

问题描述  
对于长度为5位的一个01串，每一位都可能是0或1，一共有32种可能。它们的前几个是：  

00000  

00001  

00010  

00011  

00100  

请按从小到大的顺序输出这32种01串。  

输入格式  
本试题没有输入。  
输出格式  
输出32行，按从小到大的顺序每行一个长度为5的01串。  
样例输出  
00000  
00001  
00010  
00011  
<以下部分省略>  
*/ 
public class Str01{
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Str01 s = new Str01();
		s.testInteger01();
		s.testInteger02();
//		for (int i=0;i<32;i++ ) {
//			int o = Integer.parseInt(Integer.toOctalString(i));
//			Integer p = i;
//			
//			System.out.println(p.toHexString(i));
//			System.out.println(p.toOctalString(i));
//			System.out.printf("%05d\n",p.byteValue());
//		}
	}
/**
 * int 和 Integer 的区别：
 * - 	int的默认值为0，而Integer的默认值为null，即Integer可以区分出未赋值和值为0的区别，int则无法表达出未赋值的情况
 * 			- 在JSP开发中，Integer的默认为null，所以用el表达式在文本框中显示时，值为空白字符串，
 * 			- 而int默认的默认值为0，所以用el表达式在文本框中显示时，结果为0，所以，int不适合作为web层的表单数据的类型。 
 * - 	在Hibernate中，
 * 			- 如果将OID定义为Integer类型，
 * 				那么Hibernate就可以根据其值是否为null而判断一个对象是否是临时的，
 * 			- 如果将OID定义为了int类型，还需要在hbm映射文件中设置其unsaved-value属性为0。 
 */
	@Test
	private  void testInteger01(){
		Integer a = 1000;
		Integer b = 1000;
		Integer c = 100;
		Integer d = 100;
		System.out.println(a==b);
		System.out.println(c==d);
	}
	/**
	 * 	-  当我们声明Integer c = 10;的时候，此时编译器会进行自动装箱操作，
	 * 简单的说，也就是把基本数据类型转换成Integer对象，而把int型转换成Integer对象正是调用的valueOf(int i)方法。
	 * 	 - 从上面的源码中可以看出，Integer中把-128--127这256个数据缓存了下来。
	 * 	   官方的说法是这些小的数字使用的频率比较高，为了优化性能，就把这之间的数缓存了下来。
	 * 	   这就是为什么输出结果是false和true了。
	 * 当声明的Inetger对象的值在-128--127之间时，引用的是缓存中的同一个对象（已经创建好保存在缓存中），
	 * 所以结果是true；当声明的Integer对象的值不在这个范围之类的时候，就会通过new Integer(i);语句创建一个Integer对象。
	 */
	@Test
	private  void testInteger02(){
		Integer a = new Integer(1000);
//		Integer b = 1000;
		int b = 1000;
		Integer c = new Integer(10);
		Integer d = new Integer(10);
		System.out.println(a==b);
		System.out.println(c==d);
	}
	/**
	 * 
	 * 		这里的Integer对象是我们自己new出来的，并不是用缓存，所以结果是false。
	 * 		那第一个为啥又是true呢？首先这里b的值为1000，肯定跟我们所知的Integer缓存没关系，
	 * 这里b是int型，当int和Integer进行==比较的时候，
	 * Java编译器会把Integer进行自动拆箱，也就是把Integer转为int型，所以这里进行比较的是int型的值，因此结果为true。
	 * 
	 * 如果 换成 Integer b = 1000；则就没有拆箱
	 * 
	 */

}