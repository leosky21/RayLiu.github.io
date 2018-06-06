package TextFinal;



/**
 * 定义了局部变量 str ,正常情况下  ，当执行完 main（）之后，main的生命周期就结束了
 * 	局部变量 str 的作用域也就随之结束。
 * 但是实际上 这个线程中 run 没有执行完，匿名内部类 的实例的 生命周期 就没有结束，
 * 将一直访问 str 局部变量的值。这就是 内部类会扩大局部量作用域的实例。
 * 
 * 由于 内部类  可能扩大局部量的 作用域，如果再加上 这个内部类访问的 局部变量没有用
 * fianl 修饰，也就是说该变量的值可以随意改变，如此不可。
 * @author liujun
 *
 */
public class ClosureTest {
	public static void main(String[] args) {
		// 定义一个局部变量
		final String str = "java";
		// 在 内部类访问局部变量
		new Thread(new Runnable(){
			public void run(){
				for(int i=0;i<100 ;i++){
					// 此处将 一直可以访问到 str 局部变量
					System.out.println(str+" "+i);
					// 暂停0.1s
					try{
						Thread.sleep(100);
					}catch(Exception ex){
						ex.printStackTrace();
					}
				}
			}
		}).start();
		// main() 结束
	}
}
