package TextFinal;

public class StringJoinTest {
	private void stringJoinBefore(){
		String s1 = "刘骏leo";
		String s2 = "刘骏"+"leo";
		System.out.println(s1==s2);
		
		// 定义两个字符串直接量
		String str1 = "刘骏";
		String str2 = "leo";
		// 链接运算
		String s3 = str1+str2;
		System.out.println(s3==s1);
	}
	private void stringJoinAfter(){
		/**
		 * final 使得 str1和2 执行宏替换，这样编译器在------ 在 编译 阶段 就已经确定了s3 的值----！；
		 */
		String s1 = "刘骏leo";
		String s2 = "刘骏"+"leo";
		System.out.println(s1==s2);
		
		// 定义两个字符串直接量
		final String str1 = "刘骏";
		final String str2 = "leo";
		// 链接运算
		String s3 = str1+str2;
		System.out.println(s3==s1);
	}
	private void FinalInitTest(){
		// 定义3个 final 实例变量
		final String str1;
		final String str2;
		final String str3 ="Java";
		// str2,str1 分别放在 非静态初始化块、构造器中初始化
		{
			str1 = "Java";
		}
		// 这里本该 是构造器  赋值str2
		/**
		 * 	只有str3 在定义变量的时候指定了初始值，另外两个分别在 非静态初始化块、构造器中指定初始值。因此系统不会对 str1 str2执行“宏替换”
		 *  于此类似的是， 对于普通变量 在定义时指定 初始值、执行初始化块中 赋初始值的效果基本一样。但对于final 类变量而言，只有定义fianl类变量时执行指定初始值，
		 *  系统 才会对该final 类变量执“宏替换” 。
		 */
	}
	/**
	 * 对于 final 实例变量 只有在定义的时候指定初始值 才会有“宏变量” 的效果；
	 * 在 非静态初始化块、构造器 中为final 实例变量 指定初始值则不会有这个效果
	 * 
	 */
	public static void main(String[] main){
		StringJoinTest sj = new StringJoinTest();
		sj.stringJoinBefore();
		sj.stringJoinAfter();
	}
}
