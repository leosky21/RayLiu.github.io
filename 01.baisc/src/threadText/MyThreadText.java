package threadText;
/**
 *  创建线程有2种方法：1、扩展线程父类Thread类 ， 2、实现Runnable接口。
 *  2种方法是有区别的。

      提示：Thread类已经实现了Runnable接口，Thread类中不但实现了run方法，
      也定义了更多的，全面的线程对象的操作方法，而Runnable接口中只有run这一个方法。
 * @author liujun
 *
 *	线程的启动；  .start（）
 *	线程的 结束： 通过设定标记变量、以结束相应的循环和方法
 *	暂时组织线程的执行：  Thread.sleep(1000)
 */
public class MyThreadText {
	public static void main(String[] args) {
		System.out.println("我是"+Thread.currentThread().getName()+"线程 ");
		
		MyThread leo = new MyThread("leo");
		MyThread jun = new MyThread("jun");
		
		leo.start();
		jun.start();
		System.out.println(Thread.currentThread().getName()+"ok!");
		
		//  runnable------------------
		System.out.println(Thread.currentThread().getName()+"start!");
		MyThreadRunnable mtr = new MyThreadRunnable();
		Thread  run1 = new Thread(mtr,"run1");
		Thread run2  = new Thread(mtr,"run2");
		run1.start();
		run2.start();
	}
}
/**
 * 两类线程 ： 非 daemon线程（如果  在java程序中还有 普通线程 就不会停止）---------daemond线程（守护线程、后台线程------垃圾回收线程是  后台线程；如果普通线程 结束，则自动终止。）
 * 	使用方法  setDaemon(true)
 * @author liujun
 *
 */
class MyThread extends Thread{
	public MyThread() {
		super();
	}
	public MyThread(String string) {
		super(string);
	}
	
	
	public void run(){
		for(int i =0;i<5;i++){
			System.out.println("i am "+this.getName());
		
		try{
			Thread.sleep(280);
		}catch (Exception e) {
			System.out.println(e.getStackTrace());
		}
		}
	}
}

class MyThreadRunnable implements Runnable{
	private int  num = 50;
	@Override
	public void run() {
		while(num>=1){
			System.out.println("come from "+Thread.currentThread().getName()+"num:"+num);
			num--;
		}
		
	}
	
}
