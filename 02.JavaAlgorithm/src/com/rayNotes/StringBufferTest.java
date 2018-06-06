package com.rayNotes;

import org.junit.Test;

/**
 * - StringBuffer是字符缓冲区，是一个容器
 * 		- 长度可以变化
 * 		- 可以对内容通过指定的方法进行修改
 * 		- 容器对象一般具备对容器中元素的操作功能，如： 增删改查
 * 		- 最终缓冲区存储完的数据都会变成字符串
 *
 * - 查看API帮助文档，我们发现StringBuffer类有一个空参构造方法，如下：
		StringBuffer()
		API帮助文档对其的解释是：
		构造一个其中不带字符的字符串缓冲区，其初始容量为16个字符。
 *	
 * - 当超出内部数组长度后，
 *		新建数组长度要是原数组的1.x(1.5或者1.75等)倍数，
 *		并将原数组的数据复制到新数组中，并将新的元素也添加到新数组中
 *
 * @author liujun
 *
 */
public class StringBufferTest {
	/**
	 * 		- 先创建一个字符串缓冲区容器
	 *		- 将要组成字符串的元素先存储起来
	 *		- 最后将缓冲区中填充的数据变成字符串
	 */
	String str1 = "a"+4+'c';
	String str2 = new StringBuffer().append("a").append(4).append('c').toString();
	
	/**
	 * 	- 改
	 * 		- replace
	 * 		- setCharAt
	 */
//	@Test
	public void method_update() {
		String sbc="sbc";
		String newstring = sbc.replace('c', 'v');
//		sop(newstring.toString());
//		sop(sbc.toString());
		
		StringBuffer sb = new StringBuffer("abcdef");
//		StringBuffer sb1 = "abcdefs"; // error
		sb.replace(1, 4, sbc);
		sb.setCharAt(0, 'z');
//		sop(sb);
		
		sb.reverse();//反转：容器的操作
//		sop(sb);
		char[] dst = new char[5];
		int dstBegin=0;
		sb.getChars(0, 6, dst, dstBegin);//getchar（）：【】，首地址为0
		sop(dst);
	}
	
	/**
	 * - 删
	 * 		- delete(from,to) 从from起，到to 前
	 */
//	@Test
	public void method_delete() {
		StringBuffer sb = new StringBuffer("abcde");
//		sb.delete(2, 3);//【2，3）
		//清空缓存区 sb = "";
//		sb.delete(0, sb.length());
		sb.deleteCharAt(2);
		sop("delete:sb = "+sb);
	}
	
	/**
	 * 	- 增
	 * 		- append 加到末尾
	 * 		- insert 插入到指定index位置
	 */
//	@Test
	public void method_add() {
		StringBuffer sb = new StringBuffer();
		// 方法调用链
		sb.append("string").append(true);
		StringBuffer sb1 = sb.append(23);
		sop("sb1:"+(sb1==sb));//true
		
		sb.append(23);
		sb.insert(1, "qqq");//可以将数据插入到指定index位置
		sop("sb1:"+sb);
	}
	
	private void sop(String str) {
		System.out.println(str);
	}
	private void sop(StringBuffer str) {
		System.out.println(str);
	}
	private void sop(char[] str) {
		System.out.println(str);
	}
}
