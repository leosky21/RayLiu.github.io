package com.rayNotes;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *	wait()：该方法可以让线程处于冻结状态，并将线程临时存储到线程池中。
 *	notify()：唤醒指定线程池中的任意一个线程。
 *	notifyAll()：唤醒指定线程池中的所有线程。
 *
 *	问题：
 *	1、	wait()、notify()、notifyAll()等方法用来操作线程，为什么定义在了Object类中？
 	 （1）这些方法必须使用在同步中，因为它们是用来操作同步锁上的线程的状态的。
	 （2）同时在使用这些方法时，必须标识它们所属于的锁，标识方式就是：锁对象.wait();、锁对象.notify(); 、锁对象.notifyAll();。相同锁的notify()，可以获取相同锁的wait()。
	 （3）锁可以是任意对象，所以任意对象调用的方法一定定义在Object类中。
	 2、wait()、sleep()方法有什么区别？
	 相同：
	 	可以让线程处于冻结状态。
	不同点： 
		wait()可以指定时间，也可以不指定；sleep()必须指定时间。
		wait()释放CPU资源，释放锁；sleep()释放CPU资源，不释放锁。
	 
 * @author ray
 *	- 多线程的升级解决方案
 *		同步代码块或者同步函数的锁操作是隐式的，而JDK1.5中出现的Lock接口按照面向对象的思想，
 *		将锁单独封装成了一个对象，并提供了对锁的显示操作，诸如以下操作：
 *			- lock()：获取锁。
 *			- unlock()：释放锁。
 */
class Res{
	private String name;
	private int count=1;
	
	private boolean flag;//定义标记
	
// 创建新Lock
    private Lock lock = new ReentrantLock();
 // 创建和Lock接口绑定的监视器对象
 // 生产者监视器
    private Condition producer_con = lock.newCondition();
    // 消费者监视器
    private Condition consumer_con = lock.newCondition();
	
    // 提供了给商品印上生产编号的方法
	public /*synchronized*/ void set(String name) {
//		if(flag) {//当flag=true ,说明它有货
		lock.lock();
		try {
			while(flag) {
				try {
	//				this.wait();//t0,t1等待
					producer_con.await();
				}catch(InterruptedException e) {
					e.printStackTrace();
				}
			}
//		}
			this.name = name +"--"+count;
			count++;
			System.out.println(Thread.currentThread().getName()+"…Produce…"+this.name);
			flag= true;
//			this.notifyAll();//唤醒消费者
			consumer_con.signal();
		}finally {
			lock.unlock();
		}
	}
	//提供的获取商品的方法
	public /*synchronized*/ void get() {
//		if(!flag) 
		lock.lock();
		try {
			while(!flag) {
				try {
	//				this.wait();
					consumer_con.await();
					}catch(Exception e) {}//t0,t1等待
			}
			System.out.println(Thread.currentThread().getName()+"…comsumer…"+this.name);
			flag=false;
			//唤醒生产者
//			this.notifyAll();
			producer_con.signal();
		}finally {
			lock.unlock();
		}
	}
}

class Produce implements Runnable{
	private Res r;
	//一旦出现一家生产商，就必须要让他指定在哪印上生产编号
	public Produce(Res r) {
		super();
		this.r = r;
	}
	@Override
	public void run() {
		while(true) {
			r.set("food");
		}	
	}
}
class Consumer implements Runnable{
	private Res r;
	//消费者没得挑。。。没有商店，只能从Res库里获取
	public Consumer(Res r) {
		super();
		this.r = r;
	}
	@Override
	public void run() {
		while(true)
		r.get();
	}
	
}
public class ThreadTest02 {
	public static  void main(String[] args) {
		Res r = new Res();
		Produce p = new Produce(r);
		Consumer c = new Consumer(r);
		 	Thread t0 = new Thread(p);
	        Thread t1 = new Thread(p);
	        Thread t2 = new Thread(c);
	        Thread t3 = new Thread(c);
//System.out.println(t0.getName()+"::"+t1.getName()+"::"+t2.getName()+"::"+t3.getName());
	        t0.start();
	        t1.start();
	        t2.start();
	        t3.start();
	}
}
