package com.rayNotes;

public class ThreadTest03 {
	public static void main(String[] args) throws InterruptedException 
    {
		/**
		 * TODO 多线程的写法
		 * 
		 */
		 new Thread() {
	            public void run() {
	                for(int x = 0; x < 100; x++) {
	                    System.out.println(Thread.currentThread().getName()+"....."+x);
	                }
	            }
	        }.start();

//	        for(int x = 0; x < 100; x++) {
//	            System.out.println(Thread.currentThread().getName()+"....."+x);
//	        }

	        Runnable r = new Runnable() {
	            public void run() {
	                for(int x = 0; x < 100; x++) {
	                    System.out.println(Thread.currentThread().getName()+"....."+x);
	                }
	            }
	        };
	        new Thread(r).start();
	        
	        for(int x = 0; x < 100; x++) {
	            System.out.println(Thread.currentThread().getName()+"....."+x);
	        }
		
		/**
		 * TODO yield()：
		 * 		将CPU的执行权释放出去，
		 * 		作用：让线程都有平均运行的机会。
		 * 
		 */
		/**
		 * TODO 	join()：
		 * 		当A线程执行到了B线程的join()时，A就会等待，等B线程都执行完，
		 * 		A才会执行。join可以用来临时加入线程执行。 
		 */
		
//		JoinThread d = new JoinThread();
//	
//	        Thread jt1 = new Thread(d);
//	        Thread jt2 = new Thread(d);
//	        jt1.start();
//	        jt2.start();
//	        jt1.join(); // main线程等待t1线程终止。即主线程冻结。
//	        jt2.setPriority(Thread.MAX_PRIORITY);
//	        for (int x = 1; x <= 40; x++)
//	        {
////	            System.out.println("main......" + x);
//	        }
//		
		
		/**
		 * - setDaemon(boolean)
				- setDaemon(boolean)：
					将该线程标记为守护线程或用户线程。
						当正在运行的线程都是守护线程时，Java虚拟机退出。
						注意：该方法必须在启动线程前调用。 
					我们看到的都是前台线程，主线程就是前台线程。
					后台线程，应该也是守护线程吧！
					后台线程和前台线程开启、运行都没有什么区别，但是在结束时是有区别的，前台线程一经消亡，后台线程(不管什么状态)也会跟着消亡，即后台依赖前台
		 */
//		 StopThread st = new StopThread(); 
//	        Thread t1 = new Thread(st);
//	        Thread t2 = new Thread(st);
//	        t1.start();
//	        t2.setDaemon(true); // 将t2线程标记为后台线程。垃圾回收线程就是后台线程。
//	        t2.start();
//	        for (int x = 1; x <= 1000; x++)
//	        {
//	        
//	            if (x == 40)
//	            {
//	                t1.interrupt(); // 将t1中断。
//	                System.out.println("interrupt....." + x);
//	                
//	            }
//	            System.out.println("main....." + x);
//	        }
//	        System.out.println("over");
//		
		
    }
	
}
class JoinThread implements Runnable
{
    public void run()
    {
        for (int x = 1; x <= 40; x++)
        {
            System.out.println(Thread.currentThread().getName() + "......" + x);
        }
    }
}
class StopThread implements Runnable
{
    private boolean flag = true;

    public synchronized void run()
    {
        while (flag)
        {
            try
            {
                wait(); // t1、t2
            }
            catch (InterruptedException e) // 凡是线程处于冻结状态，
            {
                System.out.println(Thread.currentThread().getName() + "..................." + e.toString());
                flag = false;
            }

           
        }
        System.out.println(Thread.currentThread().getName() + "......hello");
    }
    public void changeFlag()
    {
        flag = false;
    }
}
