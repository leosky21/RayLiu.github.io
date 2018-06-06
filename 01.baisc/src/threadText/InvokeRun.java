package threadText;
/**
 *  JAVA 提供给三种方式创建线程：
 *  	1、 继承 Thread，重写 run（）；
 *  ·	2、 实现Runnable接口、重写Run （）方法 ；
 *  	3、 实现 Callble 接口。重写 call（） 方法
 * @author liujun
 *		第一个 ： 线程类 无法再继承其他类；
 *			因为每条线程都是 一个Thread 子类的实例。多个线程之间 共享数据 比较麻烦
 *
 *			还能一种潜在的危险、如下
 */
public class InvokeRun extends Thread {
	private int i;
	// 重写 run 方法，run方法的方法体 就是线程执行体
	public  void run() {
		for(;i<100;i++){
			//直接调用run方法，Thread 的this.getname返回对象名字
			System.out.println(Thread.currentThread().getName()+"----------"+i);
		}
	}
	public static void main(String[] args) {
		for(int i=0;i<100;i++){
			// 调用Thread 的currentThread方法获取当前线程
			System.out.println(Thread.currentThread().getName()+""+i);
			if(i==20){
				// 直接调用线程线程对象 run方法
				// 系统会把 线程对象当作 普通对象， 把 run 方法当成普通方法
				// 所以 ,下面两行代码不会启动 2条线程，而是依次执行两个run 方法
				new InvokeRun().start();
				//new InvokeRun().run();
				new InvokeRun().run();
			}
		}
	}
}
