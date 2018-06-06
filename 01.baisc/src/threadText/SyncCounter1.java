package threadText;
/**
 * java	 引入了 对象 互斥 锁 的概念，保证 共享数据操作的完整性
 * 		· 每一个对象都对应一个moniter（监视器） ，标记 同一时刻 限制 访问该资源的权限
 * 		· 关键字 synchronized 用来与 对象的互斥锁联系 
 * @author liujun
 *		synchronized用法：
 *			
 *		·			synchronized( 对象) { 临界代码 }
 *		· 	声明方法时----
 *		    public	 synchronized  void push(){}	
 *		·   相对于 synchronized（this） 表明整个方法是  同步方法
 *
 *	-------查看 SyncCounter2.java
 */
class SyncCounter1
{
	public  static void main(String[] args){ 
		Num num = new Num();
		Thread counter1 = new Counter2(num);
		Thread counter2 = new Counter2(num);
		for( int i=0; i<10; i++ ){
			if(!num.testEquals()) break;
			try{							           
				Thread.sleep(100);
			}catch(InterruptedException e){
			}
		}
	}
}

class Num
{
	private int x=0;
	private int y=0;
	void increase(){ 
		x++; 
		y++; 
	}
	boolean testEquals(){
		boolean ok = (x==y);
		System.out.println( x + "," + y +" : " + ok);
		return ok;
	}
}

class Counter2 extends Thread
{
	private Num num;
	Counter2( Num num ){
		this.num = num;
		this.setDaemon(true);
		this.setPriority(MIN_PRIORITY);
		this.start();
	}
	public void run(){
		while(true){
			num.increase();
		}
	}
}
