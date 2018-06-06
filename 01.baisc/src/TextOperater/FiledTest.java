package TextOperater;

public class FiledTest{
	public static void main(String[] argv){
		Person p = new Person();
		Person p2 = new Person();
		p.name = "无能";
		p.age = 18888888;
		p2.name = "悟空";
		p2.age = 199999999;
		// 类变量 属于该类本身，只要初始化该类完成，程序即可使用类变量；
		Person.Eyenum = 2;
		System.out.println("直接访问 eyenum变量"+Person.Eyenum);
		p.Eyenum = 12;
		System.out.println("通过p访问 eyenum变量"+p.Eyenum);
		p2.info();
		p2.Eyenum = 22;
		System.out.println("通过p2访问 eyenum变量"+p2.Eyenum);
	}
}

class Person{
	/**
	 * 没有 static 修饰的成员变量是实例变量，属于 该类的 实例。
	 * 在同意jvm中 ，每个类只对应一个Class 对象。但是每个类可以创建多个JAVA对象
	 * 	由于同一个JVM中，每个类只对应一个CLass对象，因此同一个JVM内的一个类的类变量只需要一块内存空间；
	 * 	但是对于实例变量而言，该类每次创建一个实例，就需要为实例变量分配一次内存空间。
	 */
	String name ;
	int age;
	static int Eyenum;
	public void info(){
		System.out.println("myname:"+name+"; myage"+age+"; Eyenum:"+Eyenum);
	}
}