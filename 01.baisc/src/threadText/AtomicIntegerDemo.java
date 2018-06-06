package threadText;

import java.util.concurrent.atomic.AtomicInteger ;
/**
 * 多个线程对同一个变量处理的 时候  AtomicInteger类  原子变量   getAndIcrement()	 方法
 * 		---保证线程访问的时候是安全的； 
 * @author liujun
 *-------对比   TestThreadCount
 */
class AtomicIntegerDemo
{
	static int n = 0;
	static AtomicInteger cnt = new AtomicInteger(0);
	public static void main(String[] args) 
	{
		final int NUM=1000;
		Thread [] threads = new Thread[NUM];
		for(int i=0; i<NUM; i++){
			threads[i] = new Thread(){
				public void run(){ 
					n ++;
					cnt.getAndIncrement(); 
				}
			};
		}
		for(int i=0; i<NUM; i++) threads[i].start();
		try{ Thread.sleep(1000); } 
		catch(InterruptedException ex){}
		System.out.printf("%d %b\n", n, n==NUM);
		System.out.printf("%d %b\n", cnt.get(), cnt.get()==NUM);
	}
}