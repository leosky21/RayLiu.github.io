package threadText;

import java.util.*;
/**
 * 线程分为 两种： 
 * 	一类是普通线程，一类是Daemon 线程（守护线程、后台线程）
 * 		如果普通线程结束了，则后台线程自动终止
 * ---------	垃圾回收线程是后台线程
 * 
 * @author liujun
 *
 *	不考虑让其访问固有资源，如文件、 数据库、因为他会在 任何时候 甚至在  任何一个 操作的 中间发生间断 ------
 */
public class TestThreadDaemon {
	public static void main(String args[]) {
		Thread t = new MyThread3();
		/* 设置 为 daemon 线程*/
		t.setDaemon(true);//-----------必须在 线程启动之前掉调用
		t.start();

		System.out.println( "Main-----" + new Date()+"------- main线程");
		try{ Thread.sleep(500); } 
		catch(InterruptedException ex){}
		System.out.println("Main End");
	}
}

class MyThread3 extends Thread {
	public void run() {
		for(int i=0; i<10; i++ ){
			if(i<9)
			System.out.println(  i + "--" + new Date()+"---没执行完");
			else
			System.out.println(  i + "--" + new Date()+"---执行完");
			try{ Thread.sleep(100); } 
			catch(InterruptedException ex){}
		}
	}
}
