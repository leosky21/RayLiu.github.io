package TextOperater;

public class TestStringEquals {
	public  static void main(String[] argv){
		String hello = "Hello";
		String lo = "lo";
		
		System.out.println(hello == "Hello");
		//System.out.println(other.hello == hello);
		
		System.out.println(hello == "Hel"+lo);
		System.out.println(hello == "Hel"+"lo");//字符串常量编译器编译后已经形成了一个字符串
		
		System.out.println(hello == new String("Hello"));//new 创建的一个对象
		
		System.out.println(hello == ("Hel"+lo).intern());//intern：求的内部化里的的字符串	
	}
}
