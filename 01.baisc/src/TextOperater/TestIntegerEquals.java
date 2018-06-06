package TextOperater;

public class TestIntegerEquals {
	private void TestIntegerEquals(){
		/**
		 * 封装成两个包装箱
		 * 产生的引用的地址 不同
		 */
	Integer i = new Integer(10);
	Integer j = new Integer(10);
	
	System.out.println("i==j:"+(i==j));
	
	Integer m = 10;
	Integer n = 10;
	System.out.println("m==n:"+(m==n));
	/***
	 * 系统认为(int ,short) -128 到 + 127很经常用，所以
	 * -128到127 的对象放在了缓存池中，
	 * 当新建一个对象的时候，会先查看是否已经生成；
	 * 如果产生了，就直接将引用赋过去；
	 * 否则新建；
	 * 
	 *  但是超过了-128到127，则直接新建对象；
	 */
	Integer p = 200;
	Integer q = 200;
	System.out.println("p==q"+(p==q));
	}
	public static void main(){
		TestIntegerEquals t = new TestIntegerEquals();
		t.TestIntegerEquals();
	}
}
/**
 *枚举类型  ：内部进行了 唯一实例化，所以可以直接判断
 *
 *引用对象： 直接看两个引用是否一样；
 *		  如果要判断 内容是否一样，则需重写 equals 方法；
 *		  如果重写equal是 方法 ，最好重写hasCode()方法；
 */
