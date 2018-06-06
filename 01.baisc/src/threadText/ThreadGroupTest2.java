package threadText;

/**
 *  
 *  由以上的代码可以看出：ThreadGroup比ExecutorService多以下几个优势  

1.ThreadGroup可以遍历线程，知道那些线程已经运行完毕，那些还在运行  

2.可以通过ThreadGroup.activeCount知道有多少线程从而可以控制插入的线程数
 * @author liujun
 *
 */
public class ThreadGroupTest2 {
	public static void main(String args[]) throws Exception {
		     ThreadGroup tg = new ThreadGroup("My Group");
		 
		     MyThread4 thrd = new MyThread4(tg, "MyThread #1");
		     MyThread4 thrd2 = new MyThread4(tg, "MyThread #2");
		     MyThread4 thrd3 = new MyThread4(tg, "MyThread #3");
		 
		     thrd.start();
		     thrd2.start();
		     thrd3.start();
		 
		     Thread.sleep(1000);
		
		     System.out.println(tg.activeCount() + " threads in thread group.");
		 
		     Thread thrds[] = new Thread[tg.activeCount()];
		     //  把此线程组及其子组中的所有活动线程复制到指定数组中。
		     tg.enumerate(thrds);
		     for (Thread t : thrds)
		       System.out.println(t.getName());
		 
		     thrd.myStop();
		     //-------- run执行过程中无异常 就正常结束
		     
		     thrd2.stopped=true;
		 
		     Thread.sleep(1000);
		     
		     System.out.println(tg.activeCount() + " threads in tg.");
		     tg.interrupt();
		   }
}
class MyThread4 extends Thread {
	    boolean stopped;
	  
	    MyThread4(ThreadGroup tg, String name) {
	      super(tg, name);
	      stopped = false;
	    }
	  
	    public void run() {
	     System.out.println(Thread.currentThread().getName() + " starting.");
	     try {
	       for (int i = 1; i < 1000; i++) {
	         System.out.print(".");
	         Thread.sleep(250);
	         synchronized (this) {
	           if (stopped)
	             break;
	         }
	       }
	     } catch (Exception exc) {
	       System.out.println(Thread.currentThread().getName() + " interrupted.");
	     }
	     System.out.println(Thread.currentThread().getName() + " exiting.");
	   }
	 
	   synchronized void myStop() {
	     stopped = true;
	   }
	}