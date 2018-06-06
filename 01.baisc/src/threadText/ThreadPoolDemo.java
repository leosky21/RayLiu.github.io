package threadText;

import java.util.concurrent.*;
/**
 * 线程池：
 * 	相关的类：  ExecutorService 接口、ThreadPoolExecutor类
 * 			  Executors 工具类
 *  常见的用法： ExecutorService pool = Executors.newCachedThreadPool();
 *  		使用其 executr（Runnable r）方法
 * @author liujun
 *
 */
class ThreadPoolDemo 
{
	public static void main(String[] args) 
	{
		ExecutorService pool = Executors.newCachedThreadPool();// 创建一个线程池
		MyTasks t1 = new MyTasks(5);
		MyTasks t2 = new MyTasks(7);
		MyTasks t3 = new MyTasks(8);
		pool.execute(t1);
		pool.execute(t2);
		pool.execute(t3);
		// 建立完 shutdown
		pool.shutdown();
	}
}
class MyTasks implements Runnable
{
	int n=10;
	public MyTasks(int n){ this.n=n;}
	public void run(){
		for(int i=0;i<n; i++)System.out.print(i);
	}
}
