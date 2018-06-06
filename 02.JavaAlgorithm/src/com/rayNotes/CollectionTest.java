package com.rayNotes;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Set;
import java.util.TreeSet;

import org.junit.Test;
/**
 * 原文地址：http://blog.csdn.net/yerenyuan_pku/article/details/53915999
 * 集合入门综述
 * 
 * JDK1.2以后出现的集合框架中的常用子类对象，存在的子类规律可总结为：前缀名是数据结构名，后缀名是所属体系名。如：

	-	ArrayList 
		数组结构。看到数组，就知道查询快；看到List，就知道可以重复，可以增删改查。
	-	LinkeList 
		链表结构。增删快。
	-	HashSet 
		哈希表，就要想到元素必须覆盖hashCode()和equals()方法，查询速度更快。不保证有序，看到set，就知道不可以重复。
	-	LinkedHashSet 
		链表+哈希表。可以实现有序，因为有链表。
		TreeSet 
		二叉树，可以排序。就要想到两种比较方式：一种是自然排序Comparable，一种是比较器Comparator。
 * @author liujun
 *
 */

@SuppressWarnings({"unchecked","rawtypes"})
public class CollectionTest {
	/**
	 * TODO 6、 set接口
	 * 		- Set集合不允许存储重复元素，而且不保证元素是有序的
	 * 			(存入和取出的顺序有可能一致[有序]，也有可能不一致[无序])。 
	 * 		- Set集合的功能和Collection的是一致的,
	 * 			所以Set集合取出的方法只要一个，那就是迭代器。
	 			* - HashSet
	 			*  		此类实现Set接口，由哈希表（实际上是一个HashMap实例）支持。
	 			*  		它不保证set的迭代顺序，特别是它不保证该顺序恒久不变。
	 			*  		此类允许使用null元素。
	 			*  总结：
	 			*  	- 保证元素唯一性的方式依赖于hashCode()与equals()方法
	 			*  	- HashSet集合不能保证元素的迭代顺序与元素存储顺序相同
	 			*  
	 			* - LinkedHashSet
	 			*  		具有可预知迭代顺序的Set接口的哈希表和链接列表实现。
	 			*  		此实现与HashSet的不同之外在于，后者维护着一个运行于所有条目的双重链接列表。
	 			*  总结：
	 			*  	- 一个特殊的Set集合，而且是有序的，底层是一个双向链表
	 			*  
	 			* - TreeSet 
	 			* 		- Set接口中常用的类
	 			* 		- TreeSet是线程不同步的，可以对Set集合中的元素进行排序。
	 			* 			底层数据结构是二叉树（也叫红黑树），
	 			* 			保证元素唯一性的依据是：compareTo()方法return 0。
	 			* 	- TreeSet对集合中的元素进行排序的方式有两种
	 			* 		- TreeSet排序的第一种方式:
	 			* 		!!! --让元素自身具备比较性。
	 			* 					元素需要实现 **Comparable接口，覆盖compareTo()方法**。
	 			* 					@see Student
	 			* 					这种方式也称为元素的自然顺序，或者也叫默认顺序。
	 			* 		- TreeSet排序的第二种方式： 《 比较器更为灵活，自然排序通常都作为元素的默认排序。》
	 			* 					当元素自身不具备比较性时，或者具备的比较性不是所需要的，
	 			* 					这时就需要让集合自身具备比较性。在集合一初始化时，就具备比较方式.
	 			* 				即：
	 			* 					定义一个比较器实现Comparator接口，覆盖compare方法，
	 			* 					将Comparator接口的对象作为参数传递给TreeSet集合的构造函数。
	 			* 
	 * 
	 * 	- Hashset是如何保证元素的唯一性的呢？
	 * 			—— 是通过元素的两个方法，hashCode()和equals()来完成的。
	 * 				即元素必须覆盖hashCode()和equals()方法，
	 * 				覆盖hashCode()方法是为了根据元素自身的特点确定哈希值，
	 * 				覆盖equals()方法是为了解决哈希值的冲突  
	 * 		
	 */
	/**
	 * 
	 */
	@Test
	public void treeSetTest02() {
//      - 自定义比较器
//      当元素自身不具备比较性时，或者具备的比较性不是所需要的，这时就需要让容器自身具备比较性。
//      定义一个比较器，将比较器对象作为参数传递给TreeSet集合的构造函数。
      Set set1 = new TreeSet(new ComparatorByName());
      set1.add(new Student("b", 6)); 
      set1.add(new Student("e", 2));
      set1.add(new Student("d", 1));
      set1.add(new Student("c", 4));
      set1.add(new Student("a", 5));
      for (Iterator it = set1.iterator(); it.hasNext();) {
          Student stu = (Student) it.next();
          System.out.println(stu.getName() + "::" + stu.getAge());
      }
	}
	@Test
	public void treeSetTest01() {
//		- 让元素自身具备比较性。
		Set set = new TreeSet();
		set.add(new Student("b", 6)); 
        set.add(new Student("e", 2));
        set.add(new Student("d", 1));
        set.add(new Student("c", 4));
        set.add(new Student("a", 5));
        set.add(new Student("e", 2));// 比较出来发现是0，不存
        set.add(new Student("b", 1));
//      3，只能用迭代器取出
        for (Iterator it = set.iterator(); it.hasNext();) {
            Student stu = (Student) it.next();
            System.out.println(stu.getName() + "::" + stu.getAge());
        }

	}
	@Test
	public void linkedHashSetTest01() {
		Set set = new LinkedHashSet();
		
		set.add("abc");
		set.add("def");
		set.add("ray");
//		set.forEach(action);
//		set.retainAll(set);//只保留交集,将c1和c2不同的元素从c1中删除，保留c1中和c2相同的元素
		
/**
* 	将一个顺序执行的流转变成一个并发的流只要调用 parallel()方法
*/
//		set.parallelStream();
/**
 * 	TODO 可拆分迭代器Spliterator
		 *  它和Iterator一样也是用于遍历数据源中的元素，但是他是为并行执行而设计的。 
		 *  java8 所有数据结构都实现了 这个接口， 一般情况不需要自己写实现代码。
		 *  但是了解它的实现方式会让你对并行流的工作原理有更深的了解。
 */	
//		set.spliterator();
		for(Iterator it= set.iterator();it.hasNext();) {
			System.out.println(it.next());
		}
	}
	@Test
	public void hashSetTest01() {
		/**
		 * 练习，往HashSet中存储学生对象(姓名，年龄)。同姓名，同年龄视为同一个人，不存。 
			
		  -	解：HashSet中存放自定义类型元素时，
			需要重写对象中的hashCode和equals方法，建立自己的比较方式，
			才能保证HashSet集合中的对象唯一。
		 */
		// 1，创建容器对象
		Set set = new HashSet();
		 // 2，存储学生对象
        set.add(new Student("xiaoqiang", 20));
        set.add(new Student("wangcai", 27));
        set.add(new Student("xiaoming", 22));
        set.add(new Student("xiaoqiang", 20));
        set.add(new Student("daniu", 24));
        set.add(new Student("wangcai", 27));//如果Student 中不实现hash和equals方法，则无法判断

        // 3，获取所有学生
        for (Iterator it = set.iterator(); it.hasNext();) {
            Student stu = (Student) it.next();
            System.out.println(stu.getName() + "::" + stu.getAge());
        }

	}
	/**
	 * - TODO 4、List集合存储数据的结构
				 * 	List接口下有很多个集合，它们存储元素所采用的结构方式是不同的，
				 * 	这样就导致了这些集合有它们各自的特点，供给我们在不同的环境下进行使用。
	 * 	- 数据存储的常用结构有：堆栈、队列、数组、链表。我们分别来了解一下
	 * 		- 堆栈
	 			* - 先进后出
	 			* - 栈的入口、出口都是栈的顶端位置。 <!--茶杯堆栈-->
	 			* - 压栈：就是存元素。
	 			 	即把元素存储到栈的顶端位置，栈中已有元素依次向栈底方向移动一个位置。
	 			* - 弹栈：取元素
	 			 	即把栈的顶端位置元素取出，栈中已有元素依次向栈顶方向移动一个位置。
	 *		- 队列
	 			* - 先进先出
	 			* - 队列的入口、出口各占一侧。<!--水管队列-->
	 *		- 数组
	 			* - 	查找元素快 ：通过索引可以快速访问指定位置的元素 
	 			* - 增删元素慢 ： 
	 			* 		- 指定索引位置增加元素：
	 			 			需要创建一个新数组，将指定新元素存储在指定索引位置，
	 			 			再把原数组元素根据索引，复制到新数组对应索引的位置上。
	 			*		- 指定索引位置删除元素：
	 						需要创建一个新数组，把原数组元素根据索引，
	 						复制到新数组对应索引的位置，原数组中指定索引位置元素不复制到新数组中。
	 *		- 链表
	 			* - 多个节点：<!--环环相扣-->
	 			* 		(它是由两部分组成，数据域[存储的数值]+指针域[存储的地址])之间，
	 			* 		通过地址进行连接
	 			* - 查找元素慢：
	 			* 		- 想查找某个元素，需要通过连接的节点，依次向后查找指定元素。
	 			* - 增删元素快： 
				*		- 增加元素：只需要修改连接下个元素的地址即可。
				*		- 删除元素：只需要修改连接下个元素的地址即可。
	 *
	 *	- TODO 5、List接口具体子类介绍
	 *		- List
				 * - ArrayList：
				 * 		底层的数据结构使用的是数组结构。
				 * 		- 特点：查询速度很快，但是增删稍慢。
				 * 			线程不同步，并且替换了Vector。			
				 * - LinkedList：
				 * 		底层使用的是链表数据结构。
				 * 		- 特点：增删速度很快，查询稍慢，
				 * 			并且是线程不同步的。
				 * - Vector：
				 * 		底层是数组数据结构，数组是可变长度的
				 * 		(不断的new新数组并将原数组元素复制到新数组中)。
				 * 		- 特点：线程同步，增删和查询速度都慢！并且被ArrayList替代了。
				 *
	 *		
	 */
	@Test
	public void linkedListTest01() {
		/**
		 *  - addFirst();→offerFirst(); 
				addLast();→offerLast();
			- getFirst();→peekFirst(); 
				getLast();→peekLast(); 
				- 获取元素，但不删除元素。如果集合中没有元素，会返回null。
			- removeFirst();→pollFirst(); 
				removeLast();→pollLast(); 
				- 获取元素，但是元素被删除。如果集合中没有元素，会返回null。
		 */
		LinkedList link = new LinkedList();
		link.addFirst("abc1");
		link.add("abc2");
		link.addFirst("abc3");
		link.offerFirst("abc4");
		System.out.println(link);
//		获取元素，但不删除元素。如果集合中没有元素，会返回null。
		System.out.println("link.peek():"+link.peek());
		System.out.println("link.peekFirst():"+link.peekFirst());
//		 获取元素，但是元素被删除。如果集合中没有元素，会返回null。
		System.out.println("link.removeFirst():"+link.removeFirst());
		System.out.println("link.removeFirst():"+link.removeFirst());
//		倒序	取出link中所有元素
		while (!link.isEmpty()) {
		    System.out.print("----"+link.removeLast());
//		    正序 取出
//		    System.out.print("----"+link.removeFirst());
		}
	}
	@Test
	public void arrayListTest01() {
		List list = new ArrayList();
/**
 * 练习：定义功能，请除去ArrayList集合中的重复元素 */
        list.add("abc1");
        list.add("abc4");
        list.add("abc2");
        list.add("abc1");
        list.add("abc4");
        list.add("abc4");
        list.add("abc2");
        list.add("abc1");
        list.add("abc4");
        list.add("abc2");
        System.out.println(list);
        singleElement(list);// 
        System.out.println(list);
	}
	/**
	 * 练习 ：去除ArrayList集合中重复的自定义元素。
	 * 			将自定义对象作为元素存到ArrayList集合中，
	 * 			并去除重复元素。比如：存人对象。同姓名同年龄，视为同一个人，为重复元素。*/
	@Test
	public void arrayListTest02() {
		Person p1 = new Person("fir",1);
		Person p2 = new Person("sec",2);
		Person p3 = new Person("list1",13);
		Person p4 = new Person("list1",13);
		
		List list = new ArrayList();
		list.add(p1);
		list.add(p1);
		list.add(p2);
		list.add(p3);
		list.add(p4);
		System.out.println(list);
//	 remove底层用的也是equals()
		list.remove(new Person("fir",1));
		singleElement(list);
		
		System.out.println(list);
	}
	private void singleElement(List list) {
		List itlist = new ArrayList();
		
		for(ListIterator it = list.listIterator();it.hasNext();) {
			Object obj = (Object) it.next();
//			通过arrayListTest02（）可以看出 contains()底层用的是equals()
			if(!itlist.contains(obj))
				itlist.add(obj);
		}	
		list.clear();
		list.addAll(itlist);
	}

	/**
	 * - 3、List接口
	 * 		- 它是一个元素存取有序的集合。注意：有序指的是存入的顺序和取出的顺序一致。
	 * 				例如，存元素的顺序是11、22、33，那么集合中元素的存储就是按照11、22、33的顺序完成的。
	 * 		- 它是一个带有索引的集合。
	 * 				通过索引就可以精确的操作集合中的元素（与数组的索引是一个道理）。
	 * 		- 集合中可以有重复的元素。
	 * 				通过元素的equals方法，来比较是否为重复的元素。
	 * 	
	 * 		- List接口中的特有方法上，
	 * 			它的特有方法都是围绕索引定义的。
	 */
	@Test
	public void listTest() {
		List al = new ArrayList();
		al.add("java01");
		al.add("java02");
		al.add("java03");
		//在指定位置添加元素
		al.add(1, "java09");
		sop(al);
		//修改元素
		al.set(2, "java007");
		//删除指定位置的元素
		al.remove(0);
		//通过角标获取元素
		al.get(1);
		//返回列表中第一次出现的指定元素的索引
		System.out.println("al.indexOf(\"java03\") : "+al.indexOf("java03"));
		sop(al);
		System.out.println("al="+al);
		//--------返回列表中指定的部分视图------这个好用
		List sub = al.subList(1, 3);
		System.out.println("sub="+sub);
		/**
		 * ------------------------------------蛮重要的-----------------------------------------
		 * TODO 3、Iterator --- 再解释
		 * -  在迭代时，只能用迭代器的方法操作元素，
				 * 可是Iterator的方法是有限的，
				 * 只能对元素进行判断，取出，删除的操作，
				 * 如果想要其他的操作如添加，修改等，
				 * 就需要使用其子接口listIterator——List集合特有的迭代器(listIterator)。
				 * 该接口只能通过List集合的listIterator()获取。
		 */
		for(ListIterator it = al.listIterator();it.hasNext();) {
			Object obj = it.next();
			if(obj.equals("java007")) {
				it.add("abcdefg");
			}
		}
		System.out.println("al="+al);
	}
	
	/**
	 * 	- TODO 2、Iterator
	 * 		Iterator的初步解释：
			 * 把取出方式定义在了集合的内部，
			 * 这样取出方式就可以直接访问集合内部的元素，
			 * 那么取出方式就被定义成了内部类。
			 * 而每一个容器的数据结构不同，所以取出的动作细节也不一样，
			 * 但是都有共性内容——判断和取出，
			 * 那么可以将这些共性抽取，那么这些内部类都符合一个规则，该规则就是Iterator。 
	 *	- 如何获取集合的取出对象呢？ 
	 *		- 答：通过一个对外提供的方法：
	 *		Iterator iterator()
	 *		——获取集合中元素上迭代功能的迭代器对象
	 *		(迭代器：具备着迭代功能的对象，而迭代器对象不需要new，直接通过iterator()方法获取即可)。
	 *	- Iterator接口中有如下2个重要方法：
	 *		- boolean hasNext()：
	 *			看看还有没有被获取到的元素。
	 *			如果没有就返回false，如果有就返回true。
	 *		- E next()：
	 * 			获取当前光标下的元素，然后再将光标往下移一行。光标初始值是0，
	 *			而且每调用一次next()方法，光标都会往下移一行。 
	 */
	
	@Test
	public void iteratorTest() {
		
		Collection al = new ArrayList();
		al.add("java01");
	    al.add("java02");
	    al.add("java03");
	    al.add("java04");
	    // 开发时,这样写
	    for(
		Iterator it = al.iterator();it.hasNext();) {
	    		it.remove();
	    		System.out.println(it.next());
	    		
//	    		al.add("errorTest");
	    		/**
	    		 * - 运行上述代码发生了异常——java.util.ConcurrentModificationException
	    		 * 			[并发修改异常]，这是什么原因呢？
	    		 * - 原因：
	    		 * 		- 在使用迭代器或者增强for循环遍历集合的时候，
	    		 * 			再调用集合的方法修改集合的长度（添加和删除 ），
	    		 * 			就会发生并发修改异常。
	    		 * 	也可以这样说：
	    		 * 		- 在迭代过程中，使用了集合的方法对元素进行操作，
	    		 * 			会导致迭代器并不知道集合中的变化，容易引发数据的不确定性。
	    		 * 		- 注意：并发修改异常是由next()方法抛出的。 
	    		 */
	    }
	}
	/**
	 * 	1.使用范围不同，Iterator可以应用于所有的集合，Set、List和Map和这些集合的子类型。而ListIterator只能用于List及其子类型。

		2.ListIterator有add方法，可以向List中添加对象，而Iterator不能。
		
		3.ListIterator和Iterator都有hasNext()和next()方法，可以实现顺序向后遍历，但是ListIterator有hasPrevious()和previous()方法，可以实现逆向（顺序向前）遍历。Iterator不可以。
		
		4.ListIterator可以定位当前索引的位置，nextIndex()和previousIndex()可以实现。Iterator没有此功能。
		
		5.都可实现删除操作，但是ListIterator可以实现对象的修改，set()方法可以实现。Iterator仅能遍历，不能修改。


	 */
	@Test
	public void listiterator() {
		
	    List<String> staff = new LinkedList<>();  
	    staff.add("zhuwei");  
	    staff.add("xuezhangbin");  
	    staff.add("taozhiwei");  
	    ListIterator<String> iter = staff.listIterator(); 
	    Iterator<String> it = staff.listIterator(); 
	   while(it.hasNext()) {
//	    iter.next();    
	    //删除zhuwei  
	    iter.remove();  
	    //把zhuwei改为simei  
	    //iter.set("simei");  
	    System.out.println("first:"+staff);  
	   }
	    iter.add("xiaobai");  
	      
	    //遍历List元素  
	    System.out.println("遍历List中元素，方法一：");  
	    for(String str : staff)  
	        System.out.println(str+"   ");  
	      
	    iter = staff.listIterator();  
	    System.out.println("遍历List中元素，方法二：");  
	    while(iter.hasNext())  
	    {  
	        System.out.println(iter.next());  
	    }  
	 
	}
	/**
	 * - 1、 集合中的最大接口——Collection
	 */
	
	@Test
	public void test01() {
		Collection c1 = new ArrayList();
		/**
		 * 	注意：
		 * 	- add方法的参数类型是Object，以便于接受任意类型。
		 *	- 集合中存储的都是对象的引用（地址）。
		 */
		c1.add("abc1");
		c1.add("abc2");
		c1.add("abc3");
		/**
		 * 	- 注意:删除元素会改变集合的长度
		 */
		c1.remove("abc2");
//		coll.clear();//清空集合
		System.out.println("abc1是否存在："+c1.contains("abc1"));
		System.out.println("集合是否为空？"+c1.isEmpty());
		
		
		/**
		 * - Collection接口中带all的方法。两个集合的互相操作
		 */
		Collection c2 = new ArrayList();
		c2.add("abc2");
		c2.add("abc3");
		c2.add("abc5");
		
		c1.addAll(c2);//往coll中添加coll2
		boolean b = c1.containsAll(c2);
		System.out.println("b = " + b);
		c1.removeAll(c2);//移除交集
		c1.retainAll(c2); //只保留交集,将c1和c2不同的元素从c1中删除，保留c1中和c2相同的元素
	}
	@SuppressWarnings("unused")
	private void sop(Collection c) {
		for(Iterator it = c.iterator();it.hasNext();) {
			System.out.println(it.next());
		}
	}
}

class Person{
	/**
	 * 对Person类进行描述，将数据封装到人对象中。
		定义容器对象，将多个Person对象存储到集合中。
		去除同姓名同年龄的Person对象(重复元素)。
		取出集合中的Person对象
	 */
	private String name;
	private int age;
	public Person() {}
	public Person(String name, int age) {this.name = name;this.age = age;}
	public String getName() {return name;}
	public void setName(String name) {this.name = name;}
	public int getAge() {return age;}
	public void setAge(int age) {this.age = age;}
	
	public boolean equals(Object obj) {
		if(obj==this) {return true;}
		if(!(obj instanceof Person)) {throw new ClassCastException("classtypeError");}
		Person p = (Person) obj;
		return (this.name.equals(p.name)) && (this.getAge()==p.getAge());
	}
	@Override
	public String toString() {
		return "Person [name="+name+", age="+age+"]";
	}
}

// 满足TreeSet的exercise，实现Comparable接口，实现compareTo()方法 
@SuppressWarnings("rawtypes")
class Student  implements Comparable{
	/**
	 * 练习，往HashSet中存储学生对象(姓名，年龄)。同姓名，同年龄视为同一个人，不存。 
		解：HashSet中存放自定义类型元素时，需要重写对象中的hashCode和equals方法，
			建立自己的比较方式，才能保证HashSet集合中的对象唯一。
	 */
	private String name;
	private int age;
	public Student() {}
	public Student(String name, int age) {super();this.name = name;this.age = age;}
	public String getName() {return name;}
	public void setName(String name) {this.name = name;}
	public int getAge() {return age;}
	public void setAge(int age) {this.age = age;}
	 /** 覆盖hashCode方法，根据对象自身的特点定义哈希值。
	   */
	    public int hashCode() {
	        final int NUMBER = 13;
	        return name.hashCode() + age * NUMBER; // 尽量减小哈希冲突
	    }
	    /**
	     * 还需要定义对象自身判断内容相同的依据，覆盖equals()方法。
	     */
	    public boolean equals(Object obj) {
	        if (this == obj) {return true;}
	        if (!(obj instanceof Student)) {
	            throw new ClassCastException("类型错误");
	        }
	        Student stu = (Student) obj;
	        return this.name.equals(stu.name) && this.age == stu.age;
	    }

	@Override
    public String toString() {
        return "Student [name=" + name + ", age=" + age + "]";
    }
	@Override
	public int compareTo(Object o) {
		Student stu = (Student) o; 
		 // 验证TreeSet集合的add()方法调用了compareTo()方法
//        System.out.println("Student.compareTo():"+this.name + ":" + this.age + "......" + stu.name + ":" + stu.age) ;
//		if(this.age > stu.age)
//			return 1;
//		if(this.age < stu.age)
//			return -1;
		
		//升序排序，如果 需要降序，加个负号
        return this.age-stu.age==0 ? this.name.compareTo(stu.name) : this.age-stu.age;
	}
}
/**
 * TODO 自定义一个比较器，用来对学生对象按照姓名进行升序排序
 * @author li ayun
 *
 */
class ComparatorByName  /*extends Object 继承Object类覆盖了equals()方法*/ implements Comparator  {
    @Override
    public int compare(Object o1, Object o2) {
        Student s1 = (Student) o1;
        Student s2 = (Student) o2;
        int temp = s1.getName().compareTo(s2.getName());
        return temp == 0 ? s1.getAge() - s2.getAge() : temp;
    }

}
