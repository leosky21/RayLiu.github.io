package com.rayNotes;


import org.junit.Test;

/**
 * 
 * - 概述
 * 	 	-  进程就是应用程序在内存中分配的空间，也可理解为一个正在执行中的程序。（最小的拥有资源的单位）
 * 			- 每一个进程执行都有一个执行顺序，该顺序就是一个执行路径或者叫一个控制单元。
 * 		- 线程就是进程中负责程序执行的执行单元，也可理解为进程中的一个独立的控制单元。
 * 			- 线程在控制着进程的执行。
 * 
 * @author ray
 *
 */
public class ThreadTest {
	/**
	 * 死锁：
	 * 		----有空写
	 */
	/**
	 * TODO 线程安全问题 ：线程安全问题在理想状态下，不容易出现，但一旦出现对软件的影响是非常大的。
	 * 		产生买票程序会出现0,-1,-2等错票的原因是
	 * 		
	 * 		-  线程任务中有处理到共享的数据
	 * 		- 线程任务中有多条对共享数据的操作。
	 * 			一个线程在操作共享数据的过程中，其他线程参与了运算，造成了数据的错误。
	 * 		- 解决办法：
	 * 			只要能保证多条操作共享数据的代码在某一时间段被一条线程所执行，
	 * 			在执行期间不允许其他线程参与运算。
	 */
	public void safeTest01() {
		
	}
	
	
	
	public static  void main(String[] args) {
		 MyRunnable t = new MyRunnable();
//		MyThread m = new MyThread("one-----");
//		MyThread n = new MyThread("two-----");
		Thread o = new Thread(t);
		Thread p = new Thread(t);
//		m.run();// 仅仅是对象的调用方法，而线程创建了，并没有被运行
//		m.start();// 开启线程，并执行该线程的run()
//		n.start();
		o.start();
		try {Thread.sleep(10);}catch(Exception e) {}
			t.flag= false;
		p.start();
//		for(int i =0; i<60;i++) {
//		System.out.println("main--"+i);
//		}
	}
}
/**
 * 问题一、为什么要覆盖run()呢？ 
	答：Thread类用于描述线程，该类就定义了一个功能，用于存储线程要运行的代码，该存储功能就是run()。
			也就是说Thread类中的run()用于存储线程要运行的代码。 
   
   问题二、调用start方法和调用run方法的区别？ 
	答：调用start方法会开启线程，让开启的线程去执行run方法中的线程任务；而直接调用run方法，线程并未开启，去执行run方法的只有主线程。 
	
	练习：创建两个线程，和主线程交替执行。 
 * @author liujun
 *
 */
class MyThread extends Thread{
	/**
	 * 	- 创建线程的第一种方式：继承Thread类。 
	 * 		
	 * 	(1)	继承Thread类
		(2)	重写Thread类中的run()。目的：将自定义的代码存储在run()，让线程运行
		(3)	创建子类对象也即创建线程对象
		(4)	调用线程的start()。该方法有2个作用：启动线程，调用run()
	 */
	MyThread(String name){
		super(name);
	}
	public void run() {
		for(int i =0; i<60;i++) {
			//线程都有自己默认的名称：Thread-编号
			/**
			 * 	static Thread currentThread()：获取当前线程对象
					getName()：获取线程名称
					setName()或者构造函数：设置线程名称
			 */
			System.out.println("MyThread run--"+i+"::"+this);
		}
	}
}
/**
 * 	创建线程的第二种方式：实现Runnable接口。 
	 * 	(1)	定义类实现Runnable接口
		(2)	覆盖Runnable接口中的run()。目的：将线程要运行的代码存放在该run()中
		(3)	通过Thread类建立线程对象
		(4)	将Runnable接口的子类对象作为实际参数传递给Thread类的构造函数。 
		
		(5)	为什么要将Runnable接口的子类对象作为实际参数传递给Thread类的构造函数？ 
			答：因为自定义的run()所属的对象是Runnable接口的子类对象，所以要让线程去运行指定对象的run()，就必须明确该run()所属的对象。
			调用Thread类的start()开启线程并调用Runnable接口子类的run方法。
 * 		
 */
class MyRunnable implements Runnable {
    private static int tick = 100;
//    Object obj = new Object();
    boolean flag = true;
    @Override
    public /*synchronized*/ void run() {//同步函数
        if(flag) {
	    		while(true) {
	        	/**
	        	 * -----对象，不是其他
	        	 * synchronized(对象) {
					    需要被同步的代码块
					}
			 *	- 同步的前提：		
			 *		- 必须要有两个或者两个以上的线程
			 *		- 必须是多个线程使用同一个锁
			 *	- 好处：
			 *		解决了多线程的安全问题
			 *	- 弊端：
			 *		多个线程需要判断锁，较为消耗资源
	        	 */
	//        	synchronized(obj) {
	            if(tick > 0) {
	                try { Thread.sleep(10); } catch(Exception e) {}
	                System.out.println(Thread.currentThread().getName()+"...sale:"+tick--);
	            		}
	            else
	            		break;
	        		}
//        }
	    }else {
		    	 while(true) 
		             show();
	    		}
	    }
        public static synchronized void show() { 
            if(tick > 0) {
                try { Thread.sleep(10); } catch(Exception e) {}
                System.out.println(Thread.currentThread().getName()+"...show...:"+tick--);
            }
        }
}
