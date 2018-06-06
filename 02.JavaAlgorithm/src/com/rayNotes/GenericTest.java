package com.rayNotes;


import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.junit.Test;

/**
 * 
 * <http://blog.csdn.net/yerenyuan_pku/article/details/54108053>
	<http://blog.csdn.net/wisgood/article/details/11762427>
	
 *	什么时候定义泛型类？
 *		——	当类中要操作的引用数据类型不确定的时候，
 *			早期定义Object来完成扩展，现在定义泛型来完成扩展。
 * @author ray
 * 	泛型通配符
 * 		- 泛型高级(通配符)——泛型的上限
 * 		- 泛型高级(通配符)——泛型的下限
 *
 */
/**--------------------分割线-----------------------*/
interface InterDemo<T>{ void show(T t);}
class InterDemoImpl01 implements InterDemo<String> {
    public void show(String t) {
        System.out.println("InterDemoImpl01show:"+t);
    }
}
class InterDemoImpl02<T> implements InterDemo<T> {
    public void show(T t) {
        System.out.println("InterDemoImpl02show :"+t);
    }
}
/**--------------------分割线-----------------------*/
class Demo<W>{
	//  --->静态方法是无法访问类上定义的泛型的。如果静态方法需要定义泛型，泛型只能定义在方法上
	public /*static*/ void show(W w) {
		 System.out.println("show:" + w);
	}
	public static <A> void staticShow(A a) {
        System.out.println("static show:" + a);
    }
	 public <Q> void print(Q w) { // 泛型方法
	        System.out.println("print:" + w);
	    }
}
/**--------------------分割线-----------------------*/
@SuppressWarnings({"rawtypes","unchecked"})
public class GenericTest {

	
	@Test
	public void genericInterfaceMethod() {
		InterDemo i1 = new InterDemoImpl01();
		InterDemo<Integer> i2 = new InterDemoImpl02<Integer>();
		i1.show("接口实现第一种方式");
		i2.show(2);
	}
	@SuppressWarnings("static-access")
	@Test
	public void genericMethod() {
		Demo<String> d = new Demo<String>();
		d.show("genericMethod_W");
//		静态方法不可以访问类上定义的泛型。--->
		d.staticShow("genericMethod_static");
		d.print("genericMethod_Qw");
		d.print(123);
	}
	
	/**
	 * TODO 泛型擦除
	 * - 2、泛型的擦除
	 * 		- 泛型的擦除用一句话来说就是：编译器通过泛型对元素类型进行检查，
	 * 		只要检查通过，就会生成class文件，但在class文件中，就将泛型标识去掉了。
	 *
	 *	- 在泛型代码内部，无法获得任何有关泛型参数类型的信息 
	 * @throws SecurityException 
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 * @throws IllegalAccessException 
	 */
	@Test
	public void crasedTest02() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException{
		
		Class c1 = new ArrayList<String>().getClass();
		Class c2 = new ArrayList<String>().getClass();
		//泛型类型String和Integer都被擦除掉了，只剩下了原始类型。
		System.out.println(c1==c2);//true
		
		ArrayList<Integer> l = new ArrayList<Integer>();
		l.add(1);
		//利用反射调用add方法的时候，却可以存储字符串
		l.getClass().getMethod("add", Object.class).invoke(l, "abc");
		for (int i=0;i<l.size();i++) {  
            System.out.println(l.get(i));  
        }  
	}
	
	/**
	 * TODO 泛型概述
	 * 1. 概述	
	 * 		容器什么类型的对象都可以存储，
	 * 		但是在取出时，需要用到对象的特有内容时，这时需要做向下转型
	 * @since JDK1.4
	 * 	- 产生
	 * 		编译器在编译时，就可以对集合中存储的对象类型进行检查，
	 * 		一旦发现类型不匹配，就编译失败。这个技术就是泛型技术
	 * 	@since JDK 1.5
	 * 		- 运行时期出现的问题ClassCastException，转移到了编译时期
	 * 		- 避免了向下转型的麻烦。
	 * 	- 泛型的擦除
	 * 		- 泛型的擦除用一句话来说就是：编译器通过泛型对元素类型进行检查，
	 * 			只要检查通过，就会生成class文件，但在class文件中，就将泛型标识去掉了。
	 */
	@Test
	public void genericTest(){
		List l = new ArrayList();
		l.add("string");
		l.add(123);//java.lang.ClassCastException: java.lang.Integer cannot be cast to java.lang...
		for(Iterator it = l.iterator();it.hasNext();) {
			@SuppressWarnings("unused")
			String str = (String) it.next();
		}
	}
}
