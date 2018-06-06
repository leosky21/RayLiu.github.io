package TextFinal;

import java.util.Arrays;

/**
 * 如果程序需要在 匿名内部类中 使用局部变量，
 * 那么这个局部变量必须使用final修饰符修饰
 * @author liujun
 *
 */
public class CommandTest {
	// 定义一个方法生成指定的长度的数组，但是每一个数组元素用cmd负责产生
	public int[]  process(IntArrayProduct cmd,int length){
		int result[] = new int[length];
		for(int i = 0;i<result.length;i++){
			result[i]  = cmd.product();
		}
		 return result;
	}
	/**
	 * 匿名内部类 实现了 IntArrayProductor 接口，该匿名内部类实现的product()
	 * 方法中  访问了局部变量 seed 。因此 局部变量必须必须被 final 修饰。
	 * ---------------这里就关系到  java 的 对象与 闭包 
	 * 
	 * 其实 任何的  内部类  中访问的 局部变量 都应该使用  final 修饰。
	 * @param seed
	 */
	public static void main(String[] args) {
		CommandTest ct = new CommandTest();
		final int seed = 5;
		// 生成 数组，具体生成方式取决于 intArrayProductor()接口的匿名类
		int[] result = ct.process(new IntArrayProduct(){
			public int product(){
				return (int)Math.round(Math.random()*seed);
			}
		}, 6);
		System.out.println(Arrays.toString(result));
	}
}
/**
 * 原因:  对于 普通的局部变量 而言， 他的作用域 时停留在 该方法内，
 * 		当方法执行结束该局部变量也是随之消失；但是内部类则可能产生  隐式的“闭包” 
 * 		闭包将 使得 局部变量脱离它所在的方法继续存在 请看 ClosureTest.java
 * @author liujun
 *
 */

interface IntArrayProduct {
	// 接口中 定义product 方法用于封装 ‘处理行为’
	int product();
}