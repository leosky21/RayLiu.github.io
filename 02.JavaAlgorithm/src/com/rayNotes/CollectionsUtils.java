package com.rayNotes;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;

import org.junit.Test;
/**
 *  - JDK 1.5 之后的新特性 ：
 *  			- 静态导入
 *  			- 可变参数
 *  			- foreach 循环
 *  			
 */
import static java.util.Arrays.*; // 导入的是Arrays类中的所有静态成员。
import static java.lang.System.*; // 导入了System类中所有静态成员。

public class CollectionsUtils {
	

	/**	 
	 * 
	 * 	- Collections.synchronizedCollection(list);//将非同步集合转成同步集合的方法。
	 *  
	 *  
	 *  - shuffle(List<?> list)
	 *  		使用默认随机源对指定列表进行置换。所有置换发生的可能性都是大致相等的。
	 */
	@Test
	public void shuttleTest() {
		  List<String> list = new ArrayList<String>();
		 
		    list.add("abcd");
		    list.add("aaa");
		    list.add("zz");
		    list.add("kkkkk");
		    list.add("qq");
		    list.add("z");
		    System.out.println(list);
		    Collections.shuffle(list);

		    System.out.println(list);
		    
		    /**
		     * 
		     * 	- 集合变数组---转换
		     * 		- 指定类型的数组到底要定义多长呢？ 
						* 当指定类型的数组长度小于了集合的size，那么该方法内部会创建一个新的数组，
						* 长度为集合的size。
						* 当指定类型的数组长度大于了集合的size，那么就不会新创建数组，
						* 而是使用传递进来的数组。所以创建一个刚刚好的数组最优。
					- 为什么要将集合变数组？ 
						* 为了限定对元素的操作，不需要进行增删了。

		     */
		    String[] a = new String[0]; 
		    a =  list.toArray(a);
		    
		    System.out.println(a.length+"::"+list.size());
	}
	
	/**
	 * 
	 *  - reverseOrder 反转排序 ---强行将自然顺序反转过来，但他是Comparator
	 *  返回一个比较器，它强行逆转实现了Comparable接口的对象Collection的自然顺序。
	 *  		
	 */
	@Test
	public void reveserOrderTest() {
		 TreeSet<String> ts = new TreeSet<String>();
//		 TreeSet<String> ts = new TreeSet<String>(new StrComparator());
//		 TreeSet<String> ts1 = new TreeSet<String>(Collections.reverseOrder());
//		 TreeSet<String> ts1 = new TreeSet<String>(Collections.reverseOrder(new StrComparator()));
		 TreeSet<String> ts1 = new TreeSet<String>(Collections.reverseOrder(new StrComparatorByLength()));
		    ts.add("abcde"); 	ts1.add("abcde");
		    ts.add("aaa");		ts1.add("aaa");
		    ts.add("k"); 		ts1.add("k");
		    ts.add("cc"); 		ts1.add("cc");
		   
		    Iterator it = ts.iterator();
		    while(it.hasNext()) {
		        System.out.print(it.next()+"::");
		    }
		    System.out.println();
		    Iterator itt = ts1.iterator();
		    while(itt.hasNext()) {
		        System.out.print(itt.next()+"::");
		    }
	}
	/**
	 *  - reveser 反转
	 */
	@Test
	public void reveserTest() {
	    List<String> list = new ArrayList<String>();
	    list.add("abcd");
	    list.add("aaa");
	    list.add("zz");
	    list.add("kkkkk");
	    System.out.println(list);
	    Collections.reverse(list);
	    System.out.println(list);
	}
	
	
	/**
	 * - fill
	 * 		使用指定元素替换指定列表中的所有元素
	 */
	@Test
	public void fillTest() {
		 List<String> list = new ArrayList<String>();
		    list.add("abcd");
		    list.add("aaa");
		    list.add("zz");
		    list.add("kkkkk");
		    System.out.println(list);
		    /**--------------------------分割-------------------------**/
		    CollectionsUtils.fill(list,"pp");
		    System.out.println(list);
		    /**--------------------------分割-------------------------**/
		    Collections.fill(list,"aa");
		    System.out.println(list);
	}
	/**不用取出来，所以没必要管Iterator*/
	public static <T> void fill(List<? super T> list, T obj){
		for(int i=0;i<list.size();i++) {
			list.set(i, obj);
		}
	}
	
	/**
	 * - binarySearch();
	 * 		- 使用二分搜索法搜索指定列表，以获得指定对象。
	 * 			在进行此调用之前，必须根据列表元素的自然顺序对列表进行升序排序（通过sort(List)方法）。
	 * 			如果搜索键包含在列表中，则返回搜索键的索引；否则返回 (-(插入点) - 1)。(-min -1)
	 */
	@Test
	public void binarySearchDemo() {
		List<String> list = new ArrayList<String>();
	    list.add("abcd");
	    list.add("aaa");
	    list.add("zz");
	    list.add("kkkkk");
	    list.add("qq");
	    list.add("z");
	    /**--------------------分割线-------------------**/
	    Collections.sort(list);
	    System.out.println(list);
	    int index = CollectionsUtils.halfSearch(list, "aaa");
	    System.out.println("仿造java的二分：index="+index); // index=0
	   
	    int index3 = Collections.binarySearch(list, "aaa");
	    System.out.println("java自带的：index="+index3); // index=0
	    
	    /**--------------------分割线-------------------**/
	    // 按照既定的规则排序 且 查询
	    Collections.sort(list,new ComparatorByLength() );
	    int index2 = CollectionsUtils.halfSearch(list, "aaa",new ComparatorByLength());
	    System.out.println("自定义构造器的二分法：index="+index2); // index=0
	}
	/**
	 * 	二分法查找：
	 * 		 上面我们说到普通的数据查找算法复杂度是o（n），
	 * 			那么我们可以用上面一样的方法判断一下算法复杂度。
	 * 		这种方法最少是1次，那么最多需要多少次呢？
	 * 			我们发现最多需要log（n+1）/log（2）即可。
	 * 	优点：效率高，时间复杂度为O(logN)；
	 * 	缺点：数据要是有序的，顺序存储。
	 */
	@Test
	public static int halfSearch(List<String> list, String key)	{

		int max, min, mid;
	    max = list.size() - 1;
	    min = 0;
	    while(min <= max) {
	        mid = (max + min) >> 1; // /2
	    System.out.println(" mid:"+mid+"  (max = "+max+" + min="+min+"): ");
	        String str = list.get(mid);
	        int num = str.compareTo(key);
	        if(num > 0)//如果 > 0 ,则正解在str右边：说明 -- key 比str长或者ASCII比Str大 
	            max = mid - 1;
	        else if(num < 0)
	            min = mid + 1;
	        else 
	            return mid;
	    }
	    return -min-1;
	}
	public static int halfSearch(List<String> list, String key,Comparator<String> cmp)	{

		int max, min, mid;
	    max = list.size() - 1;
	    min = 0;
	    while(min <= max) {
	        mid = (max + min) >> 1; // /2
//	    System.out.println(" mid:"+mid+"  (max = "+max+" + min="+min+"): ");
	        String str = list.get(mid);
	        int num = cmp.compare(str,key);
	        if(num > 0)//如果 > 0 ,则正解在str右边：说明 -- key 比str长或者ASCII比Str大 
	            max = mid - 1;
	        else if(num < 0)
	            min = mid + 1;
	        else 
	            return mid;
	    }
	    return -min-1;
	}
	
	@Test
	public void method_sort() {
		 List<String> list = new ArrayList<String>();

		    list.add("abce");
		    list.add("z");
		    list.add("hehe");
		    list.add("nba");
		  System.out.println(list);
		  // 对list排序，自然排序使用的是元素的compareTo方法
		  Collections.sort(list);
		  System.out.println("Collections.sort(list) ::"+list);
		// sort 提供了自定义比较器方法
		  Collections.sort(list,new ComparatorByLength());
		  System.out.println("Collections.sort(list,new ComparatorByLength()) ::"+list);
		  
		  System.out.println();
		  String s = Collections.max(list);
		  System.out.println("Collections.max(list) ::"+s);
		  String s2 = CollectionsUtils.max(list);
		  System.out.println("CollectionsUtils.max(list) ::"+s);
		  String s1 = Collections.max(list,new ComparatorByLength());
		  System.out.println("Collections.max(list,new ComparatorByLength()) ::"+s1);
	}
	
	/**
	 * 
	 * 		TODO 获取最大值
	 * 		- ? extends E：泛型高级(通配符)——泛型的上限
	 * 			- 可以接收E类型或者E类型的子类型，泛型的上限。
	 * 					(俗称：父类)
	 * 		
	 * 		- ? super E：泛型高级(通配符)——泛型的下限
	 * 			- 可以接收E类型或者E的父类型，泛型的下限。
	 * 					(俗称：子类)
	 * 
	 * @param coll
	 * @return 
	 */
	public static <T extends Object & Comparable<? super T>> 
				T  max(Collection<? extends T> coll) {
		Iterator<? extends T> it = coll.iterator();
		
		T max = it.next();
		while(it.hasNext()) {
			T temp = it.next();
			if(temp.compareTo(max) > 0) {
				max = temp;
			}
		}
		return max;
	}
}
/**自定义构造器*/
class ComparatorByLength implements Comparator<String> {
    @Override
    public int compare(String o1, String o2) {
        int temp = o1.length() - o2.length();//按长度升序，其次按字典排序
        return temp == 0 ? o1.compareTo(o2) : temp;
    }
}
class StrComparator implements Comparator<String> {
	@Override
	public int compare(String s1, String s2) {
        // 这样写太繁琐
        /*
        int num = s1.compareTo(s2);
        if(num > 0)
            return -1;
        if(num < 0)
            return 1;
        return num;
        */
        return s2.compareTo(s1); // 简写
    }
}
// 长度第一，字典顺序第二
class StrComparatorByLength implements Comparator<String> {
	@Override
	public int compare(String s1, String s2) {
		int temp = s1.length() - s2.length();
        return temp==0 ? s1.compareTo(s2) : temp; // 简写
    }
}