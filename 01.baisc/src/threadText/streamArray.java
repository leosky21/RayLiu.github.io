package threadText;

import java.util.*;
/**
 * 集合是常见的 任务，抽取出来	---
 * 			List<Integer> nums = Arrays.asList(1,2,3);	
 * 		nums.stream()
 * 			.forEach(x->{System.out.println(x);});
 * 
 * 将常见的 集合上的操作抽取出来，并且能 连续的进行操作	----java 8 开始 提供“流（Stream）”操作
 * 	-----流程化、流线式----支持  流 上的函数式 风格操作
 * 
 * 得到流 
 * 		Stream<T> stream = collection.stream();
 * 操作流：
 * 		int sumOfWeights = blocks.stream()
 * 								.filter(b->b.getColor()==RED)
 * 								.mapToInt(b -> b.getWeight())
 * 								.sum();
 * @author liujun
 * 
 * 流操作 分为两类 ：
 * 		中间： 中间的流保持打开的状态，并且允许后续的操作
 * 				· 如： filter .sort .limit . map
 * 		末端：  末端的操作必须是 对流的最终操作
 * 				· 如： max  min count  forEach  findAny
 *
 *
 *	如何得到流： 
 *		·对于数组： Arrays.stream(ary)
 *		·对于collection（包括List） 用  list.stream()
 *		·对于map	： 没有流，但是提供了类似的方法：
 *					如： map.putIfAbsent
 *						map.computeIfPresent
 *						map.merge		
 *	只需要将.stream() 换成 .parallelStream() 实现并行计算------stream 就是为了并行计算而生的
 *			-----UseParallelStream.java
 *
 */
class streamArray
{
	public static void main(String[] args) 
	{
		int [] a = new int[100];
		for(int i=0; i<a.length; i++) 
			a[i] = (int)(Math.random()*100);
		
		OptionalInt result=
			Arrays.stream(a).parallel()
			.filter( i -> i>20 )
			.map( i -> i*i )
			.sorted()
			.distinct()
			.limit(10)
			.max();

		System.out.println(
			result.isPresent()? "���ֵΪ"+result.getAsInt(): "��ֵ");
	}
}
