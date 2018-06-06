package TextFinal;

public class FinalLocalTest {
	/**
	 * 执行“宏替换” 的变量
	 * 	 	 对于一个final 变量，不管他是类变量还是 实例变量，还是 局部变量，
	 * 	  只要定义该变量时使用了 final修饰符，并在定义该final类变量时指定了初始值，
	 * 	  而且该初始值可以在编译的时候就已经被确定下来，那么final变量本质上已经不在是变量了，而是一个直接量 
	 */
	
	/**
	 * final 修饰符最终要的一个用途就是 定义 “宏变量”。当定义一个final变量的时候，就该为变量指定了初始值，
	 * 而且该初始值可以在编译时就确定下来，那么该fianl变量就是一个“宏变量” ；
	 * 编译器 会把程序中所有用到的该变量的地方直接替换成 该变量的值
	 */
	public static void main(String[] args) {
		// 下面定义了四个 final 宏变量
		 final int a = 5+2 ;
		 final double b = 1.2/3;
		 final String str = "疯狂"+"java";
		 final String book = "潘素金" + 99.0;
		 // 下面 book2 的值 是因为调用了方法 ，所以无法在编译时候 被确定下来；
		  final String book2 = "潘素金"+ String.valueOf("99.99");
		  System.out.println(book=="潘素金99.0");
		  System.out.println(book2=="潘素金99.99"+";");
		  System.out.println(book2+";"+"潘素金99.99");
	}
}
