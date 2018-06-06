package TextFinal;

public class FinalInstanceVaribaleTest {
	/**
	 * 被final 修饰的实例变量必须显式的 指定初始化，而且只能如下3个位置指定初始值；
	 * 	 *定义final实例变量的时候，指定初始值
	 * 	 *在非静态代码块中 为final 实例变量  指定初始值
	 *   *在构造器中为final 实例变量指定初始值
	 *	  	对于普通实例变量，java程序可以对它执行默认的初始化，也就是将实例变量的值
	 *			指定为 默认的初始值 0或者 null
	 *		但是对于final实例变量，则必须由 程序员显式的指定初始值
	 */
	final int var1= "这3字".length();
	final int var2;	// priceTest.java  中如果将static double initPrice 改成 final static double initPrice
						 	 // 不难发现，在开始编译时，就已经将初始值确定下来 系统不会 在静态代码块中 对该类的变量赋初始值，而是在类定义中直接使用该初始值代替final变量
	final int var3;
	final static int varstatic="static的varsatatic长度".length();
	final static int varstatic2 ;
	// 非静态代码块中 赋初始值
	{
		var2 = "这是5个字".length();
	}
	// 静态代码块中为varstatic2 赋值
	static {
		varstatic2 = "这是static字符串好么好么多的字".length();
	}
	
	//构造器中赋初始值
	public FinalInstanceVaribaleTest() {
		var3 = "这是好多好多字的字符串".length();
	}
	public static void main(String[] main){
		FinalInstanceVaribaleTest zhenchangdeleiming = new FinalInstanceVaribaleTest();
		System.out.println(zhenchangdeleiming.var1
				+";-----------"+
				zhenchangdeleiming.var2
				+";-----------"+
				zhenchangdeleiming.var3);
	}
	/**
	 * 	final实例变量必须显式的被赋初始值；而且本质上 final 实例变量只能在构造器中 被赋初始值／当然
	 * 还可以在定义 fianl实例变量的时候  指定初始值。也可以在初始化块中为final 实例变量指定初始值；
	 * 这其实本质一样；
	 * 
	 * 对于  final 类变量：
	 * 	*定义final类变量的时候指定初始值；
	 *  *在静态初始化块中为 final 类变量指定初始值；
	 */
	
}
