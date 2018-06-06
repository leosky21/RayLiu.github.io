package threadText;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
/**
 * 线程状态从大的方面来说，可归结为：初始状态、可运行状态、不可运行状态和消亡状态，具体可细分为上图所示7个状态，说明如下：

1）线程的实现有两种方式，一是继承Thread类，二是实现Runnable接口，但不管怎样，当我们new了thread实例后，线程就进入了初始状态；

2）当该对象调用了start()方法，就进入可运行状态；

3）进入可运行状态后，当该对象被操作系统选中，获得CPU时间片就会进入运行状态；

4）进入运行状态后case就比较多，大致有如下情形： ﹒run()方法或main()方法结束后，线程就进入终止状态； 当线程调用了自身的sleep()方法或其他线程的join()方法，就会进入阻塞状态(该状态既停 止当前线程，但并不释放所占有的资源)。当

sleep()结束或join()结束后，该线程进入可运行状态，继续等待OS分配时间片； 当线程刚进入可运行状态(注意，还没运行)，发现将要调用的资源被锁牢(synchroniza,lock)，将会立即进入锁池状态，等待获取锁标记(这时的锁池里也许已经有了其他线

程在等待获取锁标记，这时它们处于队列状态，既先到先得)，一旦线程获得锁标记后，就转入可运行状态，等待OS分配 CPU时间片； 当线程调用wait()方法后会进入等待队列(进入这个状态会释放所占有的所有资源，与阻塞状态不同)，进入这个状态后，

是不能自动唤醒的，必须依靠其他线程调用notify()或notifyAll()方法才能被唤醒(由于notify()只是唤醒一个线程，但我们由不能确定具体唤醒的是哪一个线程，也许我们需要唤醒的线程不能够被唤醒，因此在实际使用时，一般都用notifyAll()方法，唤醒

有所线程)，线程被唤醒后会进入锁池，等待获取锁标记。 当线程调用stop方法，即可使线程进入消亡状态，但是由于stop方法是不安全的，不鼓励使用，大家可以通过run方法里的条件变通实现线程的 stop。
 * @author liujun
 *
 */
public class ThreadGroupTest1  {
	public static void main(String[] args) {
		// 创建一个 可重用的固定线程数 的线程池
		ExecutorService pool = Executors.newFixedThreadPool(2);
			//	 创建runnable 接口对象，Thread对象当然也实现了Runnable接口
		Thread t1 = new myThread1(1);
		Thread t2 = new myThread1(2);
		Thread t3 = new myThread1(3);
		Thread t4 = new myThread1(4);
		Thread t5 = new myThread1(5);
		Thread t6 = new myThread1(6);
		
		// 放入线程池
		pool.execute(t1);
		pool.execute(t2);
		pool.execute(t3);
		pool.execute(t4);
		pool.execute(t5);
		pool.execute(t6);
		// 关闭线程池
		/**
		 * 	t.start(); 该行代码相当于是启动线程，
			t.run(); 该行代码相当于是使用t这个类中的run方法而已	
		 */
		t1.start();
		t2.start();
		t3.start();
		t4.start();
		t5.start();
			pool.shutdown();
	}	
}

class myThread1 extends Thread{
	int id ;
	public myThread1(int id) {
		// TODO Auto-generated constructor stub
		this.id = id;
		this.setName(""+id);
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		
		System.out.println(Thread.currentThread().getName()+"----ing");
		
		System.out.println(this.currentThread().getName()+"----this.ing");
	}
}
