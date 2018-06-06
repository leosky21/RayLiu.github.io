package ReferenceText;

import java.lang.ref.SoftReference;
/**
 * 系统内存空间不够的时候，会被系统回收；
 * 
 * ---------创建 1000个对象的两种方式；---->  依次创建1000个对象，到那时每一个对象引用指向最后一个Person 的对象；------> 1定义1000长度的数组。
 * ---------第一种情形：  程序不允许访问需要重新访问前面创建的对象，即使这个对象占用的堆空间 没有被回收。
 * ·········第二种情形：  如果系统内存膨胀，1000个对象都被强引用占用，不能回收
 * @author liujun
 *
 */
class Person{
	String name;
	int age;
	protected Person(String name,int  age){
		this.name = name;
		this.age = age;
	}
	public String toString(){
		return "Person[name="+name
				+ ", age = "+age +"]";
	}
}


public class SoftReferenceTest{
	public static void main(String []  argv){
		@SuppressWarnings("unchecked")
		 SoftReference<Person>[]  man = 
					new  SoftReference[100000];
		for (int i=0;i<man.length;i++){
			man[i] = new SoftReference<Person>(new Person("name"+i , (i+1)*4%100));
		} 
		System.out.println(man[2].get());
		System.out.println(man[5].get());
		//通知 系统进行垃圾回收
		System.gc();
		System.runFinalization();
		// 垃圾回收机制运行之后，softReference 数组里的元素保持不变
		System.out.println(man[2].get());
		System.out.println(man[5].get());
 	} 
}