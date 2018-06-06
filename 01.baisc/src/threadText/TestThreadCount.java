package threadText;
/**
 * 线程的不确定。
 * 		大部分情况下是 5000  true 有时会出现 4999 false；
 * 
 * 做线程 不确定性 控制----多线程同步；
 * 	·	同时运行的程序 共享数据，就必须考虑其他线程的状态和数据
 * 	·------ ------查看 SyncCounter1、2.java
 * @author liujun
 *
 */
class TestThreadCount 
{
	public static int cnt=0;
	public static void main(String[] args) 
	{
		final int NUM=5000;
		Thread [] threads = new Thread[NUM];
		for(int i=0; i<NUM; i++){
			threads[i] = new Thread(){
				public void run(){ 
					cnt++;
					try{ Thread.sleep(10); } catch(InterruptedException ex){}
				}
			};
		}
		for(int i=0; i<NUM; i++) threads[i].start();

		try{ Thread.sleep(1); } catch(InterruptedException ex){}
		/* 对比 AtomicIntegerDemo.java*/
		System.out.printf("%d %b\n", cnt, cnt==NUM);
	}
}
