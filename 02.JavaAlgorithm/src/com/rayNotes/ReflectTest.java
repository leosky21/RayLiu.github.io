package com.rayNotes;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;

import org.junit.Test;

/**
 * 	- 反射
 * 
 * @author ray
 *
 */
public class ReflectTest {
	/**
	 *  TODO 1、 步骤
	 *  		- 加载 
	 *  			- 将class文件读入内存，并为之创建一个Class类型的对象。
	 *  				（任何类被使用时系统（JVM）都会建立一个Class类型的对象）
	 *  		- 连接
	 *  			- 验证 ： 验证加载进来的类是否有正确的额内部结构，并且与其他类协调一致
	 *  			- 准备 ： 负责为类的静态成员分配内存，并设置默认初始化值
	 *  			- 解析 ： 将类的二进制数据中的符号引用替换为直接引用
	 *  
	 *  		- 初始化
	 *
	 *  TODO 2、类加载的时机
	 *  			- 创建某个类的实例
	 *  			- 调用类的静态成员变量或者静态成员变量赋值
	 *  			- 调用类的静态成员
	 *  			- 使用反射方式强制创建某个类或接口对应java.lang.Class对象
	 *  			- 创建子类对象也会把父类加载到内存
	 *  			- 直接使用java.exe运行某个主类
	 *  						
	 *  TODO 3、类加载器
	 *  			- Bootstrap ClassLoader 
					* 根类加载器，也被称为引导类加载器，负责Java核心类库(rt.jar)的加载，比如System、String等，Java核心类库——rt.jar在JRE的lib目录下
				- Extension ClassLoader 
					* 扩展类加载器，其负责JRE的扩展目录中jar包的加载，即加载JRE下面的ext文件夹下面的jar包
				- System ClassLoader 
					* 系统类加载器，其负责在JVM启动时加载‘来自java命令的class文件’，以及classpath环境变量所指定的jar包和类路径
	 * 	TODO 4、反射的定义
	 * 			Java反射机制是在运行状态中，对于任意一个类，都能够知道这个类的所有属性和方法，
	 * 				对于任意一个对象，都能够调用它的任意一个方法和属性，
	 * 				这种动态获取类的信息以及动态调用对象的方法的功能称为Java语言的反射机制。
	 * 			- 总结
	 * 			（1）反射定义：
	 * 				- 能够在运行时间获得和调用类或者对象的成员变量，成员方法以及构造方法
	 * 			（2）条件：
	 * 				- 一定在运行时期  
	 * 	TODO 5、Class类
	 * 			 Class对象是在加载类时由Java虚拟机 以及 
	 * 				通过调用类加载器中的defineClass方法自动构造的。 
	 * 			
	 * 			- 获取一个类的class文件对象有三种方式
	 * 				
	 */
	/**---------------------第一种方式----------------------*
	 * 				通过该类的对象的getClass()方法获得
	 * */
	PersonReflect p = new PersonReflect();
	Class c = p.getClass();
	/**---------------------第二种方式----------------------*
	 * 				通过类名.class获取到字节码文件对象
	 * 	（任意数据类型都具备一个class静态属性，看上去要比第一种方式简单）
	 * */
	Class c1 = PersonReflect.class;
	/**---------------------第三种方式----------------------*
	 * 				通过Class类中的方法
	 * 		（将类名作为字符串传递给Class类中的静态方法forName即可）
	 * 	扩展性更强 : 我不需要知道你的类，我只提供字符串，按照配置文件加载就可以了。
	 * */
//	Class c2 = Class.forName("com.rayNotes.PersonReflect");
	
	
	/**
	 * 	TODO 6、 通过反射获取构造方法并使用 
	 * 		- 返回一个构造方法
	 * 		- 返回多个构造方法
	 * @throws SecurityException 
	 * @throws NoSuchMethodException 
	 */
	@Test
	public void constructorTest() throws NoSuchMethodException, SecurityException {
	Constructor[] cons = c.getDeclaredConstructors();
		for(Constructor con : cons) {
			System.out.println(con);
		}
		System.out.println("--------------------------------------------");
		/**
		 * 获取一个指定的构造方法，即空参数的构造方法
        		 	public Person() {}
		 */
		Constructor con1 = c.getConstructor();
		System.out.println(con1);
		/**
		 * 获取String、int参数的构造方法：
		 * public Person(String name, int age) {}
		 * 		Constructor<T> getConstructor(Class<?>... parameterTypes)  
		 * 		Class<?>... parameterTypes：传递要获取的构造方法的参数列表
		 */
		Constructor con2 = c.getConstructor(String.class,int.class);
		System.out.println(con2);
		
		 /**
		  *  Constructor getDeclaredConstructor(Class... c)：
		  *  		获取到指定参数列表的构造方法(包括私有的)
		  */
        Constructor con3 = c.getDeclaredConstructor(int.class, String.class);
        System.out.println(con3);
	}
	/**
	 *  TODO 7、反射获取构造方法，创建对象
	 *  			-（1）获取到Class对象
	 *  			-（2）获取指定的构造方法
	 *  			-（3）通过构造方法类Constructor中的方法
	 *  				- public T newInstance(Object... initargs)，
	 *  						创建对象
	 * @throws SecurityException 
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	@Test
	public void constructorTest02() throws NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
//		Class c = Class.forName("com.rayNotes.PersonReflect");
		Class c = PersonReflect.class;
		Constructor con = c.getConstructor();
		Object obj = con.newInstance();
		System.out.println(obj.toString());
	}
	/**
	 * TODO 8、反射获取class文件中的空参数构造方法并运行,快捷的方式:
	 * 	- 被反射的类，必须具有空参数的构造方法。
	 *	- 构造方法权限必须是public的。
	 * 
	 * 
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 */
	public void constructorTest03() throws NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {

		Class c = PersonReflect.class;
//		Constructor con = c.getConstructor();
		Object obj = c.newInstance();
		System.out.println(obj.toString());
	}
	/**
	 * TODO 9、 通过反射获取有参数的构造方法并运行
	 * 
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 */
	public void constructorTest04() throws NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Class c = PersonReflect.class;
		Constructor con = c.getConstructor(String.class,int.class);
		Object obj = con.newInstance("张三",20);
		System.out.println(obj.toString());
	}
	
	/**
	 *  TODO 10、通过反射方式，获取私有构造方法，创建对象
	 *  		- AccessibleObject类是Field、Method和Constructor对象的父类
	 *  		----------它提供了将反射的对象标记为 在使用时取消默认Java语言访问控制检查的能力。
	 *  		- public void setAccessible(boolean flag) throws SecurityException 
					- 参数值为true， 则指示反射的对象在使用时应该取消Java语言访问检查。
					- 参数值为false，则指示反射的对象应该实施Java语言访问检查。
	 * @throws SecurityException 
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	@Test
	public void constructorTest05() throws NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException  {
		
		/**
		 *  Constructor getDeclaredConstructor(Class... c)：
		 *  			获取到指定参数列表的构造方法(包括私有的)  
		 *  	- 11、 它破坏了程序的封装性和安全性。   
		 */
		Constructor con = c.getDeclaredConstructor(int.class,String.class);
		 // Constructor类有一个父类AccessibleObject，父类中定义了一个方法：void setAccessible(boolean flag) 
		con.setAccessible(true);// 暴力反射
		Object obj = con.newInstance(20, "李四");
        System.out.println(obj);
	}
	/**
	 * TODO 12、通过反射获取成员变量并使用
	 * 			- public Field getField(String name) / public Field[] getFields()
	 * 				 获取指定的public修饰的变量/多个变量。
	 * 			- public Field getDeclaredField(String name) / public Field[] getDeclaredFields()
	 * 				 获取指定的任意变量。/多个变量。 （包括私有）
	 * 
	 *  @out
	   		private java.lang.String com.rayNotes.PersonReflect.name
			private int com.rayNotes.PersonReflect.age
	 */
	@Test
	public void constructorTest06() {
		Field[] fields =  c.getDeclaredFields(); 
		for(Field f : fields) {
			System.out.println(f);
		}
	}
	/**
	 * 	TODO 通过反射，创建对象，获取指定的成员变量，进行赋值与获取值操作
	 * 		(1) 获取Class对象
			(2) 获取构造方法
			(3) 通过构造方法，创建对象
			(4) 获取指定的成员变量（私有成员变量，通过setAccessible(boolean flag)方法暴力访问）
			(5) 通过方法，给指定对象的指定成员变量赋值或者获取值 
				 - 	public void set(Object obj, Object value) 
					在指定对象obj中，将此Field对象表示的成员变量设置为指定的新值。
				 - 	public Object get(Object obj) 
					返回指定对象obj中，此Field对象表示的成员变量的值。

	 * @throws SecurityException 
	 * @throws NoSuchFieldException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * 	
	 */
	@Test
	public void constructorTest07() throws NoSuchFieldException, SecurityException, InstantiationException, IllegalAccessException {
		
		Object obj = c.newInstance();// 得知道放在哪个对象里把，为新建的实例创建引用
		/** 获取指定的成员变量String name
         Class类的方法：Field getField(传递字符串类型的变量名) 获取指定的--public成员变量--*/
		Field field = c.getField("name");
		/**  Field类的方法：  void set(Object obj, Object value)可以修改成员变量的值
         	 Object obj：	必须有对象的支持；Object value：修改后的值
         */
		field.set(obj, "ray");
		
		Field age = c.getDeclaredField("age");
		age.setAccessible(true);
		age.set(obj, 123);
		System.out.println(obj);
	}
	/**
	 *  TODO 通过反射获取成员方法并使用
	 *  		 - 在反射机制中，把类中的成员方法使用类Method表示
	 *  - 	返回获取一个方法 
				- public Method getMethod(String name, Class<?>... parameterTypes) 
					获取public修饰的方法。
				- public Method getDeclaredMethod(String name, Class<?>... parameterTypes)
					获取任意的方法，包含私有的。 
					参数1：name，即要查找的方法名称；参数2：parameterTypes，即该方法的参数类型。
		- 	返回获取多个方法 
				- public Method[] getMethods() 
					获取本类与父类中所有public修饰的方法。
				- public Method[] getDeclaredMethods() 
					获取本类中所有的方法(包含私有的)。
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws SecurityException 
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 */
	@Test
	public void constructorTest08() throws InstantiationException, IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException  {
		Object obj = c.newInstance();
		Method eat = c.getMethod("eat");
		eat.invoke(obj, null);
		
		Method sleep = c.getMethod("sleep", String.class,int.class,double.class);
		sleep.invoke(obj, "abc",12,1.11);
	}
	/**
	 * 练习： 
	 * 		泛型擦除
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 */
	@Test
	public void constructorTest09() throws InstantiationException, IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException  {
		ArrayList<Integer> list = new ArrayList<Integer>();
		list.add(123);
		
		Class c = list.getClass();
		Method add = c.getMethod("add", Object.class);
		add.invoke(list, "string");
		for(Iterator it = list.iterator();it.hasNext();) {
			System.out.println(it.next());
		}
	}
}


class PersonReflect{
	public String name;
	private int age;
	
	public PersonReflect() {super();};
	public PersonReflect(String name, int age) {super();this.name = name;this.age = age;}
	@SuppressWarnings("unused")
	private PersonReflect( int age,String name) {super();this.name = name;this.age = age;}
	
	public void eat() {
		System.out.println("eat");
	}
	public void sleep(String s, int a,double d) {
		System.out.println("sleep::"+s+" …… "+a+" …… "+d);
	}
	public void playGame() {
		System.out.println("game----");
	}
	public String toString() {return "Person [name="+name+",age="+age+"]";}
	
	public String getName() {return name;}
	public void setName(String name) {this.name = name;}
	public int getAge() {return age;}
	public void setAge(int age) {this.age = age;}
	
}